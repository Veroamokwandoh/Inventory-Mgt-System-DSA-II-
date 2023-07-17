package com.example.inventorymanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    Alert alert;
    Connection connect;
    ResultSet result;
    PreparedStatement prepare;
    // ADMIN LOGIN METHOD
    public void Login(){
        String sql = "SELECT Username,Password FROM admin WHERE username = ? and password = ? ";

        try {
            if (
                    username.getText().isEmpty() || password.getText().isEmpty()

            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                connect = Database.connect();
                assert connect != null;
                prepare = connect.prepareStatement(sql);
                prepare.setString(1,username.getText());
                prepare.setString(2,password.getText());
                result = prepare.executeQuery();

                if(result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successful");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Credential");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}