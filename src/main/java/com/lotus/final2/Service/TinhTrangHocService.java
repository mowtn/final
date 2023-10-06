package com.lotus.final2.Service;

import com.lotus.final2.Model.TinhTrangHoc;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;

public interface TinhTrangHocService {
    Response<TinhTrangHoc> them(Request<TinhTrangHoc> tinhTrangHocRequest);

    Response<TinhTrangHoc> sua(int tinhtranghocid, Request<TinhTrangHoc> tinhTrangHocRequest);

    Response<TinhTrangHoc> xoa(int id);
}
