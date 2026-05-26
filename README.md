# Smart Parking — Distributed Gate Access System

Project ini merupakan simulasi sistem parkir pintar berbasis distributed system.

## Teknologi yang Digunakan

- Java RMI
- Socket
- Multithread
- Java

## Fitur Sistem

- Validasi RFID
- Gate masuk
- Gate keluar
- Cek slot parkir
- Remote validation
- Concurrent access
- Fault tolerance sederhana

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
│   └── Vehicle.java
├── .gitignore
└── README.md