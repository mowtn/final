package com.lotus.final2.Repository;

import com.lotus.final2.Model.LoaiKhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiKhoaHocRP extends JpaRepository<LoaiKhoaHoc,Integer> {
}
