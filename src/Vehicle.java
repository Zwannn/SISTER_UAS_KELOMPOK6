import java.io.Serializable;
import java.time.LocalDateTime;

public class Vehicle implements Serializable {
    private String rfid;
    private LocalDateTime waktuMasuk;

    public Vehicle(String rfid) {
        this.rfid = rfid;
        this.waktuMasuk = LocalDateTime.now();
    }

    public String getRfid() {
        return rfid;
    }

    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }
}