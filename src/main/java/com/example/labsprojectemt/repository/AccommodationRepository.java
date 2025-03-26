package com.example.labsprojectemt.repository;

import com.example.labsprojectemt.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

    List<Accommodation> findAllByIdNotIn(Collection<Long> id);
}
