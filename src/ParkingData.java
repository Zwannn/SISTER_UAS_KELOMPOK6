import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParkingData {
    private final Set<String> rfidTerdaftar = new HashSet<>();
    private final Map<String, Vehicle> kendaraanParkir = new HashMap<>();

    private int totalSlot = 5;

    public ParkingData() {
        rfidTerdaftar.add("RF001");
        rfidTerdaftar.add("RF002");
        rfidTerdaftar.add("RF003");
        rfidTerdaftar.add("RF004");
        rfidTerdaftar.add("RF005");
        rfidTerdaftar.add("RF006");
        rfidTerdaftar.add("RF007");
        rfidTerdaftar.add("RF008");
        rfidTerdaftar.add("RF009");
        rfidTerdaftar.add("RF010");
    }

    public synchronized String prosesMasuk(String rfid) {
        if (!rfidTerdaftar.contains(rfid)) {
            return "AKSES DITOLAK: RFID tidak terdaftar.";
        }

        if (kendaraanParkir.containsKey(rfid)) {
            return "AKSES DITOLAK: Kendaraan sudah berada di area parkir.";
        }

        if (totalSlot <= 0) {
            return "AKSES DITOLAK: Slot parkir penuh.";
        }

        kendaraanParkir.put(rfid, new Vehicle(rfid));
        totalSlot--;

        return "AKSES DITERIMA: Palang masuk terbuka. Sisa slot: " + totalSlot;
    }

    public synchronized String prosesKeluar(String rfid) {
        if (!kendaraanParkir.containsKey(rfid)) {
            return "AKSES DITOLAK: Kendaraan tidak ditemukan di area parkir.";
        }

        kendaraanParkir.remove(rfid);
        totalSlot++;

        return "AKSES DITERIMA: Palang keluar terbuka. Sisa slot: " + totalSlot;
    }

    public synchronized int getSlotTersedia() {
        return totalSlot;
    }
}