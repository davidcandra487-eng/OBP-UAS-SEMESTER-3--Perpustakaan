package com.example.demospringboot.controller;

import com.example.demospringboot.entity.Peminjaman;
import com.example.demospringboot.service.KoleksiService;
import com.example.demospringboot.service.PeminjamanService;
import com.example.demospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/peminjaman")
public class PeminjamanController {

    @Autowired private PeminjamanService peminjamanService;
    @Autowired private UserService userService;
    @Autowired private KoleksiService koleksiService;

    @GetMapping("")
    public String listPeminjaman(Model model) {
        model.addAttribute("listPeminjaman", peminjamanService.getAllPeminjaman());
        return "peminjaman-list";
    }

    @GetMapping("/add")
    public String formPeminjaman(Model model) {
        model.addAttribute("peminjaman", new Peminjaman());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("bukus", koleksiService.getAllBuku());
        return "peminjaman-form";
    }

    @GetMapping("/edit/{id}")
    public String editPeminjaman(@PathVariable Long id, Model model) {
        model.addAttribute("peminjaman", peminjamanService.getPeminjamanById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("bukus", koleksiService.getAllBuku());
        return "peminjaman-form";
    }

    @PostMapping("/save")
    public String savePeminjaman(@ModelAttribute Peminjaman peminjaman, RedirectAttributes redirect) {

        String kodeBuku = peminjaman.getObjBuku().getKodeKoleksi();
        String statusBaru = peminjaman.getStatusPinjam();
        Long idTransaksi = peminjaman.getId(); // Bisa null jika transaksi baru

        // Cek ketersediaan hanya jika status yang akan disimpan adalah "Aktif" (Dipinjam)
        if ("Aktif".equalsIgnoreCase(statusBaru)) {

            // Pengecekan: Apakah ada pinjaman lain yang AKTIF untuk buku ini?
            boolean isBukuDipinjamLain = peminjamanService.isBukuSudahDipinjamAktif(kodeBuku, idTransaksi);

            if (isBukuDipinjamLain) {
                // Buku sedang dipinjam oleh orang lain, tolak transaksi sebagai 'Aktif'
                redirect.addFlashAttribute("error", "Buku ini sedang dipinjam oleh user lain. Transaksi tidak dapat disimpan sebagai 'Aktif'.");
                
                if (idTransaksi == null) {
                    // Kembali ke form Tambah
                    return "redirect:/peminjaman/add"; 
                } else {
                    // Kembali ke form Edit
                    return "redirect:/peminjaman/edit/" + idTransaksi; 
                }
            }
        }
        
        peminjamanService.savePeminjaman(peminjaman);

        redirect.addFlashAttribute("success", "Transaksi berhasil disimpan!");
        return "redirect:/peminjaman";
    }


    @GetMapping("/delete/{id}")
    public String deletePeminjaman(@PathVariable Long id) {
        peminjamanService.deletePeminjaman(id);
        return "redirect:/peminjaman";
    }
}
