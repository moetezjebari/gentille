package org.example.Controller;

<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
=======
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
>>>>>>> e4e9b39 (hello)
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.example.Model.Post;
import org.example.Service.PostService;
import java.io.IOException;
import java.util.List;

public class PostListController {
    @FXML private VBox postsContainer;
    @FXML private TextArea input_comment ;
    private int currentRow = 0;
    private int currentColumn = 0;
    private final int MAX_COLUMNS = 2; // Nombre de colonnes dans la grille

    public void initialize() {
<<<<<<< HEAD
=======

>>>>>>> e4e9b39 (hello)
        loadPostsFromDatabase();
        System.out.println("inittttt");
    }

    private void loadPostsFromDatabase() {

        List<Post> posts = PostService.getAllPosts();
        postsContainer.getChildren().clear();

        for (Post post : posts) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PostItem.fxml"));
                Parent postNode = loader.load();

                PostItemController controller = loader.getController();
                controller.setPostData(post);

                // Ajoute l'élément dans le GridPane avec les coordonnées
                postsContainer.getChildren().add(postNode);

                // Mise à jour des coordonnées pour le prochain post

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}