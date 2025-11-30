package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    boolean existsByObjBuku_KodeKoleksiAndStatusPinjam(String kodeKoleksi, String statusPinjam);
    long countByObjBuku_KodeKoleksiAndStatusPinjamAndIdNot(String kodeKoleksi, String statusPinjam, Long id);
}
