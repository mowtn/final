package com.lotus.final2.Controller;

import com.lotus.final2.Model.HocVien;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.HocVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hocvien")
public class HocVienController {
    @Autowired
    HocVienService hocVienService;
    @PostMapping("them")
    public Response<HocVien> them(@RequestBody Request<HocVien> hocVienRequest){
        return hocVienService.them(hocVienRequest);
    }
    @PutMapping("sua/{id}")
    public Response<HocVien> sua(@PathVariable int id,@RequestBody Request<HocVien> hocVienRequest){
        return hocVienService.sua(id,hocVienRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<HocVien> xoa(@PathVariable int id){
        return hocVienService.xoa(id);
    }
    @GetMapping("timkiem")
    public Response<List<HocVien>> getlist(@RequestParam(name="text")String text){
        return hocVienService.getlist(text);
    }
    @GetMapping("page")
    public Page<HocVien> page(Pageable pageable){
        return hocVienService.pagehocvien(pageable);
    }
}
