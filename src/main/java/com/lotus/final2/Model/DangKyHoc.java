package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_dangkyhoc")
public class DangKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dangkyhocid")
    private int DangKyHocId;
    @Column(name = "ngaydangky")
    private LocalDate NgayDangky;
    @Column(name = "ngaybatdau")
    private LocalDate NgayBatDau;
    @Column(name = "ngayketthuc")
    private LocalDate NgayKetThuc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "khoahocid")
    @JsonIgnoreProperties(value = "dangKyHocs")
    KhoaHoc khoaHoc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hocvienid")
    @JsonIgnoreProperties(value = "dangKyHocs")
    HocVien hocVien;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tinhtranghocid")
    @JsonIgnoreProperties(value = "dangKyHocs")
    TinhTrangHoc tinhTrangHoc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taikhoanid")
    @JsonIgnoreProperties(value = "dangKyHocs")
    TaiKhoan taiKhoan;

    public DangKyHoc() {
    }

    public DangKyHoc(LocalDate ngayDangky, LocalDate ngayBatDau, LocalDate ngayKetThuc, KhoaHoc khoaHoc, HocVien hocVien, TinhTrangHoc tinhTrangHoc, TaiKhoan taiKhoan) {
        NgayDangky = ngayDangky;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        this.khoaHoc = khoaHoc;
        this.hocVien = hocVien;
        this.tinhTrangHoc = tinhTrangHoc;
        this.taiKhoan = taiKhoan;
    }

    public int getDangKyHocId() {
        return DangKyHocId;
    }

    public void setDangKyHocId(int dangKyHocId) {
        DangKyHocId = dangKyHocId;
    }

    public LocalDate getNgayDangky() {
        return NgayDangky;
    }

    public void setNgayDangky(LocalDate ngayDangky) {
        NgayDangky = ngayDangky;
    }

    public LocalDate getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public HocVien getHocVien() {
        return hocVien;
    }

    public void setHocVien(HocVien hocVien) {
        this.hocVien = hocVien;
    }

    public TinhTrangHoc getTinhTrangHoc() {
        return tinhTrangHoc;
    }

    public void setTinhTrangHoc(TinhTrangHoc tinhTrangHoc) {
        this.tinhTrangHoc = tinhTrangHoc;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
