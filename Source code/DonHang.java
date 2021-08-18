import java.io.Serializable;

public class DonHang implements Serializable {
    private String ngayMua;
    private String mayMua;
    private String Hang;
    private int Pin;
    private int Ram;
    private int internalMemory;
    private String nguoiMua;
    private String Dt;
    private int giaTien;

    public DonHang(String ngayMua, String mayMua, String hang, int pin, int ram, int internalMemory, String nguoiMua, String dt, int giaTien) {
        this.ngayMua = ngayMua;
        this.mayMua = mayMua;
        Hang = hang;
        Pin = pin;
        Ram = ram;
        this.internalMemory = internalMemory;
        this.nguoiMua = nguoiMua;
        Dt = dt;
        this.giaTien = giaTien;
    }

    public String getDt() {
        return Dt;
    }

    public void setDt(String dt) {
        Dt = dt;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getMayMua() {
        return mayMua;
    }

    public void setMayMua(String mayMua) {
        this.mayMua = mayMua;
    }

    public String getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(String nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String hang) {
        Hang = hang;
    }

    public int getPin() {
        return Pin;
    }

    public void setPin(int pin) {
        Pin = pin;
    }

    public int getRam() {
        return Ram;
    }

    public void setRam(int ram) {
        Ram = ram;
    }

    public int getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(int internalMemory) {
        this.internalMemory = internalMemory;
    }
}
