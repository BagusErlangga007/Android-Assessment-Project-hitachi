# Android-Assessment-Project-hitachi
# GitHub User Search – Assessment Hitachi Bagus

Aplikasi Android berbasis **Kotlin + Jetpack Compose** untuk mencari user GitHub menggunakan
[GitHub REST API v3](https://docs.github.com/en/rest/search#search-users), menyimpan hasilnya ke **Room Database**,  
dan menampilkan detail user yang dipilih.

---

- Search User GitHub  
  Cari pengguna GitHub real-time dengan endpoint  
  `https://api.github.com/search/users?q={query}`.

- Offline Cache (Room)  
  Hasil pencarian disimpan di database lokal `github_users.db` agar bisa dibuka kembali tanpa koneksi internet.

- Detail User  
  Menampilkan informasi user yang dipilih, data diambil langsung dari database.

- UI Jetpack Compose  
  Menggunakan Material 3, ViewModel, dan Navigation Compose.

---

## Arsitektur
- **MVVM + Repository Pattern**  
  - `UserRepository` : jembatan antara Remote API dan Room Database  
  - `UserViewModel` : menyediakan data untuk UI
- **Retrofit + Moshi** untuk network call
- **Room** untuk penyimpanan lokal
- **Jetpack Compose Navigation** untuk berpindah antar screen (Search → Detail)

---


