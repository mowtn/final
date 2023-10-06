package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.BaiViet;
import com.lotus.final2.Model.ChuDe;
import com.lotus.final2.Model.LoaiBaiViet;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.LoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoaibaiVietServiceImpl implements LoaiBaiVietService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<LoaiBaiViet> them(Request<LoaiBaiViet> loaiBaiVietRequest) {
        LoaiBaiViet loaiBaiViet = dbContext.loaiBaiVietRP.save(new LoaiBaiViet(loaiBaiVietRequest.getData().getTenLoai()));
        dbContext.loaiBaiVietRP.save(loaiBaiViet);
        return new Response<>("Thêm thành công",0,loaiBaiViet);
    }

    @Override
    public Response<LoaiBaiViet> sua(int loaibaivietid, Request<LoaiBaiViet> loaiBaiVietRequest) {
        Optional<LoaiBaiViet> loaiBaiViet = dbContext.loaiBaiVietRP.findById(loaibaivietid);
        if(loaiBaiViet.isEmpty()) return new Response<>("Loại bài viết không tồn tại",1,null);
        loaiBaiViet.get().setTenLoai(loaiBaiVietRequest.getData().getTenLoai());
        dbContext.loaiBaiVietRP.save(loaiBaiViet.get());
        return new Response<>("Sửa thành công",0, loaiBaiViet.get());
    }

    @Override
    public Page<LoaiBaiViet> page(Pageable pageable) {
        return dbContext.loaiBaiVietRP.findAll(pageable);
    }

    @Override
    public Response<LoaiBaiViet> xoa(int id) {
        Optional<LoaiBaiViet> loaiBaiViet = dbContext.loaiBaiVietRP.findById(id);
        if(loaiBaiViet.isEmpty())return new Response<>("Loại bài viết không tồn tại",1,loaiBaiViet.get());
        for (ChuDe chuDe :dbContext.chuDeRP.findAll()){
            if (chuDe.getLoaiBaiViet()==loaiBaiViet.get()){
                for (BaiViet baiViet:dbContext.baiVietRP.findAll()){
                    if (baiViet.getChuDe()==chuDe){
                        dbContext.baiVietRP.delete(baiViet);
                        dbContext.chuDeRP.delete(chuDe);
                    }
                }
            }

        }
        dbContext.loaiBaiVietRP.delete(loaiBaiViet.get());
        return new Response<>("Xóa thành công",0,loaiBaiViet.get());
    }
}
