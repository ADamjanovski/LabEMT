package com.example.labsprojectemt.repository;

import com.example.labsprojectemt.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {


    @Query("SELECT r FROM Reservation r " +
            "WHERE (r.endDate BETWEEN :startDate AND :endDate) " +
            "   OR (r.startDate BETWEEN :startDate AND :endDate)")
    List<Reservation> findAllBetweenDates(@Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
                                          @Param("endDate")   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);
}
