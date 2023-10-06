package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.DangKyHoc;
import com.lotus.final2.Model.HocVien;
import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.HocVienService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HocVienServiceImpl implements HocVienService {
    @Autowired
    DbContext dbContext;

    @Override
    public Response<HocVien> them(Request<HocVien> hocVienRequest) {
        HocVien hocVien = new HocVien();
        List<HocVien> list = new ArrayList<>();
        list = dbContext.hocVienRP.findByEmail(hocVienRequest.getData().getEmail());
        if (!list.isEmpty()) return new Response<>("Thêm thất bại! Email đã tồn tại!",1,null);
        list = dbContext.hocVienRP.findByPhone(hocVienRequest.getData().getSoDienThoai());
        if (!list.isEmpty()) return new Response<>("Thêm thất bại! số điện thoại đã tồn tại!",1,null);
        String hoten = chuanHoaHoTen(hocVienRequest.getData().getHoTen());
        String image = ".image/hocvien/"+hocVien.getHinhAnh();
        BeanUtils.copyProperties(hocVienRequest.getData(),hocVien,"hocVienId");
        hocVien.setHoTen(hoten);
        hocVien.setHinhAnh(image);
        dbContext.hocVienRP.save(hocVien);
        return new Response<HocVien>("Thêm thành công",0,hocVien);
    }
    @Override
    public  String chuanHoaHoTen(String hoTen) {
        hoTen = hoTen.toLowerCase();
        char[] arr = hoTen.toCharArray();
        boolean capiTieu = true;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isLetter(arr[i])) {
                if (capiTieu) {
                    arr[i] = Character.toUpperCase(arr[i]);
                    capiTieu = false;
                }
            } else {
                capiTieu = true;
            }
        }
        return new String(arr);
    }
    @Override
    public HocVien save(HocVien entity) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<HocVien>> violations = validator.validate(entity);
        violations.forEach(x->{
            System.out.println(x.getMessage());
        });
        if (violations.size()==0) return dbContext.hocVienRP.save(entity);
        else return null;
    }
    @Override
    public Response<HocVien> sua(int id, Request<HocVien> hocVienRequest) {
        Optional<HocVien> hocVien = dbContext.hocVienRP.findById(id);
        if (hocVien.isEmpty()) return new Response<>("Học viên không tồn tại",1,null);
        for (HocVien hocVien1: dbContext.hocVienRP.findAll()){
            if (hocVien1.getHocVienID()!=hocVienRequest.getData().getHocVienID()){
                if (hocVien1.getEmail()==hocVienRequest.getData().getEmail()||
                    hocVien1.getSoDienThoai()==hocVienRequest.getData().getSoDienThoai()){
                    return new Response<>("số điện thoại hoặc email đã tồn tại!",1,null);
                }
            }
        }
        hocVien.get().setHoTen(hocVienRequest.getData().getHoTen());
        hocVien.get().setEmail(hocVienRequest.getData().getEmail());
        hocVien.get().setHinhAnh(hocVienRequest.getData().getHinhAnh());
        hocVien.get().setPhuongXa(hocVienRequest.getData().getPhuongXa());
        hocVien.get().setTinhThanh(hocVienRequest.getData().getTinhThanh());
        hocVien.get().setQuanHuyen(hocVienRequest.getData().getQuanHuyen());
        hocVien.get().setSoDienThoai(hocVienRequest.getData().getSoDienThoai());
        hocVien.get().setSoNha(hocVienRequest.getData().getSoNha());
        dbContext.hocVienRP.save(hocVien.get());
        return new Response<>("Sửa thành công",0,hocVien.get());
    }

    @Override
    public Response<HocVien> xoa(int id) {
        Optional<HocVien> hocVien = dbContext.hocVienRP.findById(id);
        if (hocVien.isEmpty()) return new Response<HocVien>("Xóa không thành công! Học viên không tồn tại",1,null);
        for (DangKyHoc dangKyHoc:dbContext.dangKyHocRP.findAll()){
            if (dangKyHoc.getHocVien()==hocVien.get()){
                dbContext.dangKyHocRP.delete(dangKyHoc);
                KhoaHoc khoaHoc = dangKyHoc.getKhoaHoc();
                int updateCount = dbContext.dangKyHocRP.getQuantityStudentByCourseID(khoaHoc.getKhoaHocId());
                khoaHoc.setSoHocVien(updateCount);
                dbContext.khoaHocRP.save(khoaHoc);
            }
        }
        dbContext.hocVienRP.delete(hocVien.get());
        return new Response<HocVien>("Xóa thành công!",0,null);
    }

    @Override
    public Response<List<HocVien>> getlist(String text) {
        List<HocVien> hocViens =dbContext.hocVienRP.findbyPhoneOrEmail(text);
        if (hocViens.isEmpty()) return new Response<>("Không có giá trị phù hợp",1,null);
        else return new Response<List<HocVien>>("Tìm thành công",0,hocViens);
    }

    @Override
    public Page<HocVien> pagehocvien(Pageable pageable) {
        return dbContext.hocVienRP.findAll(pageable);
    }

}
