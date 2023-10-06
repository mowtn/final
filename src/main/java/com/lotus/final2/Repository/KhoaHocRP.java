package com.lotus.final2.Repository;

import com.lotus.final2.Model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRP extends JpaRepository<KhoaHoc,Integer> {
    @Query(value = "SELECT * FROM tb_khoahoc where tenkhoahoc like :tenkhoahoc",nativeQuery = true)
    List<KhoaHoc> searchbyKeyword(@Param("tenkhoahoc") String tenkhoahoc);
}
