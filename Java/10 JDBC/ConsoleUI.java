import java.sql.SQLException;
import java.util.*;

public class ConsoleUI {
    SortedMap<Integer, String> Menu;
    TodoListDatabaseConnection todoList;
    Scanner scanner;
    boolean displayStackTrace = false;

    public ConsoleUI(String database) throws SQLException {
        Menu = new TreeMap<>();
        Menu.put(0, "Main Menu");
        Menu.put(10, "Create New User");
        Menu.put(11, "Find User by Name");
        Menu.put(12, "Set Active User");
        Menu.put(13, "Delete User by ID");
        Menu.put(20, "Create new Todolist");
        Menu.put(21, "Find TodoList by Name");
        Menu.put(22, "Delete Todolist");
        Menu.put(23, "Add Existing Task to Todolist");
        Menu.put(24, "Add New Task to Todolist");
        Menu.put(25, "Get all todolists of User");
        Menu.put(26, "get TodoList by ID");
        Menu.put(30, "Add new Task");
        Menu.put(31, "Delete Task");
        Menu.put(32, "Find all Tasks");
        Menu.put(40, "Quit");

        scanner = new Scanner(System.in);
        todoList = new TodoListDatabaseConnection(database);

        mainLoop();
    }

    private void mainLoop() {
        String userInput = "-1";
        int activeUser = -1;
        int userSelection = Integer.parseInt(userInput);
        while (userSelection != Menu.lastKey()) {
            System.out.println("----------------");
            if (activeUser == -1) {
                System.out.println("No Active User!");
            } else {
                System.out.println("Active User: " + activeUser);
            }
            displayMenu();
            System.out.println("Please enter Selection:");
            userInput = scanner.nextLine();
            System.out.println("|" + userInput + "|");
            try {
                userSelection = Integer.parseInt(userInput);
            } catch (Exception e) {
                System.out.println("Not a Number");
                userSelection = -1;
            }
            if (userSelection != -1) {
                switch (userSelection) {
                    case 10: //new User
                        System.out.println("Enter new Username:");
                        String newUserName = scanner.nextLine();
                        try {
                            int newUserID = todoList.addUser(newUserName);
                            System.out.println("Created new user " + newUserName + " with id " + newUserID);
                        } catch (SQLException e) {
                            System.out.println("invalid Username!");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 11: // find User
                        System.out.println("Enter username to find:");
                        String findUserName = scanner.nextLine();
                        try {
                            ArrayList<Integer> foundUsers = todoList.findUsers(findUserName);
                            if (foundUsers.size() > 0) {
                                for (Integer userid : foundUsers) {
                                    System.out.println("Found User, id is: " + userid);
                                }
                            } else {
                                System.out.println("No User found");
                            }
                        } catch (SQLException e) {
                            System.out.println("could not find username");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 12: // set current User
                        System.out.println("Enter userid to set:");
                        try {
                            int userID = Integer.parseInt(scanner.nextLine());
                            if (todoList.userExists(userID)) {
                                activeUser = userID;
                            }
                        } catch (SQLException e) {
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                            System.out.println("Not a valid User");
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break;
                    case 13: //delete User
                        System.out.println("Enter userid to delete (will also delete all todolists of user)");
                        String userID = scanner.nextLine();
                        try {
                            int deleteUserID = Integer.parseInt(userID);
                            todoList.deleteUserAndTodolists(deleteUserID);
                            System.out.println("User " + userID + " deleted!");
                            activeUser = -1;
                        } catch (SQLException e) {
                            System.out.println("could not delete User");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break;

                    case 20: //new todolist
                        System.out.println("Enter name of new Todolist:");
                        String newTodoList = scanner.nextLine();
                        if (activeUser != -1) {
                            try {
                                int newTodoListID = todoList.newTodolist(activeUser, newTodoList);
                                System.out.println("Created new TodoList with id " + newTodoListID);

                            } catch (SQLException e) {
                                System.out.println("could not create todolist");
                                if (displayStackTrace) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println("Set active user first!");
                        }
                        break;

                    case 21: //find todolist
                        System.out.println("Enter Name of todolist to find");
                        String todoListName = scanner.nextLine();
                        try {
                            ArrayList<Integer> foundLists = todoList.findTodoList(todoListName);
                            if (foundLists.size() == 0) {
                                System.out.println("No results found");
                            } else {
                                for (Integer listID : foundLists) {
                                    System.out.println("Found List, ID is: " + listID);
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("could not find List");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 22: //delete todolist
                        System.out.println("Enter ID of todolist to delete");

                        try {
                            int todoListID = Integer.parseInt(scanner.nextLine());
                            if (todoList.todolistExists(todoListID)) {
                                todoList.deleteTodolist(todoListID);
                            }
                        } catch (SQLException e) {
                            System.out.println("could not find username");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break;
                    case 23: {//add task to todolist
                        System.out.println("Enter Task ID:");
                        String task = scanner.nextLine();
                        System.out.println("Enter Todolist ID:");
                        String todolist = scanner.nextLine();
                        try {
                            int taskID = Integer.parseInt(task);
                            int todolistID = Integer.parseInt(todolist);
                            todoList.addTaskToTodolist(taskID, todolistID);
                            System.out.println("Added Task " + taskID + " to Todolist " + todolistID);
                        } catch (SQLException e) {
                            System.out.println("adding failed");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break; }
                    case 24:
                        System.out.println("Enter Task Name");
                        String task = scanner.nextLine();
                        System.out.println("Enter Todolist ID:");
                        String todolist = scanner.nextLine();

                        try {
                            int todolistID = Integer.parseInt(todolist);
                            int taskID= todoList.newTask(task);
                            try {
                                todoList.addTaskToTodolist(taskID,todolistID);
                            }
                            catch (SQLException e){
                                System.out.println("add task");
                                if (displayStackTrace) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("could not create task");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }



                        break;
                    case 25: //get todolists of user
                        System.out.println("User ID:");
                        String userid = scanner.nextLine();
                        try {
                            int getUserID = Integer.parseInt(userid);
                            ArrayList<Integer> results = todoList.getTodoLists(getUserID);
                            System.out.println("Todolists of user " + getUserID + ":");
                            for (Integer listID : results) {
                                System.out.println(listID);
                            }
                        } catch (SQLException e) {
                            System.out.println("adding failed");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break;

                    case 26: //get todolist by id
                        System.out.println("Enter todolist ID");
                        String todolistID = scanner.nextLine();
                        try {
                            int listID = Integer.parseInt(todolistID);
                            TodoListDatabaseConnection.Todolist list = todoList.getTodoList(listID);
                            System.out.println("Todolist " + listID + ":");
                            System.out.println(list.name + ":");
                            for (String currentItem : list.tasks) {
                                System.out.println(currentItem);
                            }
                        } catch (SQLException e) {
                            System.out.println("could not get todolist");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break;


                    case 30: //add new task
                        System.out.println("Enter new Task:");
                        String newTask = scanner.nextLine();
                        try {
                            int newTaskID = todoList.newTask(newTask);
                            System.out.println("Created new Task " + newTask + " with id " + newTaskID);
                        } catch (SQLException e) {
                            System.out.println("invalid Task");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 31: //delete task
                        System.out.println("Enter taskid to delete:");
                        String deleteTask = scanner.nextLine();
                        try {
                            int delete = Integer.parseInt(deleteTask);
                            todoList.deleteTask(delete);
                            System.out.println("Task deleted");
                        } catch (SQLException e) {
                            System.out.println("Could not delete Task, is it in use?");
                            if (displayStackTrace) {
                                e.printStackTrace();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                        }
                        break;
                    case 32: // find all tasks
                    case 40: //quit
                        try {
                            todoList.deleteOrphanedTasks();
                        } catch (SQLException e) {
                            System.out.println("Could not delete Orphaned Tasks");
                            e.printStackTrace();
                        }
                        System.out.println("Quitting...");
                        break;
                    default:
                        System.out.println("Not a valid choice");
                        break;
                }
            }
        }
    }

    private void displayMenu() {
        for (Map.Entry<Integer, String> entry : Menu.entrySet()) {
            if (entry.getKey() == 0) {
                System.out.println(entry.getValue());
            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

}
