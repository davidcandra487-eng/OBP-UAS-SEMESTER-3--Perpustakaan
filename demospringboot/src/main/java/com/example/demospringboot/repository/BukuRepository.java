package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; 

@Repository
// PK JPA yang digunakan JpaRepository adalah String (kodeKoleksi)
public interface BukuRepository extends JpaRepository<Buku, String> {
    
    // Metode kustom untuk mencari Buku berdasarkan ISBN (PK Logis)
    Optional<Buku> findByIsbn(String isbn);
    
    // Metode kustom untuk menghapus Buku berdasarkan ISBN
    void deleteByIsbn(String isbn);
}