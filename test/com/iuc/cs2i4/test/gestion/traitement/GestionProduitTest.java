package com.iuc.cs2i4.test.gestion.traitement;

import com.iuc.cs2i4.test.gestion.modele.Produit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestionProduitTest {
    private GestionProduit gp;
    private Produit produit;

    private String url;
    private String userName;
    private String userPassword;
    private String driverName;

    private Connection conn;
    private Statement statement;
    private ResultSet resultset;

    public GestionProduitTest() {
        super();
    }

    @BeforeEach
    public void setup() {
        driverName = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://127.0.0.1:3306/bd_gestion?serverTimezone=Africa/Douala";
        userName = "root";
        userPassword = "";

        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        gp = new GestionProduit(url, userName, userPassword);
        produit = new Produit();
        produit.setName(sb.toString());
        produit.setDescription(sb.toString());
        produit.setQté(random.nextInt());
        produit.setPrice(random.nextInt() * 100);
    }

    @AfterEach
    public void teardown() {
        produit = null;
        gp = null;
    }

    @Test
    @Order(1)
    public void creeProduitTest() {
        int sizeBefore = 0;
        int sizeAfter = 0;

        sizeBefore = sizeTable();
        System.out.println("Size Before : " + sizeBefore);

        int nbre = gp.creerProduit(produit);

        if (resultset != null) {
            try {
                resultset.close();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        sizeAfter = sizeTable();
        System.out.println("Size After : " + sizeAfter);

        assertEquals(1, nbre);
        assertEquals(sizeAfter, ++sizeBefore);
    }

    public int sizeTable() {
        int result = 0;
        String query = "SELECT * FROM produit";
        try {
            Class.forName(driverName);
            System.out.println("==== Load Driver inside Test OK ====");
        }
        catch (ClassNotFoundException exc) {
            System.out.println("==== Load Driver inside Test Error ====");
            exc.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, userName, userPassword);
            System.out.println("==== Connection inside Test OK ====");
        }
        catch (SQLException exc) {
            System.out.println("==== Connection inside Test Error ====");
            exc.printStackTrace();
        }

        if (conn != null) {
            try {
                statement = conn.createStatement();
            }
            catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        try {
            resultset = statement.executeQuery(query);
            if (resultset != null) {
                resultset.beforeFirst();
                resultset.last();
                result = resultset.getRow();
            }
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }


        return result;
    }

    @Test
    @Order(2)
    public void lireProduitTest() {
        int id = 1;
        produit = gp.lireProduit(id);
    }
}
