package com.lotus.final2.Repository;

import com.lotus.final2.Model.ChuDe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuDeRP extends JpaRepository<ChuDe,Integer> {
}
