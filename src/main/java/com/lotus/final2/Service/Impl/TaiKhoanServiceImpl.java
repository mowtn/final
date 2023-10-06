package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.BaiViet;
import com.lotus.final2.Model.DangKyHoc;
import com.lotus.final2.Model.TaiKhoan;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<TaiKhoan> them(Request<TaiKhoan> taiKhoanRequest) {
        for (TaiKhoan taiKhoan:dbContext.taiKhoanRP.findAll()){
            if (taiKhoan.getTaiKhoan().equals(taiKhoanRequest.getData().getTaiKhoan()))
                return new Response<>("Tài khoản đã tồn tại",1,null);
            if (isStrongPassword(taiKhoanRequest.getData().getMatKhau())==false)
                return new Response<>("Mật khẩu không đủ mạnh",1,null);
        }

        TaiKhoan taiKhoan = dbContext.taiKhoanRP.save(new TaiKhoan(
                taiKhoanRequest.getData().getTenNguoiDung(),
                taiKhoanRequest.getData().getTaiKhoan(),
                taiKhoanRequest.getData().getMatKhau(),
                taiKhoanRequest.getData().getQuyenHan()
        ));
        return new Response<>("Thêm thành công",0,taiKhoan);
    }

    @Override
    public Response<TaiKhoan> sua(int taikhoanid, Request<TaiKhoan> taiKhoanRequest) {
        Optional<TaiKhoan> taiKhoan = dbContext.taiKhoanRP.findById(taikhoanid);
        if (taiKhoan.isEmpty()) return new Response<>("tài khoản không tồn tại",1,null);
        if (isStrongPassword(taiKhoanRequest.getData().getMatKhau())==false) return new Response<>("Mật khẩu không đủ mạnh",1,null);
        for (TaiKhoan account: dbContext.taiKhoanRP.findAll()){
            if (account!=taiKhoan.get()){
                if (account.getTaiKhoan().equals(taiKhoanRequest.getData().getTaiKhoan()))
                    return new Response<>("tên tài khoản đã tồn tại!",1,null);
            }
        }
        taiKhoan.get().setTaiKhoan(taiKhoanRequest.getData().getTaiKhoan());
        taiKhoan.get().setMatKhau(taiKhoanRequest.getData().getMatKhau());
        taiKhoan.get().setTenNguoiDung(taiKhoanRequest.getData().getTenNguoiDung());
        taiKhoan.get().setQuyenHan(taiKhoanRequest.getData().getQuyenHan());
        dbContext.taiKhoanRP.save(taiKhoan.get());
        return new Response<>("Cập nhật thành công",0,taiKhoan.get());
    }

    @Override
    public Response<TaiKhoan> xoa(int id) {
        Optional<TaiKhoan> taiKhoan= dbContext.taiKhoanRP.findById(id);
        if (taiKhoan.isEmpty()) return new Response<>("tài khoản này không tồn tại",1,null);
        for (BaiViet baiViet:dbContext.baiVietRP.findAll()){
            if (baiViet.getTaiKhoan()==taiKhoan.get())
                dbContext.baiVietRP.delete(baiViet);
        }
        for (DangKyHoc dangKyHoc:dbContext.dangKyHocRP.findAll()){
            if (dangKyHoc.getTaiKhoan()==taiKhoan.get())
                dangKyHoc.setTaiKhoan(null);
            dbContext.dangKyHocRP.save(dangKyHoc);
        }
        dbContext.taiKhoanRP.delete(taiKhoan.get());
        return new Response<>("xóa thành công",0,taiKhoan.get());
    }

    @Override
    public  boolean isStrongPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    @Override
    public Response<List<TaiKhoan>> timkiemtheoten(String value) {
         List<TaiKhoan> list = dbContext.taiKhoanRP.findbyname(value);
         return new Response<>("tìm thành công",0,list);
    }

    @Override
    public Page<TaiKhoan> page(Pageable pageable) {
        return dbContext.taiKhoanRP.findAll(pageable);
    }

    @Override
    public Page<TaiKhoan> pageSearch(Pageable pageable, String account) {
        return dbContext.taiKhoanRP.searchByUsername(pageable,account);
    }
}
