package com.example.case_md4.repository;

import com.example.case_md4.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByHomeStay_Id(Long id);
    Page<Booking> findAllByUser_Id(Long id,Pageable pageable);
    Page<Booking> findAll(Pageable pageable);

    @Query(value = "select datediff(:endDate,:startDate)",nativeQuery = true)
    int totalDate(@Param("endDate")LocalDate endDate,
                  @Param("startDate")LocalDate startDate);
    @Query(value = "select b.id ,b.end_date, b.is_bill,b.star_date,b.action_id,b.home_stay_id,b.user_id,"
            + " (b.end_date - b.star_date) total_date,((b.end_date - b.star_date) * h.price) total_price "
            + "from booking b , home_stay h where b.home_stay_id = h.id",nativeQuery = true)
    Page<Booking>  findAllBookingPage(Pageable pageable);
   
}

