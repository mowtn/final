package com.lotus.final2.Controller;

import com.lotus.final2.Model.QuyenHan;
import com.lotus.final2.Model.TaiKhoan;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("taikhoan")
public class TaiKhoanController {
    @Autowired
    TaiKhoanService taiKhoanService;
    @PostMapping("them")
    public Response<TaiKhoan> them(@RequestBody Request<TaiKhoan> taiKhoanRequest){
        return taiKhoanService.them(taiKhoanRequest);
    }
    @PutMapping("sua")
    public Response<TaiKhoan> sua(@RequestParam(name = "taikhoanid")int taikhoanid, @RequestBody Request<TaiKhoan> taiKhoanRequest){
        return taiKhoanService.sua(taikhoanid,taiKhoanRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<TaiKhoan> xoa(@PathVariable int id){
        return taiKhoanService.xoa(id);
    }
    @GetMapping("findByName")
    public Response<List<TaiKhoan>> timkiemtheoten(@RequestParam(name = "value")String value){
        return taiKhoanService.timkiemtheoten(value);
    }
    @GetMapping("page")
    public Page<TaiKhoan> page(Pageable pageable){
        return taiKhoanService.page(pageable);
    }
    @GetMapping("search")
    public Page<TaiKhoan> getpageByUser(Pageable pageable,@RequestParam(name = "account")String account){
        return taiKhoanService.pageSearch(pageable,account);
    }
}
