package com.aziza.cereal.Model;

public class DataModel {
    String id, nom, prenom, identifiant, motDePasse;
    String id_utilisateur,numTel,addresse,cin;

    public DataModel(String id,String id_utilisateur,String numTel,String addresse,String cin, String nom, String prenom, String identifiant, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.id_utilisateur=id_utilisateur;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.numTel=numTel;
        this.addresse=addresse;
        this.cin=cin;
    }


    public String getId() {
        return id;
    }

    public String getId_utilisateur() {
        return id_utilisateur;
    }


    public String getNumTel() {
        return numTel;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getCin() {
        return cin;
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
