package com.lotus.final2.Repository;

import com.lotus.final2.Model.LoaiBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiBaiVietRP extends JpaRepository<LoaiBaiViet,Integer> {
}
