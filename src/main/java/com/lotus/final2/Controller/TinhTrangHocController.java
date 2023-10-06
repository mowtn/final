package com.lotus.final2.Controller;

import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Model.TinhTrangHoc;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.TinhTrangHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tinhtrang")
public class TinhTrangHocController {
    @Autowired
    TinhTrangHocService tinhTrangHocService;
    @PostMapping("them")
    public Response<TinhTrangHoc> them(@RequestBody Request<TinhTrangHoc> tinhTrangHocRequest){
        return tinhTrangHocService.them(tinhTrangHocRequest);
    }
    @PutMapping("sua")
    public Response<TinhTrangHoc> sua(@RequestParam(name = "tinhtranghocid")int tinhtranghocid,@RequestBody Request<TinhTrangHoc> tinhTrangHocRequest){
        return tinhTrangHocService.sua(tinhtranghocid,tinhTrangHocRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<TinhTrangHoc> xoa(@PathVariable int id){
        return tinhTrangHocService.xoa(id);
    }
}
