package com.example.empmngr;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.GraphicsEnvironment;

import java.io.FileInputStream;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws Exception {
        EmpMngr.launch();
    }

    public void main() throws Exception {




//        String fonts[] =
//                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//
//        for ( int i = 0; i < fonts.length; i++ )
//        {
//            System.out.println(fonts[i]);
//        }






//        Text text = new Text("Goodbye Penguins!");
//        text.setX(320);
//        text.setY(350);
//        text.setFill(Color.YELLOWGREEN);
////        text.setStroke(Color.RED);
//        text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 40)); //regular
//        Glow glow = new Glow();
//        glow.setLevel(100);
//        text.setEffect(glow);
//        SepiaTone sepiaTone = new SepiaTone(00);
//        text.setEffect(sepiaTone);
//
//        FileInputStream inputstream = new FileInputStream("C:\\Users\\codersbay\\Downloads\\hans-jurgen-mager-qQWV91TTBrE-unsplash.jpg");
//        Image image = new Image(inputstream);
//        ImageView imageView = new ImageView(image);
//        imageView.setX(250);
//        imageView.setY(200);
//
//        //setting the fit height and width of the image view
//        imageView.setFitHeight(455);
//        imageView.setFitWidth(500);
//        imageView.setPreserveRatio(true);
//
//        Group group = new Group();
//
//        group.getChildren().add(imageView);
//        group.getChildren().add(text);
//
////        Rotate rotate = new Rotate();
////        rotate.setAngle(-30);
////        rotate.setPivotX(text.getX());
////        rotate.setPivotY(text.getY());
////        text.getTransforms().add(rotate);
//
//        RotateTransition rotateTransition = new RotateTransition();
//        rotateTransition.setDuration(Duration.seconds(5));
//        rotateTransition.setNode(text);
//        rotateTransition.setNode(imageView);
//        rotateTransition.setCycleCount(Animation.INDEFINITE);
//        rotateTransition.setByAngle(360); //fromAngle, toAngle, byAngle
//        rotateTransition.setInterpolator(Interpolator.LINEAR);
//        rotateTransition.play();
//
//        RotateTransition rotateTransition1 = new RotateTransition();
//        rotateTransition1.setDuration(Duration.seconds(5));
//        rotateTransition1.setNode(text);
//        rotateTransition1.setCycleCount(Animation.INDEFINITE);
//        rotateTransition1.setByAngle(-360); //fromAngle, toAngle, byAngle
//        rotateTransition1.setInterpolator(Interpolator.LINEAR);
//        rotateTransition1.play();

//        Scene scene = new Scene(group, 1024,768);


    }


}