package com.example.case_md4.repository;

import com.example.case_md4.model.Home_Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IHomeStayRepository extends JpaRepository<Home_Stay, Long> {
    @Query(value = "select * from home_stay  where (home_stay.name like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max))" +
            " or (home_stay.address like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max))",nativeQuery = true)
    List<Home_Stay> search(@Param("name") String name,
                           @Param("min") Long min,
                           @Param("max") Long max);
    @Query(value = "select * from Home_Stay where Home_Stay .status = :number",nativeQuery = true)
    List<Home_Stay> findByStatus(@Param("number") int number);

}
