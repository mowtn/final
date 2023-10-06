package com.lotus.final2.Controller;

import com.lotus.final2.Model.DangKyHoc;
import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.DangKyHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dangkyhoc")
public class DangKyHocController {
    @Autowired
    DangKyHocService dangKyHocService;


    @PostMapping("dangky/{taikhoanid}")
    public Response<DangKyHoc> dangkyhoc(@PathVariable(name = "taikhoanid") int taikhoanid,
                                         @RequestParam(name = "hocvienid")int hocvienid,
                                         @RequestParam(name = "khoahocid")int khoahocid){
        return dangKyHocService.dangkyhoc(taikhoanid,hocvienid,khoahocid);
    }
    @GetMapping("capnhattrangthai/{dangkyhocid}")
    public Response<DangKyHoc> capnhat(@PathVariable int dangkyhocid,
                                       @RequestParam(name = "tinhtranghocid")int tinhtranghocid,
                                       @RequestParam(name = "taikhoanid")int taikhoanid){
        return dangKyHocService.capnhattrangthai(dangkyhocid,tinhtranghocid,taikhoanid);
    }
    @PutMapping("sua/{dangkyhocid}")
    public Response<DangKyHoc> sua(@PathVariable int dangkyhocid,@RequestParam(name = "khoahocid")int khoahocid){
        return dangKyHocService.sua(dangkyhocid,khoahocid);
    }
    @DeleteMapping("xoa/{id}")
    public Response<DangKyHoc> xoa(@PathVariable int id){
        return dangKyHocService.xoa(id);
    }
    @GetMapping("page")
    public Page<DangKyHoc> getpage(Pageable pageable){
        return dangKyHocService.getPage(pageable);
    }
}
