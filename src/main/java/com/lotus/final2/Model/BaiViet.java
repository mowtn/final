package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tb_baiviet")
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baivietid")
    private int BaiVietID;
    @Column(name = "tenbaiviet")
    @NotNull
    private String TenBaiViet;
    @Column(name = "tentacgia")
    @NotNull
    private String TenTacGia;
    @Column(name = "noidung")
    @NotNull
    private String NoiDung;
    @Column(name = "noidungngan")
    @NotNull
    private String NoiDungNgan;
    @Column(name = "thoigiantao")
    @NotNull
    private LocalDate ThoiGianTao;
    @Column(name = "hinhanh")
    @NotNull
    private String HinhAnh;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chudeid")
    @JsonIgnoreProperties(value = "baiViets")
    @NotNull
    ChuDe chuDe;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taikhoanid")
    @JsonIgnoreProperties(value = "baiViets")
    @NotNull
    TaiKhoan taiKhoan;

    public BaiViet() {
    }

    public BaiViet(String tenBaiViet, String tenTacGia, String noiDung, String noiDungNgan, LocalDate thoiGianTao, String hinhAnh, ChuDe chuDe, TaiKhoan taiKhoan) {
        TenBaiViet = tenBaiViet;
        TenTacGia = tenTacGia;
        NoiDung = noiDung;
        NoiDungNgan = noiDungNgan;
        ThoiGianTao = thoiGianTao;
        HinhAnh = hinhAnh;
        this.chuDe = chuDe;
        this.taiKhoan = taiKhoan;
    }

    public int getBaiVietID() {
        return BaiVietID;
    }

    public void setBaiVietID(int baiVietID) {
        BaiVietID = baiVietID;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNoiDungNgan() {
        return NoiDungNgan;
    }

    public void setNoiDungNgan(String noiDungNgan) {
        NoiDungNgan = noiDungNgan;
    }

    public LocalDate getThoiGianTao() {
        return ThoiGianTao;
    }

    public void setThoiGianTao(LocalDate thoiGianTao) {
        ThoiGianTao = thoiGianTao;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public ChuDe getChuDe() {
        return chuDe;
    }

    public void setChuDe(ChuDe chuDe) {
        this.chuDe = chuDe;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
