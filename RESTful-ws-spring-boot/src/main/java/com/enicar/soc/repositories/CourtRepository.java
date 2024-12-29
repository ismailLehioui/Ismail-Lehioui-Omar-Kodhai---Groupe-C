package com.enicar.soc.repositories;

import com.enicar.soc.entities.Court;
import com.enicar.soc.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
    @Query("SELECT c FROM Court c WHERE c.name = :name")
    public Court getCourtByName(@Param("name") String name);
}
