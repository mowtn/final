package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.LoaiKhoaHocService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoaiKhoaHocServiceImpl implements LoaiKhoaHocService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<LoaiKhoaHoc> them(Request<LoaiKhoaHoc> loaiKhoaHocRequest) {
        LoaiKhoaHoc loaiKhoaHoc = dbContext.loaiKhoaHocRP.save(new LoaiKhoaHoc(loaiKhoaHocRequest.getData().getTenLoai()));
        return new Response<LoaiKhoaHoc>("thanh cong",0,loaiKhoaHoc);
    }

    @Override
    public Response<LoaiKhoaHoc> sua(int loaikhoahocid, Request<LoaiKhoaHoc> loaiKhoaHocRequest) {
        Optional<LoaiKhoaHoc> loaiKhoaHoc = dbContext.loaiKhoaHocRP.findById(loaikhoahocid);
        if (loaiKhoaHoc.isEmpty()) return new Response<LoaiKhoaHoc>("Loai khóa học không tồn tại",1,null);
        loaiKhoaHoc.get().setTenLoai(loaiKhoaHocRequest.getData().getTenLoai());
        dbContext.loaiKhoaHocRP.save(loaiKhoaHoc.get());
        return new Response<>("thanh cong",0,loaiKhoaHoc.get());
    }

    @Override
    public Response<LoaiKhoaHoc> xoa(int id) {
        Optional<LoaiKhoaHoc> loaiKhoaHoc = dbContext.loaiKhoaHocRP.findById(id);
        if (loaiKhoaHoc.isEmpty()) return new Response<>("Loại khóa học không tồn tại",1,null);
        for (KhoaHoc khoaHoc:dbContext.khoaHocRP.findAll()){
            if (khoaHoc.getLoaiKhoaHoc()==loaiKhoaHoc.get()){
                khoaHoc.setLoaiKhoaHoc(null);
                dbContext.khoaHocRP.save(khoaHoc);
            }
        }
        dbContext.loaiKhoaHocRP.deleteById(id);
        return new Response<>("xoa thanh cong",0,null);
    }
}
