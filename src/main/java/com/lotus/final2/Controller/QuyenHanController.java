package com.lotus.final2.Controller;

import com.lotus.final2.Model.LoaiKhoaHoc;
import com.lotus.final2.Model.QuyenHan;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.QuyenHanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quyenhan")
public class QuyenHanController {
    @Autowired
    QuyenHanService quyenHanService;
    @PostMapping("them")
    public Response<QuyenHan> them(@RequestBody Request<QuyenHan> quyenHanRequest){
        return quyenHanService.them(quyenHanRequest);
    }
    @PutMapping("sua")
    public Response<QuyenHan> sua(@RequestParam(name = "quyenhanid")int quyenhanid,@RequestBody Request<QuyenHan> quyenHanRequest){
        return quyenHanService.sua(quyenhanid,quyenHanRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<QuyenHan> xoa(@PathVariable int id){
        return quyenHanService.xoa(id);
    }
    @GetMapping("page")
    public Page<QuyenHan> page(Pageable pageable){
        return quyenHanService.page(pageable);
    }
}
