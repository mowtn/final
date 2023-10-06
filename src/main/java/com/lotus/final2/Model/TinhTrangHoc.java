package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_tinhtranghoc")
public class TinhTrangHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tinhtranghocid")
    private int TinhTrangHocID;

    @Column(name = "tentinhtrang")
    private String TenTinhTrang;
    @OneToMany(mappedBy = "tinhTrangHoc")
    @JsonIgnoreProperties(value = "tinhTrangHoc")
    Set<DangKyHoc> dangKyHocs;

    public TinhTrangHoc() {
    }

    public TinhTrangHoc(String tenTinhTrang) {
        TenTinhTrang = tenTinhTrang;
    }

    public int getTinhTrangHocID() {
        return TinhTrangHocID;
    }

    public void setTinhTrangHocID(int tinhTrangHocID) {
        TinhTrangHocID = tinhTrangHocID;
    }

    public String getTenTinhTrang() {
        return TenTinhTrang;
    }

    public void setTenTinhTrang(String tenTinhTrang) {
        TenTinhTrang = tenTinhTrang;
    }
}
