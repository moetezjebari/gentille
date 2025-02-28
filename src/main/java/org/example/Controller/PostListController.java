package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.Model.Post;
import org.example.Service.PostService;
import java.io.IOException;
import java.util.List;

public class PostListController {
    @FXML private VBox postsContainer;
    @FXML private ComboBox<String> sortComboBox;
    @FXML TextField input_recherche ;


    public void initialize() {
   sortComboBox.getItems().addAll("plus recent", "plus ancien " , "Alphabetique");
   sortComboBox.setValue("plus recent");
   sortComboBox.setOnAction(e -> {
       loadPostsFromDatabase();
   });
        loadPostsFromDatabase();
    }

    private void loadPostsFromDatabase() {

        List<Post> posts ;
        String sortType = sortComboBox.getValue();
        switch (sortType) {
            case "plus recent":
                posts = PostService.Tri_Post();
               // Du plus récent au plus ancien
                // break;
                case "plus ancien":
                    posts = PostService.Tri_Post();
                    System.out.println("tri");// Du plus ancien au plus récent
                    break;
                    case "Alphabetique":
                        posts = PostService.Tri_Post(); // Par ordre alphabétique du titre
                        break;
                        default:
                            posts = PostService.getAllPosts(); // Sans tri particulier
                            break;
        }
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