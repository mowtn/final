package com.lotus.final2.Service;

import com.lotus.final2.Model.TaiKhoan;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaiKhoanService {
    Response<TaiKhoan> them(Request<TaiKhoan> taiKhoanRequest);

    Response<TaiKhoan> sua(int taikhoanid, Request<TaiKhoan> taiKhoanRequest);

    Response<TaiKhoan> xoa(int id);

    boolean isStrongPassword(String password);

    Response<List<TaiKhoan>> timkiemtheoten(String value);

    Page<TaiKhoan> page(Pageable pageable);

    Page<TaiKhoan> pageSearch(Pageable pageable, String account);
}
