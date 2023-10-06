package com.lotus.final2.Repository;

import com.lotus.final2.Model.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HocVienRP extends JpaRepository<HocVien,Integer> {
    @Query(value = "SELECT * FROM tb_hocvien where email = :email",nativeQuery = true)
    List<HocVien> findByEmail(@Param("email")String email);
    @Query(value = "SELECT * FROM tb_hocvien where sodienthoai = :sodienthoai",nativeQuery = true)
    List<HocVien> findByPhone(@Param("sodienthoai")String sodienthoai);
    @Query(value = "SELECT * FROM final2.tb_hocvien where sodienthoai like %:text% or email like %:text%",nativeQuery = true)
    List<HocVien> findbyPhoneOrEmail(@Param("text")String text);
}
