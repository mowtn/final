package com.lotus.final2.Service;

import com.lotus.final2.Model.ChuDe;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChuDeService {
    Response<ChuDe> them(Request<ChuDe> chuDeRequest);

    Response<ChuDe> sua(int chudeid, Request<ChuDe> chuDeRequest);

    Page<ChuDe> page(Pageable pageable);

    Response<ChuDe> xoa(int id);
}
