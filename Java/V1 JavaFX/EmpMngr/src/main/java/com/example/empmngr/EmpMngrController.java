package com.example.empmngr;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.bson.Document;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


import java.io.*;
import java.util.ArrayList;


public class EmpMngrController {
//    @FXML
//    private Button importFireStore;
//    @FXML
//    private Button exportFireStore;
    private EmpMngr mngr;
    protected MongoCollection<Document> employeeCollection;
    private ArrayList<Document> employeesArrayList;
    //    private ArrayList<Integer>
    int currentSelectedEmployee = -1;

    @FXML
    private Button loadAll;
    @FXML
    private Button saveAll;
    @FXML
    private Button save;
    @FXML
    private Button discard;
    @FXML
    private Button newEmployee;
    @FXML
    private Button deleteEmployee;
    @FXML
    private Button importFile;
    @FXML
    private Button exportFile;
    @FXML
    private Button showEmployee;
    @FXML
    private Button clearEmployees;
    @FXML
    private Label employee_id;
    @FXML
    private TextField employee_firstname;
    @FXML
    private TextField employee_lastname;
    @FXML
    private TextField employee_department;
    @FXML
    private TextField employee_salary;
    @FXML
    private ListView<String> employeeList;


    @FXML
    protected void loadAll() {

        ArrayList<Document> employeesFromDatabase = employeeCollection.find().into(new ArrayList<>());
        addToLoadedEmployees(employeesFromDatabase);
        showData();
    }

    @FXML
    protected void saveAll() {
        if (employeesArrayList != null && employeesArrayList.size() > 0) {
            try {
                UpdateOptions options = new UpdateOptions().upsert(true);
                for (Document currentEmployee : employeesArrayList) {
                    if (currentEmployee.get("_id") == null) {
                        employeeCollection.insertOne(currentEmployee);  //this automatically sets the id of currentemployee
                    } else {
                        employeeCollection.updateOne(new Document("_id", currentEmployee.get("_id")), new Document("$set", currentEmployee), options);
                    }
                }
            } catch (Exception e) {
                showAlert(3, "Error", "Could not save File", e.getMessage());
            }
        } else {
            showAlert(3, "Error", "No Data to Save!", "");
        }
    }

    @FXML
    protected void save() {
        if (employeesArrayList == null) {
            employeesArrayList = new ArrayList<>();
        }
        if (!employee_firstname.getText().equals("") && !employee_lastname.getText().equals("")) {
            String firstName = employee_firstname.getText();
            String lastName = employee_lastname.getText();
            String department = employee_department.getText();
            int salary = -1;
            try {
                salary = Integer.parseInt(employee_salary.getText());
                if (currentSelectedEmployee >= 0) {
                    Document currentDocument = employeesArrayList.get(currentSelectedEmployee);
                    currentDocument.put("first_name", firstName);
                    currentDocument.put("last_name", lastName);
                    if (!department.equals("")) {
                        currentDocument.put("department", department);
                    }
                    currentDocument.put("salary", salary);
                } else {
                    //add new user
                    Document newEmployee = new Document();
                    newEmployee.append("first_name", firstName);
                    newEmployee.append("last_name", lastName);
                    if (!department.equals("")) {
                        newEmployee.append("department", department);
                    }
                    newEmployee.append("salary", salary);
                    employeesArrayList.add(newEmployee);
                }
                showData();
            } catch (NumberFormatException e) {
                showAlert(3, "Error", "Not a valid Number", "Please enter a valid Salary");
            }

        } else {
            showAlert(3, "Error", "Not enough data", "Please enter at least a First and Last Name");
        }

    }

    @FXML
    protected void discard() {
        currentSelectedEmployee = -1;
        employee_id.setText("");
        employee_firstname.setText("");
        employee_lastname.setText("");
        employee_department.setText("");
        employee_salary.setText("");
    }

    protected void setMngr(EmpMngr mngr) {
        this.mngr = mngr;
    }

    protected void setEmployeeCollection(MongoCollection<Document> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @FXML
    private void listMouseClick(MouseEvent mouseEvent) {
        currentSelectedEmployee = employeeList.getSelectionModel().getSelectedIndex();
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
            currentSelectedEmployee = employeeList.getSelectionModel().getSelectedIndex();
            showEmployee(currentSelectedEmployee);
        }

    }

    @FXML
    private void showEmployeeBtnClick() {
        currentSelectedEmployee = employeeList.getSelectionModel().getSelectedIndex();
        showEmployee(currentSelectedEmployee);
    }

    private void showEmployee(int employeeIndex) {
        if (employeeIndex >= 0) {
            if (employeesArrayList.get(employeeIndex).get("_id") != null) {
                employee_id.setText(employeesArrayList.get(employeeIndex).get("_id").toString());
            }
            if (employeesArrayList.get(employeeIndex).get("first_name") != null) {
                employee_firstname.setText(employeesArrayList.get(employeeIndex).get("first_name").toString());
            } else {
                employee_firstname.setText("");
            }
            if (employeesArrayList.get(employeeIndex).get("last_name") != null) {
                employee_lastname.setText(employeesArrayList.get(employeeIndex).get("last_name").toString());
            } else {
                employee_lastname.setText("");
            }
            if (employeesArrayList.get(employeeIndex).get("department") != null) {
                employee_department.setText(employeesArrayList.get(employeeIndex).get("department").toString());
            } else {
                employee_department.setText("");
            }
            if (employeesArrayList.get(employeeIndex).get("salary") != null) {
                employee_salary.setText(employeesArrayList.get(employeeIndex).get("salary").toString());
            } else {
                employee_salary.setText("");
            }

        } else {
            showAlert(3, "Error", "No Employee Selected", "");
        }

    }

