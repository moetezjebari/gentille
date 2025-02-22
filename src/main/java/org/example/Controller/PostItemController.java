package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.Model.Comments;
import org.example.Model.Post;
import org.example.Service.CommentService;
import org.example.Util.DatabaseConnection;
<<<<<<< HEAD

=======
>>>>>>> e4e9b39 (hello)
import java.sql.Connection;
import java.util.List;


public class PostItemController {
    Connection cnx2;
    public PostItemController() {cnx2  = DatabaseConnection.getInstance().getCnx();}

    @FXML private Label titleLabel;
    @FXML private Label contentLabel;
    @FXML private Label categoryLabel;
    @FXML private ImageView postImage;
    @FXML private TextArea input_comment;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private VBox commentsContainer;
    @FXML private Button showCommentsButton;
    @FXML private HBox commentInputContainer;
    @FXML private Button commenter;

    private Post post; // Stocke les données du post
    private Comments comment;
    CommentService commentService = new CommentService();
    private boolean commentsVisible = false;


    @FXML
    private void toggleCommentInput() {
                // Inverse la visibilité du conteneur de commentaire
               commentInputContainer.setVisible(!commentInputContainer.isVisible());

                // Change le texte du bouton selon l'état
                if (commentInputContainer.isVisible()) {
                        commenter.setText("Annuler");
                        input_comment.requestFocus();
                    } else {
                        commenter.setText("commenter");
                        input_comment.clear();
                    }
            }
    @FXML
    private void handleAddComment() {
        if (input_comment.getText() == null || input_comment.getText().trim().isEmpty()) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le commentaire ne peut pas être vide!");
            alert.showAndWait();
            return;
        }
        int post_id = post.getId();
        comment = new Comments(post_id,1, input_comment.getText());
        commentService.AddComment(comment);
        input_comment.clear();
    }
    @FXML
    private void toggleComments() {
        if (!commentsVisible) {
            // Charger et afficher les commentaires
            loadComments();
            commentsContainer.setVisible(true);
            showCommentsButton.setText("Masquer Commentaires");
        } else {
            // Cacher les commentaires
            commentsContainer.setVisible(false);
            showCommentsButton.setText("Afficher Commentaires");
        }
        commentsVisible = !commentsVisible;
    }
    private void loadComments() {
        commentsContainer.getChildren().clear();
        List<Comments> comments = commentService.getCommentsByPostId(this.post.getId());

        for (Comments comment : comments) {
            this.comment = comment;

            // Créer un conteneur horizontal pour le commentaire et ses boutons
            HBox commentRow = new HBox(50); // 10 pixels d'espacement

            // Créer le conteneur vertical pour le contenu du commentaire
            VBox commentBox = new VBox(5);
            commentBox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: #ddd;");

            Label contentLabel = new Label(comment.getComment_text());
            Label dateLabel = new Label(comment.getDate());
            dateLabel.setStyle("-fx-text-fill: gray;");

            commentBox.getChildren().addAll(contentLabel, dateLabel);

            // Créer les boutons pour ce commentaire spécifique
            Button deleteBtn = new Button("Supprimer");
            Button editBtn = new Button("Modifier");

            // Ajouter les actions pour les boutons
            deleteBtn.setOnAction(e -> handleDeleteSpecificComment(comment.getComment_id()));
            editBtn.setOnAction(e -> handleEditSpecificComment(comment.getComment_id()));

            // Créer un conteneur vertical pour les boutons
            VBox buttonBox = new VBox(5);
            buttonBox.getChildren().addAll(editBtn, deleteBtn);

            // Ajouter le commentaire et les boutons au conteneur horizontal
            commentRow.getChildren().addAll(commentBox, buttonBox);

            // Ajouter la ligne complète au conteneur de commentaires
            commentsContainer.getChildren().add(commentRow);
        }
    }
    public void setPostData(Post post) {
        if (post == null) return;

        this.post = post;
        titleLabel.setText(post.getTitle() != null ? post.getTitle() : "");
        contentLabel.setText(post.getContent() != null ? post.getContent() : "");
        categoryLabel.setText(post.getCategory() != null ? post.getCategory() : "");

        // Vérification de l'image
        if (post.getImage() != null && !post.getImage().isEmpty()) {
            try {
                postImage.setImage(new Image(post.getImage()));
                postImage.setVisible(true);
            } catch (Exception e) {
                postImage.setVisible(false);
            }
        } else {
            postImage.setVisible(false);
        }
    }
    private void handleDeleteSpecificComment(int commentId) {
        commentService.DeleteSpecificComment(commentId);
        loadComments(); // Recharger les commentaires après la suppression
    }
    private void handleEditSpecificComment(int commentId) {
        // Créer une boîte de dialogue pour la modification
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier le commentaire");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nouveau commentaire:");

        dialog.showAndWait().ifPresent(newText -> {
            commentService.UpdateSpecificComment(commentId, newText);
            loadComments(); // Recharger les commentaires après la modification
        });
    }


<<<<<<< HEAD



=======
>>>>>>> e4e9b39 (hello)
}
