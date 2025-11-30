package com.example.demospringboot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "publikasi_berkala")
@PrimaryKeyJoinColumn(name = "kode_koleksi")
public abstract class PublikasiBerkala extends KoleksiPerpustakaan {
    private String issn;

    public PublikasiBerkala() {}
    public PublikasiBerkala(String kodeKoleksi, String lokasiRak, int tahunTerbit, String issn) {
        super(kodeKoleksi, lokasiRak, tahunTerbit);
        this.issn = issn;
    }
    
    public String getIssn() { return issn; }
    public void setIssn(String issn) { this.issn = issn; }
}