package com.lotus.final2.Repository.DbContext;

import com.lotus.final2.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbContext {
    @Autowired
    public BaiVietRP baiVietRP;
    @Autowired
    public ChuDeRP chuDeRP;
    @Autowired
    public DangKyHocRP dangKyHocRP;
    @Autowired
    public HocVienRP hocVienRP;
    @Autowired
    public KhoaHocRP khoaHocRP;
    @Autowired
    public LoaiBaiVietRP loaiBaiVietRP;
    @Autowired
    public LoaiKhoaHocRP loaiKhoaHocRP;
    @Autowired
    public QuyenHanRP quyenHanRP;
    @Autowired
    public TaiKhoanRP taiKhoanRP;
    @Autowired
    public TinhTrangHocRP tinhTrangHocRP;
}
