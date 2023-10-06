package com.lotus.final2.Controller;

import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.LoaiKhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loaikhoahoc")
public class LoaiKhoaHocController {
    @Autowired
    LoaiKhoaHocService loaiKhoaHocService;
    @PostMapping("them")
    public Response<LoaiKhoaHoc> them(@RequestBody Request<LoaiKhoaHoc> loaiKhoaHocRequest){
        return loaiKhoaHocService.them(loaiKhoaHocRequest);
    }
    @PutMapping("sua")
    public Response<LoaiKhoaHoc> sua(@RequestParam(name = "loaikhoahocid")int loaikhoahocid,@RequestBody Request<LoaiKhoaHoc> loaiKhoaHocRequest){
        return loaiKhoaHocService.sua(loaikhoahocid,loaiKhoaHocRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<LoaiKhoaHoc> xoa(@PathVariable int id){
        return loaiKhoaHocService.xoa(id);
    }

}
