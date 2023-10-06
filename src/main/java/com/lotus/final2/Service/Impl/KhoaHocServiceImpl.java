package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.DangKyHoc;
import com.lotus.final2.Model.HocVien;
import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.KhoaHocService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocServiceImpl implements KhoaHocService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<KhoaHoc> sua(int khoahocid,Request<KhoaHoc> khoaHocRequest) {
        Optional<KhoaHoc> khoaHoc = dbContext.khoaHocRP.findById(khoahocid);
        if (khoaHoc.isEmpty()) return new Response<KhoaHoc>("Khoa hoc khong ton tai",1,null);
        BeanUtils.copyProperties(khoaHocRequest.getData(),khoaHoc.get(),"khoaHocId");
        KhoaHoc khoaHocnew = dbContext.khoaHocRP.save(khoaHoc.get());
        return new Response<>("sua thanh cong",0,khoaHocnew);
    }

    @Override
    public Response<KhoaHoc> xoa(int id) {
        Optional<KhoaHoc> khoahoc = dbContext.khoaHocRP.findById(id);
        if (khoahoc.isEmpty()) return new Response<>("Khóa học không tồn tại!",1,null);
        for (DangKyHoc dangKyHoc:dbContext.dangKyHocRP.findAll()){
            if (dangKyHoc.getKhoaHoc() == khoahoc.get()){
                dbContext.dangKyHocRP.delete(dangKyHoc);
            }
        }
        dbContext.khoaHocRP.deleteById(id);
        return new Response<>("xoa thanh cong",0,null);
    }

    @Override
    public Response<List<KhoaHoc>> searchbyKeyword(String tenkhoahoc) {
        List<KhoaHoc> list = dbContext.khoaHocRP.searchbyKeyword(tenkhoahoc);
        if (list.isEmpty()) return new Response<>("Khong co khoa hoc phu hop",0,null);
        else return new Response<>("thanh cong",0,list);
    }

    @Override
    public Page<KhoaHoc> pagekhoahoc(Pageable pageable) {
        return dbContext.khoaHocRP.findAll(pageable);
    }

    @Override
    public Response<KhoaHoc> them(int loaikhoahocid, Request<KhoaHoc> khoaHocRequest) {
        Optional<LoaiKhoaHoc> loaiKhoaHoc = dbContext.loaiKhoaHocRP.findById(loaikhoahocid);
        if (loaiKhoaHoc.isEmpty())return  new Response<KhoaHoc>("Them khong thanh cong",1,null);
        KhoaHoc khoaHoc = new KhoaHoc(
                khoaHocRequest.getData().getTenKhoaHoc(),
                khoaHocRequest.getData().getThoiGianHoc(),
                khoaHocRequest.getData().getGioiThieu(),
                khoaHocRequest.getData().getNoiDung(),
                khoaHocRequest.getData().getHocPhi(),
                khoaHocRequest.getData().getSoHocVien(),
                khoaHocRequest.getData().getSoLuongMon(),
                khoaHocRequest.getData().getHinhAnh(),
                loaiKhoaHoc.get()
        );
        dbContext.khoaHocRP.save(khoaHoc);
        return new Response<KhoaHoc>("Them thanh cong",0,khoaHoc);
    }
}
