package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_loaikhoahoc")
public class LoaiKhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaikhoahocid")
    private int LoaiKhoaHocId;
    @Column(name = "tenloai")
    private String TenLoai;
    @OneToMany(mappedBy = "loaiKhoaHoc")
    @JsonIgnoreProperties(value = "loaiKhoaHoc")
    Set<KhoaHoc> khoaHocs;

    public LoaiKhoaHoc() {
    }

    public LoaiKhoaHoc(String tenLoai) {
        TenLoai = tenLoai;
    }

    public int getLoaiKhoaHocId() {
        return LoaiKhoaHocId;
    }

    public void setLoaiKhoaHocId(int loaiKhoaHocId) {
        LoaiKhoaHocId = loaiKhoaHocId;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
