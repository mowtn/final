package com.lotus.final2.Service;

import com.lotus.final2.Model.LoaiBaiViet;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoaiBaiVietService {
    Response<LoaiBaiViet> them(Request<LoaiBaiViet> loaiBaiVietRequest);

    Response<LoaiBaiViet> sua(int loaibaivietid, Request<LoaiBaiViet> loaiBaiVietRequest);

    Page<LoaiBaiViet> page(Pageable pageable);

    Response<LoaiBaiViet> xoa(int id);
}
