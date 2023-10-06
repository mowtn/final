package com.lotus.final2.Service;

import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KhoaHocService {
    Response<KhoaHoc> them(int loaikhoahoc, Request<KhoaHoc> khoaHocRequest);


    Response<KhoaHoc> sua(int khoahocid, Request<KhoaHoc> khoaHocRequest);

    Response<KhoaHoc> xoa(int id);

    Response<List<KhoaHoc>> searchbyKeyword(String tenkhoahoc);
    Page<KhoaHoc> pagekhoahoc(Pageable pageable);
}
