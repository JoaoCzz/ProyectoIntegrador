package com.JCJ.gescon.Repository;

import com.JCJ.gescon.Model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocheRepository extends JpaRepository<Coche, Integer> {

    @Query(value = "SELECT * FROM T_COCHE WHERE marca = :marca", nativeQuery = true)
    List<Coche> findByMarca(@Param("marca") String marca);
}
