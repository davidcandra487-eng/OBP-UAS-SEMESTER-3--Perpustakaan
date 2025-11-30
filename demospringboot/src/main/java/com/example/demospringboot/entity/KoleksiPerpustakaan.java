package com.example.demospringboot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "koleksi_perpustakaan")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class KoleksiPerpustakaan {
    @Id
    @Column(name = "kode_koleksi")
    private String kodeKoleksi; // Primary Key JPA

    @Column(name = "lokasi_rak")
    private String lokasiRak;
    
    @Column(name = "tahun_terbit")
    private int tahunTerbit;

    public KoleksiPerpustakaan() {}
    public KoleksiPerpustakaan(String kodeKoleksi, String lokasiRak, int tahunTerbit) {
        this.kodeKoleksi = kodeKoleksi;
        this.lokasiRak = lokasiRak;
        this.tahunTerbit = tahunTerbit;
    }

    public abstract void infoKoleksi();

    // Getters & Setters
    public String getKodeKoleksi() { return kodeKoleksi; }
    public void setKodeKoleksi(String kodeKoleksi) { this.kodeKoleksi = kodeKoleksi; }
    public String getLokasiRak() { return lokasiRak; }
    public void setLokasiRak(String lokasiRak) { this.lokasiRak = lokasiRak; }
    public int getTahunTerbit() { return tahunTerbit; }
    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }
}