package com.codecool.dungeoncrawl.display;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class SaveDialog {
    private Alert alert;
    public SaveDialog(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("this is a test");
        Dialog dialog = new Dialog<>();

    }
    public void show() {
        alert.show();
    }

}
