package com.example.demospringboot.service;

import com.example.demospringboot.entity.*;
import com.example.demospringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private MahasiswaRepository mhsRepo;
    @Autowired private DosenRepository dosenRepo;
    @Autowired private UserRepository userRepo; 

    // --- LOGIKA ID OTOMATIS BARU ---
    /**
     * Mencari ID User Mahasiswa terakhir (misal: M03) dan menghasilkan ID baru (M04).
     * @return ID User baru
     */
    public String generateNewMahasiswaId() {
        // Ambil semua ID User dari Mahasiswa
        List<Mahasiswa> allMhs = mhsRepo.findAll();
        
        Optional<Mahasiswa> lastMahasiswa = allMhs.stream()
            // Urutkan berdasarkan angka ID (misal: dari M01, M02, ambil yang terbesar)
            .max(Comparator.comparing(mhs -> Integer.parseInt(mhs.getIdUser().substring(1))));

        int nextNumber = 1;
        
        if (lastMahasiswa.isPresent()) {
            String lastId = lastMahasiswa.get().getIdUser(); // Misal: "M03"
            // Ambil angka dari ID (hapus huruf 'M' di depan)
            int lastNumber = Integer.parseInt(lastId.substring(1)); 
            nextNumber = lastNumber + 1;
        }

        // Format angka kembali ke format MXX (misal: 1 -> M01, 10 -> M10)
        return String.format("M%02d", nextNumber);
    }
    // ---------------------------------

    // --- MAHASISWA ---
    public List<Mahasiswa> getAllMahasiswa() { return mhsRepo.findAll(); }
    public void saveMahasiswa(Mahasiswa m) { mhsRepo.save(m); }
    public void deleteMahasiswa(String id) { mhsRepo.deleteById(id); }
    public Mahasiswa getMahasiswaById(String id) { return mhsRepo.findById(id).orElse(null); }

    // --- DOSEN ---
    public List<Dosen> getAllDosen() { return dosenRepo.findAll(); }
    public void saveDosen(Dosen d) { dosenRepo.save(d); }
    public void deleteDosen(String id) { dosenRepo.deleteById(id); }
    public Dosen getDosenById(String id) { return dosenRepo.findById(id).orElse(null); }
    
    // --- GENERAL USER (Untuk Dropdown) ---
    public List<User> getAllUsers() { return userRepo.findAll(); }
}