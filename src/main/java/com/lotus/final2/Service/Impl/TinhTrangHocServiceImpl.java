package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.DangKyHoc;
import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Model.TinhTrangHoc;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.TinhTrangHocService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TinhTrangHocServiceImpl implements TinhTrangHocService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<TinhTrangHoc> them(Request<TinhTrangHoc> tinhTrangHocRequest) {
        TinhTrangHoc tinhTrangHoc = dbContext.tinhTrangHocRP.save(new TinhTrangHoc(tinhTrangHocRequest.getData().getTenTinhTrang()));
        return new Response<TinhTrangHoc>("thanh cong",0,tinhTrangHoc);
    }

    @Override
    public Response<TinhTrangHoc> sua(int tinhtranghocid, Request<TinhTrangHoc> tinhTrangHocRequest) {
        Optional<TinhTrangHoc> tinhTrangHoc = dbContext.tinhTrangHocRP.findById(tinhtranghocid);
        if (tinhTrangHoc.isEmpty()) return new Response<TinhTrangHoc>(" tình trạng học không tồn tại",1,null);
        tinhTrangHoc.get().setTenTinhTrang(tinhTrangHocRequest.getData().getTenTinhTrang());
        dbContext.tinhTrangHocRP.save(tinhTrangHoc.get());
        return new Response<>("thanh cong",0,tinhTrangHoc.get());
    }

    @Override
    public Response<TinhTrangHoc> xoa(int id) {
        Optional<TinhTrangHoc> tinhTrangHoc = dbContext.tinhTrangHocRP.findById(id);
        if (tinhTrangHoc.isEmpty()) return new Response<TinhTrangHoc>(" tình trạng học không tồn tại",1,null);
        for (DangKyHoc dangKyHoc:dbContext.dangKyHocRP.findAll()){
            if (dangKyHoc.getTinhTrangHoc()==tinhTrangHoc.get()){
                dangKyHoc.setTinhTrangHoc(null);
                dbContext.dangKyHocRP.save(dangKyHoc);
            }
        }
        dbContext.tinhTrangHocRP.delete(tinhTrangHoc.get());
        return new Response<TinhTrangHoc>("Xóa thành công",0,null);
    }
}
