package com.lotus.final2.Repository;

import com.lotus.final2.Model.QuyenHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenHanRP extends JpaRepository<QuyenHan,Integer> {
}
