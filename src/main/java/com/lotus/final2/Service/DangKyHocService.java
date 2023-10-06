package com.lotus.final2.Service;

import com.lotus.final2.Model.DangKyHoc;
import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DangKyHocService {
    Response<DangKyHoc> dangkyhoc(int taikhoanid,int hocvienid, int khoahocid);

    Response<DangKyHoc> capnhattrangthai(int dangkyhocid, int tinhtranghocid, int taikhoanid);

    Response<DangKyHoc> sua(int dangkyhocid, int khoahoc);

    Response<DangKyHoc> xoa(int id);

    Page<DangKyHoc> getPage(Pageable pageable);
}
