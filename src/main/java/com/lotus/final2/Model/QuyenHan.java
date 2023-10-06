package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_quyenhan")
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quyenhanid")
    private int QuyenHanID;
    @Column(name = "tenquyenhan")
    private String TenQuyenHan;
    @OneToMany(mappedBy = "quyenHan")
    @JsonIgnoreProperties(value = "quyenHan")
    Set<TaiKhoan> taiKhoans;

    public QuyenHan() {
    }

    public QuyenHan(String tenQuyenHan) {
        TenQuyenHan = tenQuyenHan;
    }

    public int getQuyenHanID() {
        return QuyenHanID;
    }

    public void setQuyenHanID(int quyenHanID) {
        QuyenHanID = quyenHanID;
    }

    public String getTenQuyenHan() {
        return TenQuyenHan;
    }

    public void setTenQuyenHan(String tenQuyenHan) {
        TenQuyenHan = tenQuyenHan;
    }
}
