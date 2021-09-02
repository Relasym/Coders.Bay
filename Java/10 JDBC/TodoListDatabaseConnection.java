import java.sql.*;
import java.util.ArrayList;

public class TodoListDatabaseConnection {
    private final Connection dbConnection;
    private final ArrayList<String> tables = new ArrayList<>();

    public TodoListDatabaseConnection(String db) throws SQLException {
        dbConnection = DriverManager.getConnection(db);
        fetchTables();
        checkTableSetup();
    }


    //users
    public int addUser(String name) throws SQLException {
        PreparedStatement addUser = dbConnection.prepareStatement("INSERT INTO \"USER\" (USER_NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        addUser.setString(1, name);
        addUser.executeUpdate();
        ResultSet generatedKeys = addUser.getGeneratedKeys();
        int returnValue = -1;
        if (generatedKeys.next()) {
            returnValue = generatedKeys.getInt(1);
        }
        return returnValue;
    }

    public boolean userExists(int userid) throws SQLException {
        PreparedStatement userExists = dbConnection.prepareStatement("SELECT USER_ID FROM \"USER\" WHERE USER_ID=? FETCH FIRST 1 ROW ONLY");
        userExists.setInt(1, userid);
        ResultSet users = userExists.executeQuery();
        return users.next();
    }

    public void deleteUserAndTodolists (int user_id) throws SQLException {
        ArrayList<Integer> lists = getTodoLists(user_id);
        for (Integer listID : lists) {
            deleteTodolist(listID);
        }
        deleteUser(user_id);

    }


    public void deleteUser(int user_id) throws SQLException {
        PreparedStatement removeUser = dbConnection.prepareStatement("DELETE FROM \"USER\" WHERE USER_ID=?");
        removeUser.setInt(1, user_id);
        removeUser.executeUpdate();
    }

    public void removeUsers(ArrayList<Integer> user_ids) throws SQLException {
        for (int user_id : user_ids) {
            deleteUser(user_id);
        }
    }

    public ArrayList<Integer> findUsers(String name) throws SQLException {
        PreparedStatement getUsers = dbConnection.prepareStatement("SELECT * FROM \"USER\" WHERE USER_NAME=?");
        getUsers.setString(1, name);
        ResultSet users = getUsers.executeQuery();
        ArrayList<Integer> user_ids = new ArrayList<>();
        while (users.next()) {
            user_ids.add(users.getInt("USER_ID"));
        }
        return user_ids;
    }

    public void clearAllUsers() throws SQLException {
        PreparedStatement removeUser = dbConnection.prepareStatement("DELETE FROM \"USER\"");
        removeUser.executeUpdate();
    }

    //todolists
    public int newTodolist(int owner, String name) throws SQLException {
        PreparedStatement newTodolist = dbConnection.prepareStatement("INSERT INTO TODOLIST (TODOLIST_NAME, TODOLIST_OWNER) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
        newTodolist.setString(1, name);
        newTodolist.setInt(2, owner);
        newTodolist.executeUpdate();
        ResultSet generatedKeys = newTodolist.getGeneratedKeys();
        int returnValue = -1;
        if (generatedKeys.next()) {
            returnValue = generatedKeys.getInt(1);
        }
        return returnValue;
    }

    public ArrayList<String> getTasksInTodolist(int todolist) throws SQLException {
        PreparedStatement getTasks = dbConnection.prepareStatement("SELECT TASK.task_name FROM TODOTASK, TASK WHERE todotask.task_id=TASK.task_id AND todotask.todolist_id=(?)");
        getTasks.setInt(1, todolist);
        ResultSet resultTasks = getTasks.executeQuery();

        PreparedStatement getListName = dbConnection.prepareStatement("SELECT TODOLIST_NAME FROM TODOLIST WHERE TODOLIST_ID=(?)");
        getListName.setInt(1, todolist);
        ResultSet resultName = getListName.executeQuery();

        ArrayList<String> resultsAsStrings = new ArrayList<>();
        resultName.next();
        resultsAsStrings.add(resultName.getString("TODOLIST_NAME"));

        while (resultTasks.next()) {
            resultsAsStrings.add(resultTasks.getString("task_name"));
        }
        return resultsAsStrings;
    }

    public Todolist getTodoList (int todolist) throws SQLException {

        PreparedStatement getTasks = dbConnection.prepareStatement("SELECT TASK.task_name FROM TODOTASK, TASK WHERE todotask.task_id=TASK.task_id AND todotask.todolist_id=(?)");
        getTasks.setInt(1, todolist);
        ResultSet resultTasks = getTasks.executeQuery();

        PreparedStatement getListName = dbConnection.prepareStatement("SELECT TODOLIST_NAME FROM TODOLIST WHERE TODOLIST_ID=(?)");
        getListName.setInt(1, todolist);
        ResultSet resultName = getListName.executeQuery();

        resultName.next();
        String name = resultName.getString(("TODOLIST_NAME"));
        ArrayList<String> tasks = new ArrayList<>();
        while (resultTasks.next()) {
            tasks.add(resultTasks.getString("task_name"));
        }
        return new Todolist(todolist,name,tasks);
    }

    public ArrayList<Integer> getTodoLists (int owner) throws  SQLException {
        ArrayList<Integer> todolists = new ArrayList<>();
        PreparedStatement getTodolists = dbConnection.prepareStatement("SELECT TODOLIST_ID FROM TODOLIST WHERE TODOLIST_OWNER=?");
        getTodolists.setInt(1,owner);
        ResultSet lists = getTodolists.executeQuery();
        while (lists.next()) {
            todolists.add(lists.getInt("TODOLIST_ID"));
        }
        return todolists;
    }

    public ArrayList<Integer> findTodoList(String todolist) throws SQLException {
        ArrayList<Integer> lists = new ArrayList<>();
        PreparedStatement findLists = dbConnection.prepareStatement("SELECT TODOLIST_ID FROM TODOLIST WHERE TODOLIST_NAME=? FETCH FIRST 1 ROW ONLY");
        findLists.setString(1, todolist);
        ResultSet todolists = findLists.executeQuery();
        while (todolists.next()) {
            lists.add(todolists.getInt("TODOLIST_ID"));
        }
        return lists;
    }

    public boolean todolistExists(int todolistid) throws SQLException {
        PreparedStatement todolistExists = dbConnection.prepareStatement("SELECT TODOLIST_ID FROM TODOLIST WHERE TODOLIST_ID=? FETCH FIRST 1 ROW ONLY");
        todolistExists.setInt(1, todolistid);
        ResultSet todolists = todolistExists.executeQuery();
        return todolists.next();
    }

    public void deleteTodolist(int todolistID) throws SQLException {
        deleteTasksFromTodolist(todolistID);
        PreparedStatement deleteTodolist = dbConnection.prepareStatement("DELETE FROM TODOLIST WHERE TODOLIST_ID=?");
        deleteTodolist.setInt(1,todolistID);
        deleteTodolist.executeUpdate();
    }

    public void deleteTasksFromTodolist (int todolistID) throws SQLException {
        PreparedStatement deleteTasks = dbConnection.prepareStatement("DELETE FROM TODOTASK WHERE TODOLIST_ID=?");
        deleteTasks.setInt(1,todolistID);
        deleteTasks.executeUpdate();
    }

    //tasks
    public int newTask(String task) throws SQLException {
        PreparedStatement newTask = dbConnection.prepareStatement("INSERT INTO TASK (TASK_NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        newTask.setString(1, task);
        newTask.executeUpdate();
        ResultSet generatedKeys = newTask.getGeneratedKeys();
        int returnValue = -1;
        if (generatedKeys.next()) {
            returnValue = generatedKeys.getInt(1);
        }
        return returnValue;
    }

    public void addTaskToTodolist(int task, int todolist) throws SQLException {
        PreparedStatement addTask = dbConnection.prepareStatement("INSERT INTO TODOTASK (TODOLIST_ID, TASK_ID) VALUES (?,?)");
        addTask.setInt(1, todolist);
        addTask.setInt(2, task);
        addTask.executeUpdate();
    }

    public void removeTaskFromTodolist(int task, int todolist) throws SQLException {
        PreparedStatement addTask = dbConnection.prepareStatement("DELETE FROM TODOTASK WHERE TODOLIST_ID=? AND TASK_ID=?");
        addTask.setInt(1, todolist);
        addTask.setInt(2, task);
        addTask.executeUpdate();
    }

    public boolean taskExists(int taskid) throws SQLException {
        PreparedStatement taskExists = dbConnection.prepareStatement("SELECT TASK_ID FROM TASK WHERE TASK_ID=? FETCH FIRST 1 ROW ONLY");
        taskExists.setInt(1, taskid);
        ResultSet tasks = taskExists.executeQuery();
        return tasks.next();
    }

    public boolean taskIsUsed(int taskid) throws SQLException {
        PreparedStatement taskUsed = dbConnection.prepareStatement("SELECT TASK_ID FROM TODOTASK WHERE TASK_ID=? FETCH FIRST 1 ROW ONLY");
        taskUsed.setInt(1, taskid);
        ResultSet tasks = taskUsed.executeQuery();
        return tasks.next();
    }

    public void deleteTask(int taskID) throws SQLException {
        PreparedStatement deleteTask = dbConnection.prepareStatement("DELETE FROM TASK WHERE TASK_ID=?");
        deleteTask.setInt(1,taskID);
        deleteTask.executeUpdate();
    }


    //tables
    public void fetchTables() throws SQLException {
        assert dbConnection != null;
        DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
        ResultSet currentTables = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
        tables.clear();
        while (currentTables.next()) {
            tables.add(currentTables.getString("TABLE_NAME"));
        }

    }

    public void printTables() throws SQLException {
        fetchTables();
        for (String table : tables) {
            System.out.println(table);
        }
    }

    public void checkTableSetup() throws SQLException {
        fetchTables();
        if (!tables.contains("USER")) {
            System.out.println("Table USER not found, creating");
            Statement createTableUser = dbConnection.createStatement();
            createTableUser.executeUpdate("create table \"USER\"(user_id   int not null generated always as identity ( start with 1 , increment by 1), user_name varchar(100), primary key (user_id))");
        }
        if (!tables.contains("TODOLIST")) {
            System.out.println("Table TODOLIST not found, creating");
            Statement createTableUser = dbConnection.createStatement();
            createTableUser.executeUpdate("""
                    create table todolist
                    (
                        todolist_id    int not null generated always as identity ( start with 1 , increment by 1),
                        todolist_name  varchar(100),
                        todolist_owner int references "USER" (user_id),
                        primary key (todolist_id)
                    )""");
        }
        if (!tables.contains("TASK")) {
            System.out.println("Table TASK not found, creating");
            Statement createTableUser = dbConnection.createStatement();
            createTableUser.executeUpdate("""
                    create table task(
                        task_id   int not null generated always as identity ( start with 1 , increment by 1),
                        task_name varchar(100),
                        primary key (task_id)
                    )""");
        }
        if (!tables.contains("TODOTASK")) {
            System.out.println("Table TODOTASK not found, creating");
            Statement createTableUser = dbConnection.createStatement();
            createTableUser.executeUpdate("create table todotask" +
                    "(" +
                    "    todolist_id int references todolist(todolist_id)," +
                    "    task_id int references task(task_id)," +
                    "    primary key (todolist_id,task_id)" +
                    ")");
        }

    }

    public void deleteOrphanedTasks() throws SQLException {
        PreparedStatement deleteOrphanedTasks = dbConnection.prepareStatement("DELETE FROM TASK WHERE TASK_ID NOT IN (SELECT TASK_ID FROM TODOTASK GROUP BY TASK_ID)");
        deleteOrphanedTasks.executeUpdate();
    }




    public boolean existsInTable(String table, String attribute, String value) throws SQLException {
        PreparedStatement checkTable = dbConnection.prepareStatement("SELECT 1 FROM ? WHERE ?=?");
        checkTable.setString(1,table);
        checkTable.setString(2,attribute);
        checkTable.setString(3,value);
        ResultSet results = checkTable.executeQuery();
        return(results.next());
    }


    //temp util
    public void printArrayList(ArrayList<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }

    public class Todolist {
        int id;
        String name;
        ArrayList<String> tasks;

        Todolist(int id, String name, ArrayList<String> tasks) {
            this.id=id;
            this.name=name;
            this.tasks=tasks;
        }
    }


}
