package org.example.Service;

import javafx.scene.control.Alert;
import org.example.Model.Comments;
import org.example.Util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class CommentService {

    private Connection cnx2;
    public CommentService() {
        cnx2 = DatabaseConnection.getInstance().getCnx();
    }

    public void AddComment(Comments comment ){
            String query = "INSERT INTO comments (post_id, user_id, comment_text, created_at) VALUES (?, ?, ?, NOW())";
            try  {
                PreparedStatement st = cnx2.prepareStatement(query);
                System.out.println("click");
                st.setInt(1, comment.getPost_id());
                st.setInt(2, comment.getUser_id());
                st.setString(3, comment.getComment_text());

                int rowsInserted = st.executeUpdate();
                if (rowsInserted > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Commentaire ajouté avec succès !");
                }


            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur SQL : " + e.getMessage());
                showAlert(Alert.AlertType.ERROR, "Erreur SQL", "Impossible d'ajouter le commentaire.");
            }
        }
    public void DeleteComment(int post_id ){
        String query = "DELETE FROM comments WHERE post_id = ?";

        try (PreparedStatement stmt = cnx2.prepareStatement(query)) {
            stmt.setInt(1, post_id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                showAlert(Alert.AlertType.INFORMATION , " succes " , "Post DELETED");
            }else{
                showAlert(Alert.AlertType.WARNING , "erreur" , "Post non existant ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR , "SQL ERROR" , "Impossible de supprimer  le post.");
        }
    }
<<<<<<< HEAD
    public void UpComment(int post_id ){
        String comment_text = "Up";
        String query = "UPDATE comments SET comment_text = ? WHERE post_id = ?";
        try (PreparedStatement st = cnx2.prepareStatement(query)) {
            st.setString(1, comment_text);
            st.setInt(2, post_id);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Post mis à jour avec succès !");
            } else {
                showAlert(Alert.AlertType.WARNING, "Aucune modification", "Aucun post mis à jour.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur SQL", "Impossible de mettre à jour le post.");
        }
    }
=======

>>>>>>> e4e9b39 (hello)
    //Read Comments
    public  List<Comments> getCommentsByPostId(int postId) {

      List<Comments> comments = new ArrayList<>();
      String query = "SELECT * FROM comments WHERE post_id = ?";

      try  {
          PreparedStatement stmt = cnx2.prepareStatement(query);

          stmt.setInt(1, postId);
          ResultSet rs = stmt.executeQuery();

          while (rs.next()) {

              comments.add(new Comments(
                      rs.getInt("comment_id"),
                      rs.getInt("post_id"),
                      rs.getInt("user_id"),
                      rs.getString("comment_text"),
                      rs.getString("created_at")
              ));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return comments;
  }
    public void DeleteSpecificComment(int commentId) {
        try {
            String query = "DELETE FROM comments WHERE comment_id = ?";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setInt(1, commentId);
            pst.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Commentaire supprimé avec succès");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression du commentaire");
            e.printStackTrace();
        }
    }
    public void UpdateSpecificComment(int commentId, String newText) {
        try {
            String query = "UPDATE comments SET comment_text = ? WHERE comment_id = ?";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setString(1, newText);
            pst.setInt(2, commentId);
            pst.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Commentaire modifié avec succès");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification du commentaire");
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
