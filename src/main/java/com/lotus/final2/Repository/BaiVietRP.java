package com.lotus.final2.Repository;

import com.lotus.final2.Model.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiVietRP extends JpaRepository<BaiViet,Integer> {
    @Query(value = "SELECT * FROM tb_baiviet where tenbaiviet like %:title%",nativeQuery = true)
    Page<BaiViet> searchByTitle(Pageable pageable,@Param("title") String title);
}
