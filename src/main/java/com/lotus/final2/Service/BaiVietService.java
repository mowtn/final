package com.lotus.final2.Service;

import com.lotus.final2.Model.BaiViet;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaiVietService {
    Response<BaiViet> them(Request<BaiViet> baiVietRequest);

    Response<BaiViet> sua(int baivietid, Request<BaiViet> baiVietRequest);

    Response<BaiViet> xoa(int id);

    Page<BaiViet> searchByName(Pageable pageable,String title);
}
