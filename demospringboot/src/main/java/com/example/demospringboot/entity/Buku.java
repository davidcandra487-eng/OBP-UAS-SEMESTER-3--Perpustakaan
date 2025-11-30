package com.example.demospringboot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "buku")
@PrimaryKeyJoinColumn(name = "kode_koleksi")
public class Buku extends KoleksiPerpustakaan implements Pinjam, KaryaTulis {
    
    // ISBN dijadikan UNIQUE, berperan sebagai PK Logis
    @Column(unique = true)
    private String isbn; 
    private String judul;
    private String status; // Tersedia / Dipinjam
    private String penulis;

    public Buku() {}

    @Override
    public void infoKoleksi() { System.out.println("Buku: " + judul); }

    @Override
    public void pinjam() { this.status = "Dipinjam"; }

    @Override
    public void kembali() { this.status = "Tersedia"; }

    @Override
    public String getPenulis() { return this.penulis; }

    // Getters & Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
}