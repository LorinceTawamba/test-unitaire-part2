package com.iuc.cs2i4.test.gestion.traitement;

import com.iuc.cs2i4.test.gestion.bd.BdManager;
import com.iuc.cs2i4.test.gestion.modele.Produit;

public class GestionProduit {

    private String url;
    private String userName;
    private String userPassword;

    BdManager bdManager;

    public GestionProduit() {
        super();
    }

    public GestionProduit(String url, String userName, String userPassword) {
        super();
        this.url = url;
        this.userName = userName;
        this.userPassword = userPassword;
        bdManager = new BdManager(url, userName, userPassword);
    }

    public int creerProduit(Produit produit) {
        int nbre = 0;
        bdManager.loadDriver();
        bdManager.connectToBd();

        String query = "INSERT INTO produit (nom, description, qté, prix) VALUES ('" + produit.getName() + "', '" + produit.getDescription() + "', " + produit.getQté() + ", " + produit.getPrice() + ")";
        nbre = bdManager.setDataInBd(query);
        return nbre;
    }

    public Produit lireProduit(int id) {
        Produit result = null;

        return result;
    }
}
