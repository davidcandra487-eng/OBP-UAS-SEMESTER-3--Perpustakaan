package com.example.demospringboot.service;

import com.example.demospringboot.entity.*;
import com.example.demospringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired private PeminjamanRepository pinjamRepo;
    @Autowired private BukuRepository bukuRepo;

    public List<Peminjaman> getAllPeminjaman() { return pinjamRepo.findAll(); }
    
    // PERBAIKAN DISINI: Ganti String id jadi Long id
    public Peminjaman getPeminjamanById(Long id) { 
        return pinjamRepo.findById(id).orElse(null); 
    }

    public void savePeminjaman(Peminjaman p) {
        if (p.getObjBuku() != null) {
            Buku b = p.getObjBuku();
            if ("Selesai".equalsIgnoreCase(p.getStatusPinjam())) {
                b.kembali();
            } else {
                b.pinjam();
                p.setStatusPinjam("Aktif");
            }
            bukuRepo.save(b);
        }
        pinjamRepo.save(p);
    }

    // PERBAIKAN DISINI: Ganti String id jadi Long id
    public void deletePeminjaman(Long id) {
        Optional<Peminjaman> p = pinjamRepo.findById(id);
        if (p.isPresent() && p.get().getObjBuku() != null) {
            Buku b = p.get().getObjBuku();
            b.kembali();
            bukuRepo.save(b);
        }
        pinjamRepo.deleteById(id);
    }

    public boolean isBukuSudahDipinjamAktif(String kodeKoleksi, Long idTransaksiSaatIni) {
        if (idTransaksiSaatIni == null) {
            // Kasus Transaksi Baru
            // Cek keberadaan pinjaman aktif untuk kodeKoleksi
            return pinjamRepo.existsByObjBuku_KodeKoleksiAndStatusPinjam(kodeKoleksi, "Aktif");
        } else {
            // Kasus Edit Transaksi
            // Hitung pinjaman aktif yang BUKAN transaksi ini
            // Jika hasilnya > 0, artinya ada pinjaman aktif lain.
            long count = pinjamRepo.countByObjBuku_KodeKoleksiAndStatusPinjamAndIdNot(kodeKoleksi, "Aktif", idTransaksiSaatIni);
            return count > 0;
        }
    }

}