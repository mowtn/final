package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.BaiViet;
import com.lotus.final2.Model.ChuDe;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.ChuDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChuDeServiceImpl implements ChuDeService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<ChuDe> them(Request<ChuDe> chuDeRequest) {
        ChuDe chuDe = dbContext.chuDeRP.save(new ChuDe(
                chuDeRequest.getData().getTenChuDe(),
                chuDeRequest.getData().getNoiDung(),
                chuDeRequest.getData().getLoaiBaiViet()
        ));
        dbContext.chuDeRP.save(chuDe);
        return new Response<>("Thêm thành công",0,chuDe);
    }

    @Override
    public Response<ChuDe> sua(int chudeid, Request<ChuDe> chuDeRequest) {
        Optional<ChuDe> chuDe = dbContext.chuDeRP.findById(chudeid);
        if (chuDe.isEmpty()) return new Response<>("Chủ đề không tồn tại",1,null);
        chuDe.get().setTenChuDe(chuDeRequest.getData().getTenChuDe());
        chuDe.get().setNoiDung(chuDeRequest.getData().getNoiDung());
        chuDe.get().setLoaiBaiViet(chuDeRequest.getData().getLoaiBaiViet());
        dbContext.chuDeRP.save(chuDe.get());
        return new Response<>("Sửa thành công",0,chuDe.get());
    }

    @Override
    public Page<ChuDe> page(Pageable pageable) {
        return dbContext.chuDeRP.findAll(pageable);
    }

    @Override
    public Response<ChuDe> xoa(int id) {
        Optional<ChuDe> chuDe = dbContext.chuDeRP.findById(id);
        if (chuDe.isEmpty()) return new Response<>("chu de không tồn tại",0,null);
        for (BaiViet baiViet:dbContext.baiVietRP.findAll()){
            if (baiViet.getChuDe()==chuDe.get())
                dbContext.baiVietRP.delete(baiViet);
        }
        dbContext.chuDeRP.delete(chuDe.get());
        return new Response<>("Xóa thành công",0,null);
    }
}
