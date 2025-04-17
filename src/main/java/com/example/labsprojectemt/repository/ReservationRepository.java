package com.example.labsprojectemt.repository;

import com.example.labsprojectemt.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {


    @Query("SELECT r FROM Reservation r " +
            "WHERE r.status='CONFIRMED' AND ((r.endDate BETWEEN :startDate AND :endDate) " +
            "   OR (r.startDate BETWEEN :startDate AND :endDate))")
    List<Reservation> findAllBetweenDates(@Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
                                          @Param("endDate")   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);


    @Query("SELECT r FROM Reservation r " +
            "WHERE (r.endDate BETWEEN :startDate AND :endDate) " +
            "   OR (r.startDate BETWEEN :startDate AND :endDate)" +
            "AND :id=r.id")
    Optional<Reservation> findIfReserved(@Param("id") Long id, @Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
                                        @Param("endDate")   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate);
}
