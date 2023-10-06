package com.lotus.final2.Controller;

import com.lotus.final2.Model.LoaiBaiViet;
import com.lotus.final2.Model.QuyenHan;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.LoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loaibaiviet")
public class LoaibaiVietController {
    @Autowired
    LoaiBaiVietService loaiBaiVietService;
    @PostMapping("them")
    public Response<LoaiBaiViet> them(@RequestBody Request<LoaiBaiViet> loaiBaiVietRequest){
        return loaiBaiVietService.them(loaiBaiVietRequest);
    }
    @PutMapping("sua")
    public Response<LoaiBaiViet> sua(@RequestParam(name = "loaibaivietid")int loaibaivietid,@RequestBody Request<LoaiBaiViet> loaiBaiVietRequest){
        return loaiBaiVietService.sua(loaibaivietid,loaiBaiVietRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<LoaiBaiViet> xoa(@PathVariable int id){
        return loaiBaiVietService.xoa(id);
    }
    @GetMapping("page")
    public Page<LoaiBaiViet> page(Pageable pageable){
        return loaiBaiVietService.page(pageable);
    }
}
