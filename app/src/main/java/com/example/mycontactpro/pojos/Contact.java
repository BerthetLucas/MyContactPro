package com.example.mycontactpro.pojos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "prenom")
    private String prenom;
    @ColumnInfo(name = "societe")
    private String societe;
    @ColumnInfo(name = "tel")
    private String tel;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "secteur")
    private String secteur;
    @ColumnInfo(name = "favori")
    private int favori;

    public Contact(String nom, String prenom, String societe, String tel, String email, String secteur, int favori) {
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.tel = tel;
        this.email = email;
        this.secteur = secteur;
        this.favori = favori;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", societe='" + societe + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", secteur='" + secteur + '\'' +
                ", favori=" + favori +
                '}';
    }
}
