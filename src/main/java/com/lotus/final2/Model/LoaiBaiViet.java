package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_loaibaiviet")
public class LoaiBaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaibaivietid")
    private int LoaiBaiVietID;
    @Column(name = "tenloai")
    private String TenLoai;
    @OneToMany(mappedBy = "loaiBaiViet")
    @JsonIgnoreProperties(value = "loaiBaiViet")
    Set<ChuDe> chuDeSet;

    public LoaiBaiViet() {
    }

    public LoaiBaiViet(String tenLoai) {
        TenLoai = tenLoai;
    }

    public int getLoaiBaiVietID() {
        return LoaiBaiVietID;
    }

    public void setLoaiBaiVietID(int loaiBaiVietID) {
        LoaiBaiVietID = loaiBaiVietID;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
