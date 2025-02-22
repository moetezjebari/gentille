package org.example.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public String url="jdbc:mysql://localhost:3306/PIDEV" ;
    public String login="root";
    public String pwd="" ;
    Connection cnx ;
    public static DatabaseConnection instance ;
<<<<<<< HEAD
=======

>>>>>>> e4e9b39 (hello)
    private DatabaseConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection établie");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
<<<<<<< HEAD
=======

>>>>>>> e4e9b39 (hello)
    public Connection getCnx(){
        try {
                       // Vérifier si la connexion est fermée ou nulle
                        if (cnx == null || cnx.isClosed()) {
                                cnx = DriverManager.getConnection(url, login, pwd);
                            }
                   } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                return cnx;
    }

    public static DatabaseConnection getInstance(){
        if (instance==null){
            instance=new DatabaseConnection() ;
        }
        return instance ;
<<<<<<< HEAD



=======
>>>>>>> e4e9b39 (hello)
    }
}
