// User.java (Parent)
package com.example.demospringboot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @Column(name = "id_user")
    private String idUser; // Input Manual (misal: M001, D001)

    private String nama;
    private String alamat;
    
    @Column(name = "no_hp")
    private String noHP;

    public User() {}
    public User(String idUser, String nama, String alamat, String noHP) {
        this.idUser = idUser;
        this.nama = nama;
        this.alamat = alamat;
        this.noHP = noHP;
    }

    // Getters & Setters
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNoHP() { return noHP; }
    public void setNoHP(String noHP) { this.noHP = noHP; }
}