package com.aziza.cereal.Model;

public class DataModel {
    String id, nom, prenom, identifiant, motDePasse;

    public DataModel(String id, String nom, String prenom, String identifiant, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }


    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
}
