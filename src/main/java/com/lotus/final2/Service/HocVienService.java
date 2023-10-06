package com.lotus.final2.Service;

import com.lotus.final2.Model.HocVien;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HocVienService {
    Response<HocVien> them(Request<HocVien> hocVienRequest);

    String chuanHoaHoTen(String hoTen);

    HocVien save(HocVien hocVien);

    Response<HocVien> sua(int id, Request<HocVien> hocVienRequest);

    Response<HocVien> xoa(int id);

    Response<List<HocVien>> getlist(String text);
    Page<HocVien> pagehocvien(Pageable pageable);
}
