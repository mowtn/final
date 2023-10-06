package com.lotus.final2.Service.Impl;

import com.lotus.final2.Model.*;
import com.lotus.final2.Repository.DbContext.DbContext;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.DangKyHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DangKyHocServiceImpl implements DangKyHocService {
    @Autowired
    DbContext dbContext;
    @Override
    public Response<DangKyHoc> dangkyhoc(int taikhoanid,int hocvienid, int khoahocid) {
        Optional<HocVien> hocVien = dbContext.hocVienRP.findById(hocvienid);
        if (hocVien.isEmpty()) return new Response<>("Hoc Vien khong ton tai",1,null);
        Optional<KhoaHoc> khoahoc = dbContext.khoaHocRP.findById(khoahocid);
        if (khoahoc.isEmpty()) return new Response<>("Khoa hoc khong ton tai",1,null);
        Optional<TaiKhoan> taikhoan = dbContext.taiKhoanRP.findById(khoahocid);
        if (taikhoan.isEmpty()) return new Response<>("tai khoan khong ton tai",1,null);

        for (DangKyHoc dangKyHoc:dbContext.dangKyHocRP.findAll()){
            if (dangKyHoc.getKhoaHoc()==khoahoc.get()&&dangKyHoc.getHocVien()==hocVien.get())
                return new Response<>("Học viên đã đăng kí khóa học này!",1,null);
        }

        TinhTrangHoc tinhTrangHoc = new TinhTrangHoc();
        boolean flag = false;
        for (TinhTrangHoc status: dbContext.tinhTrangHocRP.findAll()){
            if (status.getTenTinhTrang().trim().equals("Chờ duyệt")){
                tinhTrangHoc = status;
                flag = true;
            }
        }
        if (!flag) return new Response<>("Không có tình trạng học chờ duyệt",1,null);
        DangKyHoc dangKyHoc = new DangKyHoc(
                LocalDate.now(),
                null,
                null,
                khoahoc.get(),
                hocVien.get(),
                tinhTrangHoc,
                taikhoan.get());
        dbContext.dangKyHocRP.save(dangKyHoc);
        int updateStudentcount = dbContext.dangKyHocRP.getQuantityStudentByCourseID(khoahocid);
        khoahoc.get().setSoHocVien(updateStudentcount);
        dbContext.khoaHocRP.save(khoahoc.get());
        return new Response<>("Dang ky thanh cong!",0,dangKyHoc);
    }

    @Override
    public Response<DangKyHoc> capnhattrangthai(int dangkyhocid, int tinhtranghocid, int taikhoanid) {
        Optional<TaiKhoan> taiKhoan = dbContext.taiKhoanRP.findById(taikhoanid);
        Optional<TinhTrangHoc> tinhTrangHoc = dbContext.tinhTrangHocRP.findById(tinhtranghocid);
        if (tinhTrangHoc.isEmpty()) return new Response<>("tình trạng học không tồn tại",1,null);
        if (taiKhoan.isEmpty()){
            return new Response<>("tài khoản không tồn tại!",1,null);
        }else {
            if (taiKhoan.get().getQuyenHan().getTenQuyenHan().trim().toLowerCase().equals("admin")||
                    taiKhoan.get().getQuyenHan().getTenQuyenHan().trim().toLowerCase().equals("quản lý đào tạo")){
                for (DangKyHoc dangKyHoc:dbContext.dangKyHocRP.findAll()){
                    if (dangKyHoc.getDangKyHocId()==dangkyhocid){
                        if (tinhTrangHoc.get().getTenTinhTrang().trim().equals("Đang học")){
                            dangKyHoc.setNgayBatDau(LocalDate.now());
                            LocalDate ngayketthuc = LocalDate.now().plusDays(dangKyHoc.getKhoaHoc().getThoiGianHoc());
                            dangKyHoc.setNgayKetThuc(ngayketthuc);
                        }
                        dangKyHoc.setTinhTrangHoc(tinhTrangHoc.get());
                        dbContext.dangKyHocRP.save(dangKyHoc);
                        return new Response<>("Cập nhật tình trạng thành công!",0,dangKyHoc);
                    }
                }
            }else return new Response<>("không có quyền chỉnh sửa!",1,null);
        }
        return new Response<>("không  thành công!",1,null);
    }

    @Override
    public Response<DangKyHoc> sua(int dangkyhocid, int khoahocid) {
        Optional<DangKyHoc> dangKyHoc = dbContext.dangKyHocRP.findById(dangkyhocid);
        if (dangKyHoc.isEmpty()) return  new Response<>("Phiếu đăng ký không tồn tại",1,null);
        Optional<KhoaHoc> Khoahoc = dbContext.khoaHocRP.findById(khoahocid);
        if (Khoahoc.isEmpty()) return  new Response<>("Khóa học không tồn tại",1,null);
        int courseBefore = dangKyHoc.get().getKhoaHoc().getKhoaHocId();
        //set new course
        dangKyHoc.get().setKhoaHoc(Khoahoc.get());
        dbContext.dangKyHocRP.save(dangKyHoc.get());
        //update quantity student of new course
        Integer updateStudentcountNew = dbContext.dangKyHocRP.getQuantityStudentByCourseID(khoahocid);
        if (updateStudentcountNew == null) {
            updateStudentcountNew = 0;
        }
        Khoahoc.get().setSoHocVien(updateStudentcountNew);
        dbContext.khoaHocRP.save(Khoahoc.get());
        //update quantity student of last course
        Integer quantityStudent = dbContext.dangKyHocRP.getQuantityStudentByCourseID(courseBefore);
        if (quantityStudent == null) {
            quantityStudent = 0;
        }
        Optional<KhoaHoc> kh = dbContext.khoaHocRP.findById(courseBefore);
        kh.get().setSoHocVien(quantityStudent);
        dbContext.khoaHocRP.save(kh.get());
        //return
        return new Response<>("Cập nhật thành công",0,dangKyHoc.get());
    }

    @Override
    public Response<DangKyHoc> xoa(int id) {
       try {
           Optional<DangKyHoc> dangKyHoc = dbContext.dangKyHocRP.findById(id);
           if (dangKyHoc.isEmpty()) return new Response<>("phiếu đăng ký học không tồn tại",1,null);
           KhoaHoc khoaHoc = dangKyHoc.get().getKhoaHoc();
           dbContext.dangKyHocRP.deleteById(id);
           int updateCount = dbContext.dangKyHocRP.getQuantityStudentByCourseID(khoaHoc.getKhoaHocId());
           khoaHoc.setSoHocVien(updateCount);
           dbContext.khoaHocRP.save(khoaHoc);
           return new Response<>("xóa thành công!",0,null);
       }catch (Exception ex){
           return new Response<>("xóa thất bại!",1,null);
       }
    }

    @Override
    public Page<DangKyHoc> getPage(Pageable pageable) {
        return dbContext.dangKyHocRP.findAll(pageable);
    }
}