    public void newEmployee(ActionEvent actionEvent) {
        discard();
        employee_id.setText("Will be generated automagically");
    }

    public void deleteEmployee(ActionEvent actionEvent) {
        discard();
        currentSelectedEmployee = employeeList.getSelectionModel().getSelectedIndex();
        if (currentSelectedEmployee >= 0) {
            employeesArrayList.remove(currentSelectedEmployee);
            showData();
        } else {
            showAlert(3, "Error", "No Employee Selected!", "");
        }

    }

    public void importFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to import");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showOpenDialog(mngr.scene.getWindow());
        if (file != null) {

            try {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                JSONArray employees = new JSONArray();
                JSONParser parser = new JSONParser();
                String line;
                StringBuilder jsonFile = new StringBuilder();
                line = bufferedReader.readLine();
                while (line != null) {
                    jsonFile.append(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();

                try {
                    JSONObject outerJSON = (JSONObject) parser.parse(jsonFile.toString());
                    if (outerJSON.get("type").equals("employeeManager data file") && outerJSON.get("employees") != null) {
                        employees = (JSONArray) outerJSON.get("employees");
                    } else {
                        showAlert(3, "Error", "Invalid File Type", "");
                    }
                } catch (Exception e) {
                    showAlert(3, "Error", "Could not parse File", "");
                }

                if (employees.size() > 0) {
                    try {ArrayList<Document> importedEmployees = new ArrayList<>();
                        employees.forEach((employee) -> {
                            importedEmployees.add(Document.parse(employee.toString()));
                        });
                        addToLoadedEmployees(importedEmployees);
                        showData();
                    } catch (Exception e) {
                        showAlert(3, "Error", "Corrupted Employee Object", "");
                    }

                }
            } catch (IOException e) {
                showAlert(3, "Error", "Could not import File", e.getMessage());
            }

        }
    }

    public void exportFile(ActionEvent actionEvent) {
        if (employeesArrayList != null && employeesArrayList.size() > 0) {
            for (Document currentDocument : employeesArrayList) {
                if (currentDocument.get("_id") == null) {
                    showAlert(2, "Warning", "Data without Id detected", "Exporting this data may lead to duplicates when importing. Save to database first to prevent this");
                    break;
                }
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select File to Export to");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
            File file = fileChooser.showSaveDialog(mngr.scene.getWindow());
            if (file != null) {

                try {
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    JSONArray employees = new JSONArray();
                    JSONParser parser = new JSONParser();
                    for (Document currentDocument : employeesArrayList) {
                        JSONObject employee = (JSONObject) parser.parse(currentDocument.toJson());
                        employees.add(employee);
                    }
                    JSONObject outerObject = new JSONObject();
                    outerObject.put("type", "employeeManager data file");
                    outerObject.put("amount", employees.size());
                    outerObject.put("employees", employees);

//            System.out.println(employees.toString());
                    bufferedWriter.write(outerObject.toJSONString());
                    bufferedWriter.close();
                    writer.close();
                } catch (Exception e) {
                    showAlert(3, "Error", "Could not export File", e.getMessage());
                }
            }
        } else {
            showAlert(3, "Error", "No Data to save", "");
        }
    }

    private void showData() {
        ObservableList<String> employeeOL = employeeList.getItems();
        employeeOL.clear();
        employeesArrayList.forEach((document) -> {
            employeeOL.add(document.get("first_name") + " " + document.get("last_name"));
        });
    }

    private void showAlert(int type, String title, String header, String text) {
        Alert alert;
        switch (type) {
            case 0: {
                alert = new Alert(Alert.AlertType.NONE);
                break;
            }
            case 1: {
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            }
            case 2: {
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            }
            case 3: {
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            }
            case 4: {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            }
            default: {
                alert = new Alert(Alert.AlertType.NONE);
            }
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private boolean employeeExists(ArrayList<Document> employees, Document employee) {
        for (int i = 0; i < employee.size(); i++) {
            if (employees.get(i).get("_id").equals(employee.get("_id"))) {
                return true;
            }
        }
        return false;
    }

    public void addToLoadedEmployees(ArrayList<Document> newEmployees) {
        if (employeesArrayList == null) {
            employeesArrayList = new ArrayList<>();
        }
        for (Document employee : newEmployees) {
            if (!employeesArrayList.contains(employee)) {
                employeesArrayList.add(employee);
            }
        }
    }

    public void clearEmployeeList(ActionEvent actionEvent) {
        employeesArrayList.clear();
        showData();
    }

    public void importFromFirebase(ActionEvent actionEvent) throws IOException {
//        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .setProjectId("eternal-delight-323311")
//                .build();
//        FirebaseApp.initializeApp(options);
//        Firestore db = FirestoreClient.getFirestore();

//        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.getApplicationDefault())
//                .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
//                .build();
//
//        FirebaseApp.initializeApp(options);

//        InputStream serviceAccount = new FileInputStream("C:\\Users\\codersbay\\Desktop\\eternal-delight-323311-4960ae3246ab.json");
//        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .build();
//        FirebaseApp.initializeApp(options);
//
//        Firestore db = FirestoreClient.getFirestore();


    }

    public void exportToFirebase(ActionEvent actionEvent) {
    }
}