package com.iuc.cs2i4.test.gestion.modele;

public class Produit {
    private int id;
    private String name;
    private String description;
    private int qté;
    private double price;

    public Produit() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQté() {
        return qté;
    }

    public void setQté(int qté) {
        this.qté = qté;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
