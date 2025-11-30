package com.example.demospringboot.controller;

import com.example.demospringboot.entity.*;
import com.example.demospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 

@Controller
@RequestMapping("/user") // Semua URL diawali /user
public class UserController {

    @Autowired private UserService userService;

    // ----------------------------------------------------------------------
    // --- MAHASISWA ---
    // ----------------------------------------------------------------------
    
    /**
     * Menampilkan daftar mahasiswa dan juga menangani pemuatan data untuk form Edit
     * menggunakan parameter 'id' (ID User).
     * @param id ID User mahasiswa yang akan diedit (optional).
     */
    @GetMapping("/mahasiswa")
    public String listMahasiswa(@RequestParam(value = "id", required = false) String id, Model model) {
        // 1. Ambil semua data untuk menampilkan list
        model.addAttribute("listMahasiswa", userService.getAllMahasiswa());

        Mahasiswa mahasiswaRec = new Mahasiswa(); // Default: Mode Tambah

        // 2. Cek apakah ada parameter ID (Mode Edit)
        if (id != null && !id.isEmpty()) {
            // Ambil data mahasiswa berdasarkan ID
            mahasiswaRec = userService.getMahasiswaById(id);
            
            // Jika data tidak ditemukan, inisialisasi objek baru
            if (mahasiswaRec == null) {
                mahasiswaRec = new Mahasiswa();
                model.addAttribute("error", "Data Mahasiswa dengan ID: " + id + " tidak ditemukan.");
            }
        }
        
        // 3. Masukkan data ke model untuk FORM (digunakan oleh mahasiswa-list.html)
        model.addAttribute("mahasiswaRec", mahasiswaRec); 
        
        // 4. Return nama file HTML yang berisi list DAN form
        return "mahasiswa-list";
    }
    
    // Gabungkan Add/Edit/Delete ke dalam satu POST mapping
    @PostMapping("/mahasiswa/save")
    public String saveMahasiswa(@ModelAttribute Mahasiswa mahasiswa, 
                                @RequestParam(required = false) String add, 
                                @RequestParam(required = false) String edit, 
                                @RequestParam(required = false) String delete,
                                RedirectAttributes redirectAttributes) {

        // Logika untuk tombol DELETE
        if (delete != null) {
            if (mahasiswa.getIdUser() == null || mahasiswa.getIdUser().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Gagal menghapus data: ID User tidak terisi.");
                return "redirect:/user/mahasiswa";
            }
            try {
                userService.deleteMahasiswa(mahasiswa.getIdUser());
                redirectAttributes.addFlashAttribute("message", "Data Mahasiswa dengan NIM " + mahasiswa.getNim() + " berhasil dihapus!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Gagal menghapus data Mahasiswa. Error: " + e.getMessage());
            }
        } 
        // Logika untuk tombol ADD
        else if (add != null) {
            if (mahasiswa.getIdUser() == null || mahasiswa.getIdUser().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Gagal menambah data: ID User wajib diisi.");
                return "redirect:/user/mahasiswa";
            }
            try {
                userService.saveMahasiswa(mahasiswa);
                redirectAttributes.addFlashAttribute("message", "Data Mahasiswa " + mahasiswa.getNama() + " berhasil ditambahkan!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Gagal menyimpan data Mahasiswa. Pastikan ID User dan NIM belum pernah digunakan.");
            }
        }
        // Logika untuk tombol EDIT
        else if (edit != null) {
            if (mahasiswa.getIdUser() == null || mahasiswa.getIdUser().isEmpty()) {
                 redirectAttributes.addFlashAttribute("error", "Gagal mengedit data: ID User tidak terisi.");
                return "redirect:/user/mahasiswa";
            }
            try {
                userService.saveMahasiswa(mahasiswa);
                redirectAttributes.addFlashAttribute("message", "Data Mahasiswa " + mahasiswa.getNama() + " berhasil diubah!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Gagal mengubah data Mahasiswa. Error: " + e.getMessage());
            }
        }
        
        return "redirect:/user/mahasiswa";
    }
    
    // ----------------------------------------------------------------------
    // --- DOSEN ---
    // ----------------------------------------------------------------------

    /**
     * Menampilkan daftar dosen dan juga menangani pemuatan data untuk form Edit
     * menggunakan parameter 'id' (ID User).
     * @param id ID User dosen yang akan diedit (optional).
     */
    @GetMapping("/dosen")
    public String listDosen(@RequestParam(value = "id", required = false) String id, Model model) {
        // 1. Ambil semua data untuk menampilkan list
        model.addAttribute("listDosen", userService.getAllDosen());

        Dosen dosenRec = new Dosen(); // Default: Mode Tambah
        
        // 2. Cek apakah ada parameter ID (mode Edit)
        if (id != null && !id.isEmpty()) {
            // Mode EDIT: Ambil data dosen berdasarkan ID
            dosenRec = userService.getDosenById(id);
            
            // Pastikan tidak null, jika null buat objek baru
            if (dosenRec == null) {
                dosenRec = new Dosen();
                model.addAttribute("error", "Data Dosen dengan ID: " + id + " tidak ditemukan.");
            }
        }
        
        // 3. Masukkan data ke model untuk FORM
        model.addAttribute("dosenRec", dosenRec); 
        
        // 4. Kembali ke halaman list
        return "dosen-list";
    }
    
    // Gabungkan Add/Edit/Delete ke dalam satu POST mapping
    @PostMapping("/dosen/save")
    public String saveDosen(@ModelAttribute Dosen dosen, 
                            @RequestParam(required = false) String add, 
                            @RequestParam(required = false) String edit, 
                            @RequestParam(required = false) String delete,
                            RedirectAttributes redirectAttributes) {

        // Logika untuk tombol DELETE
        if (delete != null) {
            if (dosen.getIdUser() == null || dosen.getIdUser().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Gagal menghapus data: ID User tidak terisi.");
                return "redirect:/user/dosen";
            }
            try {
                userService.deleteDosen(dosen.getIdUser());
                redirectAttributes.addFlashAttribute("message", "Data Dosen dengan NIP " + dosen.getNip() + " berhasil dihapus!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Gagal menghapus data Dosen. Error: " + e.getMessage());
            }
        } 
        // Logika untuk tombol ADD
        else if (add != null) {
            if (dosen.getIdUser() == null || dosen.getIdUser().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Gagal menambah data: ID User wajib diisi.");
                return "redirect:/user/dosen";
            }
            try {
                userService.saveDosen(dosen);
                redirectAttributes.addFlashAttribute("message", "Data Dosen " + dosen.getNama() + " berhasil ditambahkan!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Gagal menyimpan data Dosen. Pastikan ID User dan NIP belum pernah digunakan.");
            }
        } 
        // Logika untuk tombol EDIT
        else if (edit != null) {
            if (dosen.getIdUser() == null || dosen.getIdUser().isEmpty()) {
                 redirectAttributes.addFlashAttribute("error", "Gagal mengedit data: ID User tidak terisi.");
                return "redirect:/user/dosen";
            }
            try {
                userService.saveDosen(dosen);
                redirectAttributes.addFlashAttribute("message", "Data Dosen " + dosen.getNama() + " berhasil diubah!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Gagal mengubah data Dosen. Error: " + e.getMessage());
            }
        }
        
        return "redirect:/user/dosen";
    }
}