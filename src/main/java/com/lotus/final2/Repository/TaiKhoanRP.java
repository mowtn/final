package com.lotus.final2.Repository;

import com.lotus.final2.Model.TaiKhoan;
import com.lotus.final2.Response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiKhoanRP extends JpaRepository<TaiKhoan,Integer> {
    @Query(value = "SELECT * FROM tb_taikhoan where taikhoan like %:value%",nativeQuery = true)
    List<TaiKhoan> findbyname(@Param("value") String value);
    @Query(value = "SELECT * FROM tb_taikhoan where taikhoan like %:account%",nativeQuery = true)
    Page<TaiKhoan> searchByUsername(Pageable pageable,@Param("account") String account);
}
