package com.lotus.final2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "tb_chude")
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chudeid")
    private int ChuDeID;
    @Column(name = "tenchude")
    @NotNull
    private String TenChuDe;
    @Column(name = "noidung")
    @NotNull
    private String NoiDung;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaibaivietid")
    @JsonIgnoreProperties(value = "chuDeSet")
    @NotNull
    LoaiBaiViet loaiBaiViet;
    @OneToMany(mappedBy = "chuDe")
    @JsonIgnoreProperties(value = "chuDe")
    Set<BaiViet> baiViets;

    public ChuDe() {
    }

    public ChuDe(String tenChuDe, String noiDung, LoaiBaiViet loaiBaiViet) {
        TenChuDe = tenChuDe;
        NoiDung = noiDung;
        this.loaiBaiViet = loaiBaiViet;
    }

    public int getChuDeID() {
        return ChuDeID;
    }

    public void setChuDeID(int chuDeID) {
        ChuDeID = chuDeID;
    }

    public String getTenChuDe() {
        return TenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        TenChuDe = tenChuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public LoaiBaiViet getLoaiBaiViet() {
        return loaiBaiViet;
    }

    public void setLoaiBaiViet(LoaiBaiViet loaiBaiViet) {
        this.loaiBaiViet = loaiBaiViet;
    }
}
