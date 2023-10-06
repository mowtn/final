package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_taikhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taikhoanid")
    private int TaiKhoanId;
    @Column(name = "tennguoidung")
    private String TenNguoiDung;
    @Column(name = "taikhoan")
    private String TaiKhoan;
    @Column(name = "matkhau")
    private String MatKhau;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quyenhanid")
    @JsonIgnoreProperties(value = "taiKhoans")
    QuyenHan quyenHan;
    @OneToMany(mappedBy = "taiKhoan")
    @JsonIgnoreProperties(value = "taiKhoan")
    Set<BaiViet> baiViets;
    @OneToMany(mappedBy = "taiKhoan")
    @JsonIgnoreProperties(value = "taiKhoan")
    Set<DangKyHoc> dangKyHocs;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenNguoiDung, String taiKhoan, String matKhau, QuyenHan quyenHan) {
        TenNguoiDung = tenNguoiDung;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        this.quyenHan = quyenHan;
    }

    public int getTaiKhoanId() {
        return TaiKhoanId;
    }

    public void setTaiKhoanId(int taiKhoanId) {
        TaiKhoanId = taiKhoanId;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public QuyenHan getQuyenHan() {
        return quyenHan;
    }

    public void setQuyenHan(QuyenHan quyenHan) {
        this.quyenHan = quyenHan;
    }
}
