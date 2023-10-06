package com.lotus.final2.Service;

import com.lotus.final2.Model.QuyenHan;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuyenHanService {
    Response<QuyenHan> them(Request<QuyenHan> quyenHanRequest);

    Response<QuyenHan> sua(int quyenhanid, Request<QuyenHan> quyenHanRequest);

    Response<QuyenHan> xoa(int id);

    Page<QuyenHan> page(Pageable pageable);
}
