package com.aziza.cereal.Model;

public class Reponse {
    String id,id_africulteur,reponse;

    public Reponse(String id, String id_africulteur, String reponse) {
        this.id = id;
        this.id_africulteur = id_africulteur;
        this.reponse = reponse;
    }

    public String getId() {
        return id;
    }

    public String getId_africulteur() {
        return id_africulteur;
    }

    public String getReponse() {
        return reponse;
    }
}
