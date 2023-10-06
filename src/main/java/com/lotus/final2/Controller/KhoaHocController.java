package com.lotus.final2.Controller;

import com.lotus.final2.Model.KhoaHoc;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.KhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("khoahoc")
public class KhoaHocController {
    @Autowired
    KhoaHocService khoaHocService;
    @PostMapping("them")
    public Response<KhoaHoc> them(@RequestParam(name = "loaikhoahoc")int loaikhoahoc,@RequestBody Request<KhoaHoc> khoaHocRequest){
        return khoaHocService.them(loaikhoahoc,khoaHocRequest);
    }
    @PutMapping("sua")
    public Response<KhoaHoc> sua(@RequestParam(name = "khoahocid")int khoahocid,@RequestBody Request<KhoaHoc> khoaHocRequest){
        return khoaHocService.sua(khoahocid,khoaHocRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<KhoaHoc> xoa(@PathVariable int id){
        return khoaHocService.xoa(id);
    }
    @GetMapping("search")
    public Response<List<KhoaHoc>> getListByName(@RequestParam(name = "tenkhoahoc")String tenkhoahoc){
        return khoaHocService.searchbyKeyword(tenkhoahoc);
    }
    @GetMapping("page")
    public Page<KhoaHoc> page(Pageable pageable){
        return khoaHocService.pagekhoahoc(pageable);
    }
}
