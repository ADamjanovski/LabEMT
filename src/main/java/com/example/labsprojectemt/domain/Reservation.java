package com.example.labsprojectemt.domain;

import com.example.labsprojectemt.domain.enumerations.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Accommodation accommodation;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Reservation() {
    }

    public Reservation( Accommodation accommodation, LocalDate startDate, LocalDate endDate,User user) {
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user=user;
        this.status=ReservationStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
