package com.lotus.final2.Controller;

import com.lotus.final2.Model.ChuDe;
import com.lotus.final2.Model.LoaiBaiViet;
import com.lotus.final2.Request.Request;
import com.lotus.final2.Response.Response;
import com.lotus.final2.Service.ChuDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chude")
public class ChuDeController {
    @Autowired
    ChuDeService chuDeService;
    @PostMapping("them")
    public Response<ChuDe> them(@RequestBody Request<ChuDe> chuDeRequest){
        return chuDeService.them(chuDeRequest);
    }
    @PutMapping("sua")
    public Response<ChuDe> sua(@RequestParam(name = "chudeid")int chudeid,@RequestBody Request<ChuDe> chuDeRequest){
        return chuDeService.sua(chudeid,chuDeRequest);
    }
    @DeleteMapping("xoa/{id}")
    public Response<ChuDe> xoa(@PathVariable int id){
        return chuDeService.xoa(id);
    }
    @GetMapping("page")
    public Page<ChuDe> page(Pageable pageable){
        return chuDeService.page(pageable);
    }
}
