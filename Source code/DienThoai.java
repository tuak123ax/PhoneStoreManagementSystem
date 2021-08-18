import java.io.Serializable;

public class DienThoai implements Serializable {
    private String name;
    private String hang;
    private int pin;
    private int ram;
    private int internalMemory;
    private int price;
    private int SoLuong;

    public DienThoai() {
    }

    public DienThoai(String name, String hang, int pin, int ram, int internalMemory, int price, int soLuong) {
        this.name = name;
        this.hang = hang;
        this.pin = pin;
        this.ram = ram;
        this.internalMemory = internalMemory;
        this.price = price;
        this.SoLuong = soLuong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(int internalMemory) {
        this.internalMemory = internalMemory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
