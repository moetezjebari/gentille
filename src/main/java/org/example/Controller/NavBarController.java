package org.example.Controller;
import com.mysql.cj.exceptions.StreamingNotifiable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class NavBarController {
    private static  VBox currentActive = null;

    private void setActive(VBox clicked) {
        // Retire la classe active de l'élément précédent
        if (currentActive != null) {
            currentActive.getStyleClass().remove("active");

        }
        // Ajoute la classe active au nouvel élément
        clicked.getStyleClass().add("active");
        currentActive = clicked;
        String rout = getCurrentActive(currentActive.getId()) ;
        shownotifications(rout);

    }

    @FXML
    private void handleNavClick(MouseEvent event) {
        setActive((VBox) event.getSource());
    }
    public String getCurrentActive(String clicked) {
        switch (clicked){
            case "acceuil":
                System.out.println("Accueil");
                return "MainView";
            case "notif":
                return "Notifications";
            default:
                return "";
        }
    }
    @FXML
    private void shownotifications(String rout) {
    try {
        // Charger la nouvelle scène depuis le fichier FXML (home.fxml)
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/" + rout + ".fxml"));
        Scene newScene = new Scene(newRoot );

        // Changer la scène actuelle
         Stage stage = (Stage) currentActive.getScene().getWindow();
        //Stage stage = new Stage();
        stage.setScene(newScene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace(); // Vérifiez la console pour des détails d'erreur
    }
    }



}
