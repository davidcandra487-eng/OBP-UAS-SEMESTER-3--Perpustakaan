package com.example.demospringboot.service;

import com.example.demospringboot.entity.*;
import com.example.demospringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;

@Service
public class KoleksiService {

    @Autowired private BukuRepository bukuRepo;
    @Autowired private JurnalRepository jurnalRepo;
    @Autowired private MajalahRepository majalahRepo;


    /* =========================================================
     * B U K U (Menggunakan ISBN sebagai kunci logis)
     * ========================================================= */
    
    public List<Buku> getAllBuku() { 
        return bukuRepo.findAll(); 
    }

    public Buku getBukuByIsbn(String isbn) { 
        return bukuRepo.findByIsbn(isbn).orElse(null); 
    }

    public void saveBuku(Buku buku) { 
        bukuRepo.save(buku); 
    }

    @Transactional 
    public void deleteBuku(String isbn) { 
        // Menggunakan PK delete karena JpaRepository dasarnya menggunakan PK
        bukuRepo.deleteById(isbn);
    }


    /* =========================================================
     * J U R N A L (Menggunakan Kode Koleksi sebagai PK)
     * ========================================================= */

    public List<Jurnal> getAllJurnal() { 
        return jurnalRepo.findAll(); 
    }

    // Menggunakan Kode Koleksi (PK JPA)
    public Jurnal getJurnalById(String id) { 
        return jurnalRepo.findById(id).orElse(null); 
    }

    public void saveJurnal(Jurnal jurnal) { 
        jurnalRepo.save(jurnal); 
    }

    public void deleteJurnal(String id) { 
        jurnalRepo.deleteById(id); 
    }


    /* =========================================================
     * M A J A L A H (Menggunakan Kode Koleksi sebagai PK)
     * ========================================================= */

    public List<Majalah> getAllMajalah() { 
        return majalahRepo.findAll(); 
    }

    // Menggunakan Kode Koleksi (PK JPA)
    public Majalah getMajalahById(String id) { 
        return majalahRepo.findById(id).orElse(null); 
    }

    public void saveMajalah(Majalah majalah) { 
        majalahRepo.save(majalah); 
    }

    public void deleteMajalah(String id) { 
        majalahRepo.deleteById(id); 
    }
}