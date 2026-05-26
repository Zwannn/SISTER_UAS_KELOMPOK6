# Smart Parking — Distributed Gate Access System

Project ini merupakan simulasi sistem parkir pintar berbasis sistem terdistribusi. Sistem menggunakan konsep client-server, di mana gate masuk dan gate keluar berperan sebagai distributed client, sedangkan server bertugas melakukan validasi RFID, mengecek ketersediaan slot parkir, dan memperbarui status kendaraan.

## Teknologi yang Digunakan

- Java
- Java RMI
- Multithread
- Socket sebagai konsep komunikasi jaringan
- Remote validation

## Catatan Teknologi

Pada implementasi project ini, teknologi utama yang digunakan untuk remote validation adalah Java RMI. Java RMI dipilih karena seluruh sistem dibuat menggunakan Java sehingga client dapat memanggil method validasi pada server secara langsung melalui jaringan.

CORBA tidak digunakan pada implementasi karena project difokuskan pada Java RMI. Socket digunakan sebagai landasan konsep komunikasi client-server, sedangkan komunikasi utama program dijalankan menggunakan Java RMI.

## Fitur Sistem

- Validasi RFID
- Gate masuk sebagai client
- Gate keluar sebagai client
- Buka palang secara simulasi
- Cek slot parkir tersedia
- Remote validation ke server
- Pengujian concurrent access 10 mobil bersamaan
- Pengujian fault tolerance ketika server mati

## Struktur Project

```text
SmartParkingProject/
├── LAPORAN/
├── src/
│   ├── GateMasukClient.java
│   ├── GateKeluarClient.java
│   ├── ParkingServer.java
│   ├── ParkingInterface.java
│   ├── ParkingData.java
│   ├── Vehicle.java
│   └── ConcurrentAccessTest.java
├── .gitignore
└── README.md