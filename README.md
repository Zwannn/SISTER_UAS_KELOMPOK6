# 🚗 Smart Parking Distributed System

Smart Parking Distributed System merupakan simulasi sistem parkir pintar berbasis Distributed System yang mengimplementasikan Java RMI, TCP Socket, Multithreading, dan Web Monitoring Dashboard.

Sistem mensimulasikan proses validasi RFID pada gerbang masuk dan gerbang keluar parkir secara terdistribusi. Seluruh data kendaraan dan slot parkir dikelola secara terpusat melalui Validation Server sehingga konsistensi data tetap terjaga meskipun terdapat beberapa metode komunikasi yang berbeda.

---

## 📌 Fitur Utama

* RFID Validation
* Gate Masuk (Java RMI)
* Gate Keluar (Java RMI)
* Gate Masuk (TCP Socket)
* Gate Keluar (TCP Socket)
* Monitoring Slot Parkir
* Monitoring Status Server
* Concurrent Access Test
* Reset Data Parkir
* Validation Server Failure Testing
* Multithreaded Request Handling

---

## 🏗️ Arsitektur Sistem

```text
                   ParkingData
              (In-Memory Database)
                        ↑
                        |
                 ParkingServer
                    (Java RMI)
                        ↑
         +--------------+--------------+
         |                             |
    RMI Client                  Socket Server
         |                             |
         |                             |
    Web RMI Client             Socket Client
                        |
                        |
                  Flask Dashboard
```

ParkingServer bertindak sebagai Validation Server dan Single Source of Truth yang menyimpan seluruh state sistem parkir.

Socket Server berfungsi sebagai communication gateway yang meneruskan request ke Validation Server sehingga seluruh client menggunakan sumber data yang sama.

---

## 💻 Teknologi yang Digunakan

### Backend

* Java
* Java RMI
* TCP Socket Programming
* Multithreading
* Distributed Client-Server Architecture

### Frontend

* Python Flask
* HTML
* CSS

### Pengujian

* Concurrent Access Test
* Validation Server Failure Test
* Monitoring Server Status

---

## 📂 Struktur Project

```text
SmartParkingProject/
│
├── Java/
│   ├── ParkingServer.java
│   ├── ParkingInterface.java
│   ├── ParkingData.java
│   ├── Vehicle.java
│   │
│   ├── WebMasukClient.java
│   ├── WebKeluarClient.java
│   ├── WebCekSlotClient.java
│   ├── WebResetClient.java
│   │
│   ├── SocketServer.java
│   ├── SocketClientMasuk.java
│   ├── SocketClientKeluar.java
│   ├── SocketClientCekSlot.java
│   ├── SocketPingClient.java
│   │
│   ├── ConcurrentWebTest.java
│   └── SocketConcurrentTest.java
│
├── templates/
│   ├── index.html
│   ├── masuk.html
│   ├── keluar.html
│   ├── masuk_socket.html
│   ├── keluar_socket.html
│   └── concurrent.html
│
├── static/
│   └── style.css
│
├── app.py
├── README.md
└── LAPORAN/
```

---

## 🔄 Alur Sistem

### Gate Masuk RMI

```text
User
 ↓
Flask
 ↓
WebMasukClient
 ↓
ParkingServer (RMI)
 ↓
ParkingData
```

### Gate Masuk Socket

```text
User
 ↓
Flask
 ↓
SocketClientMasuk
 ↓
SocketServer
 ↓
ParkingServer (RMI)
 ↓
ParkingData
```

---

## 🧪 Pengujian Sistem

### 1. RFID Validation Test

Menguji validasi RFID yang terdaftar dan tidak terdaftar.

### 2. Slot Availability Test

Menguji perubahan jumlah slot parkir ketika kendaraan masuk atau keluar.

### 3. Concurrent Access Test

Menguji akses bersamaan oleh 10 kendaraan menggunakan multithreading.

### 4. Validation Server Failure Test

Menguji perilaku sistem ketika Validation Server dimatikan.

### 5. Socket Connectivity Test

Menguji status Socket Server menggunakan mekanisme PING.

---

## ⚠️ Fault Tolerance

Sistem memiliki monitoring terhadap status Validation Server dan Socket Server.

Apabila Validation Server tidak tersedia, sistem akan memberikan notifikasi:

```text
Validation Server Offline
```

sehingga pengguna mengetahui bahwa layanan validasi sedang tidak tersedia.

---

## 👨‍💻 Author

Basir Farel
Brayen Brian
Abin Awabin

Technology Engineering of Internet

Distributed System Project
