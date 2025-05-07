package com.example.labsprojectemt.repository;

import com.example.labsprojectemt.domain.Host;
import com.example.labsprojectemt.domain.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HostRepository extends JpaRepository<Host, Long> {


    @Query("SELECT h.name as name, h.surname as surname FROM Host h")
    List<HostProjection> takeNameAndSurnameByProjection();
}
