package com.aziza.cereal.Model;

public class DataModel {
    String id, nom, prenom, identifiant, motDePasse;
    String id_utilisateur,numTel,addresse,cin,reponse;
    String id_detail_transaction,date_trans,quantite_trans,
            id_type_cereal,id_trans,id_agriculteur,id_details_transaction,id_type,type_cereal;

    public DataModel(String id, String nom, String prenom,String reponse, String identifiant, String motDePasse,
                     String id_utilisateur, String numTel, String addresse, String cin,
                     String id_detail_transaction, String date_trans, String quantite_trans,
                     String id_type_cereal, String id_trans,
                     String id_agriculteur, String id_details_transaction, String id_type,
                     String type_cereal) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.id_utilisateur = id_utilisateur;
        this.numTel = numTel;
        this.addresse = addresse;
        this.cin = cin;
        this.reponse=reponse;
        this.id_detail_transaction = id_detail_transaction;
        this.date_trans = date_trans;
        this.quantite_trans = quantite_trans;
        this.id_type_cereal = id_type_cereal;
        this.id_trans = id_trans;
        this.id_agriculteur = id_agriculteur;
        this.id_details_transaction = id_details_transaction;
        this.id_type = id_type;
        this.type_cereal = type_cereal;
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

    public String getId_detail_transaction() {
        return id_detail_transaction;
    }

    public String getReponse() {
        return reponse;
    }

    public String getDate_trans() {
        return date_trans;
    }

    public String getQuantite_trans() {
        return quantite_trans;
    }

    public String getId_type_cereal() {
        return id_type_cereal;
    }

    public String getId_trans() {
        return id_trans;
    }

    public String getId_agriculteur() {
        return id_agriculteur;
    }

    public String getId_details_transaction() {
        return id_details_transaction;
    }

    public String getId_type() {
        return id_type;
    }

    public String getType_cereal() {
        return type_cereal;
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
