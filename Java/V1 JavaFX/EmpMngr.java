package com.example.empmngr;

import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

public class EmpMngr extends Application {
    protected MongoClient dbClient;
    protected MongoCollection<Document> employeeCollection;
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        dbClient = getClient();
        employeeCollection = dbClient.getDatabase("employees").getCollection("current_employees");


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("empmngr.fxml"));

        scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Employee Manager");
        stage.setScene(scene);
        stage.show();

        EmpMngrController controller = fxmlLoader.getController();
        controller.setMngr(this);
        controller.setEmployeeCollection(employeeCollection);

    }

    public static void main(String[] args) throws Exception {
        launch();

    }



    private MongoClient getClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    private MongoDatabase getDatabase(MongoClient client, String database) {
        return client.getDatabase(database);
    }

    private MongoCollection<Document> getCollection(MongoDatabase database, String collection) {
        return database.getCollection(collection);
    }

    private static void getAllDocuments(MongoCollection<Document> col) {
        FindIterable<Document> fi = col.find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println((cursor.next().toJson()));
            }
        } finally {
            cursor.close();
        }
    }

    private static class Employee {
        int id;
        String firstName;
        String lastName;
        String department;
        int salary;

        Employee(int id, String firstName, String lastName, String department, int salary) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.department = department;
            this.salary = salary;
        }
    }


}