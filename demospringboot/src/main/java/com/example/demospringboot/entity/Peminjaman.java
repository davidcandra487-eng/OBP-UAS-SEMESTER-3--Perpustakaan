package com.example.demospringboot.entity;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "peminjaman")
public class Peminjaman implements Pinjam {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID untuk Database

    @Column(name = "kode_pinjam")
    private String kodePinjam;

    // Menggunakan LocalDate agar sesuai dengan tipe DATE di database
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglPinjam;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglKembali;

    private String statusPinjam;
    private double denda;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User objUser;

    @ManyToOne
    @JoinColumn(name = "kode_buku")
    private Buku objBuku;

    public Peminjaman() {}

    @Override
    public void pinjam() {
        this.statusPinjam = "Aktif";
        if (objBuku != null) objBuku.pinjam();
    }

    @Override
    public void kembali() {
        this.statusPinjam = "Selesai";
        if (objBuku != null) objBuku.kembali();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getKodePinjam() { return kodePinjam; }
    public void setKodePinjam(String kodePinjam) { this.kodePinjam = kodePinjam; }
    public LocalDate getTglPinjam() { return tglPinjam; }
    public void setTglPinjam(LocalDate tglPinjam) { this.tglPinjam = tglPinjam; }
    public LocalDate getTglKembali() { return tglKembali; }
    public void setTglKembali(LocalDate tglKembali) { this.tglKembali = tglKembali; }
    public String getStatusPinjam() { return statusPinjam; }
    public void setStatusPinjam(String statusPinjam) { this.statusPinjam = statusPinjam; }
    public double getDenda() { return denda; }
    public void setDenda(double denda) { this.denda = denda; }
    public User getObjUser() { return objUser; }
    public void setObjUser(User objUser) { this.objUser = objUser; }
    public Buku getObjBuku() { return objBuku; }
    public void setObjBuku(Buku objBuku) { this.objBuku = objBuku; }
}