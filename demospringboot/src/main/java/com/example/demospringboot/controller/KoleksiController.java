package com.example.demospringboot.controller;

import com.example.demospringboot.entity.*;
import com.example.demospringboot.service.KoleksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/koleksi")
public class KoleksiController {

    @Autowired private KoleksiService koleksiService;


    // ============================================================
    // -------------------------  B U K U  -------------------------
    // ============================================================
    @GetMapping("/buku")
    public String listBuku(
            @RequestParam(value = "id", required = false) String isbn, 
            Model model) {

        model.addAttribute("listBuku", koleksiService.getAllBuku());

        Buku bukuRec = new Buku();   // Default: mode ADD

        if (isbn != null && !isbn.isEmpty()) {
            bukuRec = koleksiService.getBukuByIsbn(isbn); 

            if (bukuRec == null) {
                bukuRec = new Buku();
                model.addAttribute("error", 
                    "Data buku dengan ISBN: " + isbn + " tidak ditemukan.");
            }
        }

        model.addAttribute("bukuRec", bukuRec);

        return "buku-list";
    }

    @PostMapping("/buku/save")
    public String saveBuku(
            @ModelAttribute Buku buku,
            @RequestParam(required = false) String add,
            @RequestParam(required = false) String edit,
            @RequestParam(required = false) String delete,
            RedirectAttributes redirectAttributes) {

        // DELETE
        if (delete != null) {
            if (buku.getKodeKoleksi() == null || buku.getKodeKoleksi().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Kode Koleksi buku tidak boleh kosong.");
                return "redirect:/koleksi/buku";
            }

            try {
                koleksiService.deleteBuku(buku.getKodeKoleksi()); // Menggunakan Kode Koleksi (PK JPA)
                redirectAttributes.addFlashAttribute("message",
                        "Buku dengan Kode Koleksi " + buku.getKodeKoleksi() + " berhasil dihapus!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal menghapus buku: " + e.getMessage());
            }
        }

        // ADD
        else if (add != null) {
            try {
                koleksiService.saveBuku(buku); 
                redirectAttributes.addFlashAttribute("message",
                        "Buku " + buku.getJudul() + " berhasil ditambahkan!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal menambah buku: " + e.getMessage());
            }
        }

        // EDIT
        else if (edit != null) {
            try {
                koleksiService.saveBuku(buku);
                redirectAttributes.addFlashAttribute("message",
                        "Buku " + buku.getJudul() + " berhasil diperbarui!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal mengedit buku: " + e.getMessage());
            }
        }

        return "redirect:/koleksi/buku";
    }


    // ============================================================
    // -------------------------  J U R N A L  -------------------------
    // ============================================================
    @GetMapping("/jurnal")
    public String listJurnal(
            @RequestParam(value = "id", required = false) String kodeKoleksi, 
            Model model) {

        model.addAttribute("listJurnal", koleksiService.getAllJurnal());

        Jurnal jurnalRec = new Jurnal();

        if (kodeKoleksi != null && !kodeKoleksi.isEmpty()) {
            jurnalRec = koleksiService.getJurnalById(kodeKoleksi); 

            if (jurnalRec == null) {
                jurnalRec = new Jurnal();
                model.addAttribute("error", 
                    "Data jurnal dengan Kode Koleksi: " + kodeKoleksi + " tidak ditemukan.");
            }
        }

        model.addAttribute("jurnalRec", jurnalRec);

        return "jurnal-list";
    }

    @PostMapping("/jurnal/save")
    public String saveJurnal(
            @ModelAttribute Jurnal jurnal,
            @RequestParam(required = false) String add,
            @RequestParam(required = false) String edit,
            @RequestParam(required = false) String delete,
            RedirectAttributes redirectAttributes) {
        
        // DELETE
        if (delete != null) {
            if (jurnal.getKodeKoleksi() == null || jurnal.getKodeKoleksi().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Kode Koleksi tidak boleh kosong.");
                return "redirect:/koleksi/jurnal";
            }

            try {
                koleksiService.deleteJurnal(jurnal.getKodeKoleksi()); 
                redirectAttributes.addFlashAttribute("message",
                        "Jurnal dengan Kode Koleksi " + jurnal.getKodeKoleksi() + " berhasil dihapus!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal menghapus jurnal: " + e.getMessage());
            }
        }

        // ADD
        else if (add != null) {
            try {
                koleksiService.saveJurnal(jurnal); 
                redirectAttributes.addFlashAttribute("message",
                        "Jurnal Volume " + jurnal.getVolume() + " berhasil ditambahkan!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal menambah jurnal: " + e.getMessage());
            }
        }

        // EDIT
        else if (edit != null) {
            try {
                koleksiService.saveJurnal(jurnal);
                redirectAttributes.addFlashAttribute("message",
                        "Jurnal Kode Koleksi " + jurnal.getKodeKoleksi() + " berhasil diperbarui!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal mengedit jurnal: " + e.getMessage());
            }
        }

        return "redirect:/koleksi/jurnal";
    }


    // ============================================================
    // ------------------------- M A J A L A H ------------------------
    // ============================================================
    
    @GetMapping("/majalah")
    public String listMajalah(
            @RequestParam(value = "id", required = false) String kodeKoleksi, // Menggunakan 'kodeKoleksi'
            Model model) {

        model.addAttribute("listMajalah", koleksiService.getAllMajalah());

        Majalah majalahRec = new Majalah(); // Default: mode ADD

        if (kodeKoleksi != null && !kodeKoleksi.isEmpty()) {
            // Memanggil service untuk mencari berdasarkan kodeKoleksi (PK)
            majalahRec = koleksiService.getMajalahById(kodeKoleksi); 

            if (majalahRec == null) {
                majalahRec = new Majalah();
                model.addAttribute("error",
                        "Data majalah dengan Kode Koleksi: " + kodeKoleksi + " tidak ditemukan.");
            }
        }
        model.addAttribute("majalahRec", majalahRec);
        return "majalah-list";
    }

    @PostMapping("/majalah/save")
    public String saveMajalah(
            @ModelAttribute Majalah majalah,
            @RequestParam(required = false) String add,
            @RequestParam(required = false) String edit,
            @RequestParam(required = false) String delete,
            RedirectAttributes redirectAttributes) {

        // DELETE
        if (delete != null) {
            if (majalah.getKodeKoleksi() == null || majalah.getKodeKoleksi().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Kode Koleksi tidak boleh kosong.");
                return "redirect:/koleksi/majalah";
            }

            try {
                // Menghapus menggunakan Kode Koleksi (PK JPA)
                koleksiService.deleteMajalah(majalah.getKodeKoleksi()); 
                redirectAttributes.addFlashAttribute("message",
                        "Majalah dengan Kode Koleksi " + majalah.getKodeKoleksi() + " berhasil dihapus!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal menghapus majalah: " + e.getMessage());
            }
        } 
        
        // ADD
        else if (add != null) {
            try {
                koleksiService.saveMajalah(majalah);
                redirectAttributes.addFlashAttribute("message", 
                        "Majalah Edisi " + majalah.getEdisi() + " berhasil ditambahkan!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal menambah majalah: " + e.getMessage());
            }
        } 
        
        // EDIT
        else if (edit != null) {
            try {
                koleksiService.saveMajalah(majalah);
                redirectAttributes.addFlashAttribute("message", 
                        "Majalah Kode Koleksi " + majalah.getKodeKoleksi() + " berhasil diperbarui!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                        "Gagal mengedit majalah: " + e.getMessage());
            }
        }
        
        return "redirect:/koleksi/majalah";
    }
}