// // URL API backend Spring Boot Anda
// const API_URL = '/api/koleksi/buku';

// // --- 1. FUNGSI LOAD DATA (READ) ---
// async function loadDataBuku() {
//     try {
//         const response = await fetch(API_URL);
//         const dataBuku = await response.json();
        
//         const tbody = document.querySelector('#tabelBuku tbody');
//         tbody.innerHTML = ''; // Bersihkan tabel

//         if(dataBuku.length === 0) {
//             tbody.innerHTML = '<tr><td colspan="6" style="text-align:center;">Belum ada data buku.</td></tr>';
//             return;
//         }

//         dataBuku.forEach(buku => {
//             const statusClass = buku.status.toLowerCase() === 'tersedia' ? 'status-tersedia' : 'status-dipinjam';

//             const row = `
//                 <tr>
//                     <td><strong>${buku.kodeKoleksi}</strong></td>
//                     <td>${buku.judul}</td>
//                     <td>${buku.penulis}</td>
//                     <td>${buku.tahunTerbit}</td>
//                     <td>${buku.lokasiRak}</td>
//                     <td><span class="badge ${statusClass}">${buku.status}</span></td>
//                 </tr>
//             `;
//             tbody.innerHTML += row;
//         });

//     } catch (error) {
//         console.error("Error mengambil data:", error);
//         // Alert atau tampilkan pesan error yang lebih informatif di UI
//     }
// }

// // --- 2. FUNGSI TAMBAH DATA (CREATE) ---
// document.getElementById('bukuForm').addEventListener('submit', async function(event) {
//     event.preventDefault(); // Mencegah reload halaman

//     // Ambil data dari input form (pastikan ID match dengan HTML)
//     const dataBaru = {
//         kodeKoleksi: document.getElementById('kode').value,
//         judul: document.getElementById('judul').value,
//         penulis: document.getElementById('penulis').value,
//         tahunTerbit: parseInt(document.getElementById('tahun').value),
//         lokasiRak: document.getElementById('rak').value,
//         isbn: document.getElementById('isbn').value,
//         status: document.getElementById('status').value
//     };

//     try {
//         const response = await fetch(API_URL, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(dataBaru)
//         });

//         if (response.ok) {
//             alert("✅ Buku berhasil disimpan!");
//             document.getElementById('bukuForm').reset(); // Kosongkan form
//             loadDataBuku(); // Refresh tabel
//         } else {
//             // Jika status kode 4xx atau 5xx
//             alert("❌ Gagal menyimpan buku. Pastikan ID unik dan server berjalan di port 8081.");
//         }
//     } catch (error) {
//         console.error("Error koneksi:", error);
//         alert("Terjadi kesalahan koneksi ke server.");
//     }
// });

// // Panggil fungsi load saat halaman pertama kali dibuka
// loadDataBuku();