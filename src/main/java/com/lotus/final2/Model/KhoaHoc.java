package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tb_khoahoc")
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khoahocid")
    private int KhoaHocId;
    @Column(name = "tenkhoahoc")
    @NotNull
    private String TenKhoaHoc;
    @Column(name = "thoigianhoc")
    @NotNull
    private int ThoiGianHoc;
    @Column(name = "gioithieu")
    @NotNull
    private String GioiThieu;
    @Column(name = "noidung")
    @NotNull
    private String NoiDung;
    @Column(name = "hocphi")
    @NotNull
    private float HocPhi;
    @Column(name = "sohocvien")
    @NotNull
    private int SoHocVien;
    @Column(name = "soluongmon")
    @NotNull
    private int SoLuongMon;
    @Column(name = "HinhAnh")
    @NotNull
    private String HinhAnh;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaikhoahocid")
    @JsonIgnoreProperties(value = "khoaHocs")
    LoaiKhoaHoc loaiKhoaHoc;
    @OneToMany(mappedBy = "khoaHoc")
    @JsonIgnoreProperties(value = "khoaHoc")
    Set<DangKyHoc> dangKyHocs;

    public KhoaHoc() {
    }

    public KhoaHoc(String tenKhoaHoc, int thoiGianHoc, String gioiThieu, String noiDung, float hocPhi, int soHocVien, int soLuongMon, String hinhAnh, LoaiKhoaHoc loaiKhoaHoc) {
        TenKhoaHoc = tenKhoaHoc;
        ThoiGianHoc = thoiGianHoc;
        GioiThieu = gioiThieu;
        NoiDung = noiDung;
        HocPhi = hocPhi;
        SoHocVien = soHocVien;
        SoLuongMon = soLuongMon;
        HinhAnh = hinhAnh;
        this.loaiKhoaHoc = loaiKhoaHoc;
    }

    public int getKhoaHocId() {
        return KhoaHocId;
    }

    public void setKhoaHocId(int khoaHocId) {
        KhoaHocId = khoaHocId;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        TenKhoaHoc = tenKhoaHoc;
    }

    public int getThoiGianHoc() {
        return ThoiGianHoc;
    }

    public void setThoiGianHoc(int thoiGianHoc) {
        ThoiGianHoc = thoiGianHoc;
    }

    public String getGioiThieu() {
        return GioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        GioiThieu = gioiThieu;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public float getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(float hocPhi) {
        HocPhi = hocPhi;
    }

    public int getSoHocVien() {
        return SoHocVien;
    }

    public void setSoHocVien(int soHocVien) {
        SoHocVien = soHocVien;
    }

    public int getSoLuongMon() {
        return SoLuongMon;
    }

    public void setSoLuongMon(int soLuongMon) {
        SoLuongMon = soLuongMon;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public LoaiKhoaHoc getLoaiKhoaHoc() {
        return loaiKhoaHoc;
    }

    public void setLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc) {
        this.loaiKhoaHoc = loaiKhoaHoc;
    }
}
