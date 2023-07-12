package com.example.case_md4.repository;

import com.example.case_md4.model.Home_Stay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IHomeStayRepository extends JpaRepository<Home_Stay, Long> {
    @Query(value = "select * from home_stay  where (home_stay.name like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max))" +
            " or (home_stay.address like LOWER(:name) " +
            "and (home_stay.price >= :min and home_stay.price <= :max))",nativeQuery = true)
    List<Home_Stay> search(@Param("name") String name,
                           @Param("min") Long min,
                           @Param("max") Long max);
    List<Home_Stay> findAllByUser_Id(Long id);
    Page<Home_Stay> findAll(Pageable pageable);
    @Query(value = "select * from home_stay  where (home_stay.address like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max))" +
            " or (home_stay.address like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max))",nativeQuery = true)
    Page<Home_Stay> search(@Param("name") String name,
                           @Param("min") Long min,
                           @Param("max") Long max,Pageable pageable);
//    @Query(value = "select distinct * from home_stay inner join booking on home_stay.id = booking.home_stay_id" +
//            " where (home_stay.address like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max) " +
//            "and (:start_day > booking.end_date or :end_day < booking.star_date)) " +
//            "or (home_stay.address like LOWER(:name) and (home_stay.price >= :min and home_stay.price <= :max) " +
//            "and (:start_day > booking.end_date or :end_day < booking.star_date))",nativeQuery = true)
//    Page<Home_Stay> fndTer(@Param("name") String name,
//                               @Param("min") Long min,
//                               @Param("max") Long max,
//                               @Param("start_day") String start_day,
//                               @Param("end_day") String end_day,
//                               Pageable pageable);
}
