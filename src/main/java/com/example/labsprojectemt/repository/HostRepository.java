package com.example.labsprojectemt.repository;

import com.example.labsprojectemt.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
