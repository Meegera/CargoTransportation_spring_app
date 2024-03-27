package com.example.autopark.repository;

import java.util.List;

import com.example.autopark.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
    @Query("select p from Cargo p where concat(p.id, '', p.name, '', p.cargo_contents, '', p.city_dispatch, '', p.date_dispatch, '', p.city_arrival, '', p.date_arrival) LIKE %?1%")
    List<Cargo> search(String keyword);

}
