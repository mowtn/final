package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_hocvien")
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hocvienid")
    private int HocVienID;

    @Column(name = "hinhanh")
    private String HinhAnh;
    @Column(name = "hoten")
    private String HoTen;
    @Column(name = "sodienthoai")
    private String SoDienThoai;
    @Column(name = "email")
    private String Email;
    @Column(name = "tinhthanh")
    private String TinhThanh;
    @Column(name = "quanhuyen")
    private String QuanHuyen;
    @Column(name = "phuongxa")
    private String PhuongXa;
    @Column(name = "sonha")
    private String SoNha;
    @OneToMany(mappedBy = "hocVien")
    @JsonIgnoreProperties(value = "hocVien")
    Set<DangKyHoc> dangKyHocs;

    public HocVien(String hinhAnh, String hoTen, String soDienThoai, String email, String tinhThanh, String quanHuyen, String phuongXa, String soNha) {
        HinhAnh = hinhAnh;
        HoTen = hoTen;
        SoDienThoai = soDienThoai;
        Email = email;
        TinhThanh = tinhThanh;
        QuanHuyen = quanHuyen;
        PhuongXa = phuongXa;
        SoNha = soNha;
    }

    public HocVien() {
    }

    public int getHocVienID() {
        return HocVienID;
    }

    public void setHocVienID(int hocVienID) {
        HocVienID = hocVienID;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTinhThanh() {
        return TinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        TinhThanh = tinhThanh;
    }

    public String getQuanHuyen() {
        return QuanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        QuanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return PhuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        PhuongXa = phuongXa;
    }

    public String getSoNha() {
        return SoNha;
    }

    public void setSoNha(String soNha) {
        SoNha = soNha;
    }
}
