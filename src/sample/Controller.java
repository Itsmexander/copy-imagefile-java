package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;


public class Controller {
    @FXML
    ImageView refrigeratorImg, freeze1Image;

    @FXML
    Button addBtn;

    @FXML
    AnchorPane pane;
    @FXML public void initialize(){
        refrigeratorImg.setImage(new Image("images/r.png"));
        refrigeratorImg.setX(250);
        refrigeratorImg.setY(100);
        refrigeratorImg.setFitWidth(430*0.8);
        refrigeratorImg.setFitHeight(818*0.8);

        freeze1Image = new ImageView(new Image("images/icecream.jpg"));
        freeze1Image.setX(270);
        freeze1Image.setY(500);
        freeze1Image.setFitHeight(199);
        freeze1Image.setFitWidth(253);
        pane.getChildren().add(freeze1Image);


        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                // SET FILECHOOSER INITIAL DIRECTORY
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                // DEFINE ACCEPTABLE FILE EXTENSION
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));
                // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
                File file = chooser.showOpenDialog(addBtn.getScene().getWindow());
                if (file != null){
                    try {
                        // CREATE FOLDER IF NOT EXIST
                        File destDir = new File("images");
                        destDir.mkdirs();
                        // RENAME FILE
                        String[] fileSplit = file.getName().split("\\.");
                        String filename = LocalDate.now()+"_"+System.currentTimeMillis()+"."+fileSplit[fileSplit.length - 1];
                        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
                        // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                        Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                        // SET NEW FILE PATH TO IMAGE
                        freeze1Image.setImage(new Image(target.toUri().toString()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });






    }

}
