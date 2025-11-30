package com.example.demospringboot.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max; // Import Max
import jakarta.validation.constraints.Min; // Import Min

@Entity
@Table(name = "mahasiswa")
@PrimaryKeyJoinColumn(name = "id_user")
public class Mahasiswa extends User {
    private Integer nim;
    private String jurusan;
    
    // REVISI: Nilai minimum diubah menjadi 1
    @Min(1) 
    @Max(14) 
    private int semester;

    public Mahasiswa() {}
    // Getters & Setters
    public Integer getNim() { return nim; }
    public void setNim(Integer nim) { this.nim = nim; }
    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}