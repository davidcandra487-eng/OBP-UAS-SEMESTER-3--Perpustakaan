package com.example.demospringboot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "majalah")
@PrimaryKeyJoinColumn(name = "kode_koleksi")
// Asumsi Majalah extends PublikasiBerkala, yang pada gilirannya extends KoleksiPerpustakaan
public class Majalah extends PublikasiBerkala { 
    private String edisi; //
    private String penerbit; //

    public Majalah() {}
    
    @Override
    public void infoKoleksi() { System.out.println("Majalah Edisi: " + edisi); } //

    // Getters & Setters
    public String getEdisi() { return edisi; } //
    public void setEdisi(String edisi) { this.edisi = edisi; } //
    public String getPenerbit() { return penerbit; } //
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; } //
    
    // get/setKodeKoleksi, get/setTahunTerbit, get/setLokasiRak diwarisi dari parent.
}