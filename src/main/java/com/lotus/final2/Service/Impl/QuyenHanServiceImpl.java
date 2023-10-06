package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.QuyenHan;
import com.lotus.final2.Model.TaiKhoan;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.QuyenHanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuyenHanServiceImpl implements QuyenHanService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<QuyenHan> them(Request<QuyenHan> quyenHanRequest) {
        QuyenHan quyenHan = dbContext.quyenHanRP.save(new QuyenHan(quyenHanRequest.getData().getTenQuyenHan()));
        return new Response<>("Thêm thành công",0,quyenHan);
    }

    @Override
    public Response<QuyenHan> sua(int quyenhanid, Request<QuyenHan> quyenHanRequest) {
        Optional<QuyenHan> quyenHan = dbContext.quyenHanRP.findById(quyenhanid);
        if (quyenHan.isEmpty()) return new Response<>("Quyền hạn không tồn tại",1,null);
        quyenHan.get().setTenQuyenHan(quyenHanRequest.getData().getTenQuyenHan());
        dbContext.quyenHanRP.save(quyenHan.get());
        return new Response<>("Sửa thành công",0,null);
    }

    @Override
    public Response<QuyenHan> xoa(int id) {
        Optional<QuyenHan> quyenHan = dbContext.quyenHanRP.findById(id);
        if (quyenHan.isEmpty()) return new Response<>("Quyền hạn không tồn tại",1,null);
        for (TaiKhoan taiKhoan:dbContext.taiKhoanRP.findAll()){
            if (taiKhoan.getQuyenHan()==quyenHan.get())
                taiKhoan.setQuyenHan(null);
        }
        dbContext.quyenHanRP.delete(quyenHan.get());
        return new Response<>("Xóa thành công",1,null);
    }
    @Override
    public Page<QuyenHan> page(Pageable pageable) {
        return dbContext.quyenHanRP.findAll(pageable);
    }
}
