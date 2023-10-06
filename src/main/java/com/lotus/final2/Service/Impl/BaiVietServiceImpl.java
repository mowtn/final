package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.BaiViet;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.BaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BaiVietServiceImpl implements BaiVietService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<BaiViet> them(Request<BaiViet> baiVietRequest) {
        String image = ".image/baiviet"+baiVietRequest.getData().getHinhAnh();
        LocalDate thoigiantao = LocalDate.now();
        BaiViet baiViet = dbContext.baiVietRP.save(new BaiViet(
                baiVietRequest.getData().getTenBaiViet(),
                baiVietRequest.getData().getTenTacGia(),
                baiVietRequest.getData().getNoiDung(),
                baiVietRequest.getData().getNoiDungNgan(),
                thoigiantao,
                image,
                baiVietRequest.getData().getChuDe(),
                baiVietRequest.getData().getTaiKhoan()
        ));
        return new Response<>("Thêm thành công",0,baiViet);
    }

    @Override
    public Response<BaiViet> sua(int baivietid, Request<BaiViet> baiVietRequest) {
        Optional<BaiViet> baiViet = dbContext.baiVietRP.findById(baivietid);
        if (baiViet.isEmpty()) return new Response<>("Bài viết không tồn tại",1,null);
        String image = ".image/baiviet"+baiVietRequest.getData().getHinhAnh();
        baiViet.get().setTenBaiViet(baiVietRequest.getData().getTenBaiViet());
        baiViet.get().setTenTacGia(baiVietRequest.getData().getTenTacGia());
        baiViet.get().setNoiDung(baiVietRequest.getData().getNoiDung());
        baiViet.get().setNoiDungNgan(baiVietRequest.getData().getNoiDungNgan());
        baiViet.get().setHinhAnh(image);
        baiViet.get().setChuDe(baiVietRequest.getData().getChuDe());
        baiViet.get().setTaiKhoan(baiVietRequest.getData().getTaiKhoan());
        dbContext.baiVietRP.save(baiViet.get());
        return new Response<>("Sửa thành công",0,baiViet.get());
    }

    @Override
    public Response<BaiViet> xoa(int id) {
        Optional<BaiViet> baiViet = dbContext.baiVietRP.findById(id);
        if (baiViet.isEmpty()) return new Response<>("Bài viết không tồn tại",1,null);
        dbContext.baiVietRP.delete(baiViet.get());
        return new Response<>("xóa thành công",0,null);
    }

    @Override
    public Page<BaiViet> searchByName(Pageable pageable, String title) {
        return dbContext.baiVietRP.searchByTitle(pageable,title);
    }
}
