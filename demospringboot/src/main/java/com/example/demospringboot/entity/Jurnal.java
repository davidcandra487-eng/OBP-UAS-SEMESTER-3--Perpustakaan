package com.example.demospringboot.entity;
import jakarta.persistence.*;

// Asumsi: PublikasiBerkala mewarisi dari KoleksiPerpustakaan
@Entity
@Table(name = "jurnal")
@PrimaryKeyJoinColumn(name = "kode_koleksi")
public class Jurnal extends PublikasiBerkala implements KaryaTulis {
    private String volume;
    private String penulis;

    public Jurnal() {}
    
    @Override
    public void infoKoleksi() { System.out.println("Jurnal Vol: " + volume); }

    @Override
    public String getPenulis() { return this.penulis; }

    // Getters & Setters
    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
    
    // Asumsi: Anda memiliki PublikasiBerkala yang extends KoleksiPerpustakaan
    // Jika tidak, Jurnal harus langsung extends KoleksiPerpustakaan.
    // get/setKodeKoleksi dan get/setTahunTerbit, get/setLokasiRak diwarisi.
}