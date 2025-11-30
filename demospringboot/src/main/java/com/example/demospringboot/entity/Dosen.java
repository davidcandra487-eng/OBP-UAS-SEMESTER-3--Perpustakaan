package com.example.demospringboot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "dosen")
@PrimaryKeyJoinColumn(name = "id_user")
public class Dosen extends User {
    private Integer nip;
    private String fakultas;
    private String jabatan;

    public Dosen() {}
    // Getters & Setters
    public Integer getNip() { return nip; }
    public void setNip(Integer nip) { this.nip = nip; }
    public String getFakultas() { return fakultas; }
    public void setFakultas(String fakultas) { this.fakultas = fakultas; }
    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }
}