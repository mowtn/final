package com.lotus.final2.Repository;

import com.lotus.final2.Model.TinhTrangHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhTrangHocRP extends JpaRepository<TinhTrangHoc,Integer> {
}
