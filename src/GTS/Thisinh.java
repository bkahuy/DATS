/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GTS;

/**
 *
 * @author buikh
 */
public class Thisinh {
    private int sobd, tongdiem;
    private String hoten, gioitinh, nganh;

    public int getSobd() {
        return sobd;
    }

    public int getTongdiem() {
        return tongdiem;
    }

    public String getHoten() {
        return hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setSobd(int sobd) {
        this.sobd = sobd;
    }

    public void setTongdiem(int tongdiem) {
        this.tongdiem = tongdiem;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public Thisinh() {
        this.sobd = 0;
        this.tongdiem = 0;
        this.hoten = "";
        this.gioitinh = "";
        this.nganh = "";
    }

    public Thisinh(int sobd, int tongdiem, String hoten, String gioitinh, String nganh) {
        this.sobd = sobd;
        this.tongdiem = tongdiem;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.nganh = nganh;
    }
    
    public String Hochong(){
        if (tongdiem >= 29){
            return "hoc bong";
        }
        else {
            return "";
        }
    }
    
    
}
