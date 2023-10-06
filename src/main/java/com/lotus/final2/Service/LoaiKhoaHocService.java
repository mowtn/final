package com.lotus.final2.Service;

import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;

public interface LoaiKhoaHocService {

    Response<LoaiKhoaHoc> them(Request<LoaiKhoaHoc> loaiKhoaHocRequest);

    Response<LoaiKhoaHoc> sua(int loaikhoahocid, Request<LoaiKhoaHoc> loaiKhoaHocRequest);

    Response<LoaiKhoaHoc> xoa(int id);
}
