package com.lotus.final2.Controller;

import com.lotus.final2.Model.BaiViet;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.BaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("baiviet")
public class BaiVietController {
    @Autowired
    BaiVietService baiVietService;
    @PostMapping("them")
    public Response<BaiViet> them(@RequestBody Request<BaiViet> baiVietRequest){
        return baiVietService.them(baiVietRequest);
    }
    @PutMapping("sua")
    public Response<BaiViet> sua(@RequestParam(name = "baivietid")int baivietid, @RequestBody Request<BaiViet> baiVietRequest){
        return baiVietService.sua(baivietid,baiVietRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<BaiViet> xoa(@PathVariable int id){
        return baiVietService.xoa(id);
    }
    @GetMapping("page")
    public Page<BaiViet> page(Pageable pageable,@RequestParam(name = "title")String title){
        return baiVietService.searchByName(pageable,title);
    }
}
