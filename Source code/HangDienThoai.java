import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class HangDienThoai implements Serializable {
    private String name;
    private int yearAppear;

    public HangDienThoai(String name,int yearAppear)
    {
        this.name=name;
        this.yearAppear=yearAppear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearAppear() {
        return yearAppear;
    }

    public void setYearAppear(int yearAppear) {
        this.yearAppear = yearAppear;
    }
}
