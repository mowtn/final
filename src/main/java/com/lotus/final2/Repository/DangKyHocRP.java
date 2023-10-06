package com.lotus.final2.Repository;

import com.lotus.final2.Model.DangKyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyHocRP extends JpaRepository<DangKyHoc,Integer> {
    @Query(value = "SELECT count(hocvienid) FROM tb_dangkyhoc where khoahocid = :khoahocid group by khoahocid",nativeQuery = true)
    Integer getQuantityStudentByCourseID(@Param("khoahocid")int khoahocid);
}
