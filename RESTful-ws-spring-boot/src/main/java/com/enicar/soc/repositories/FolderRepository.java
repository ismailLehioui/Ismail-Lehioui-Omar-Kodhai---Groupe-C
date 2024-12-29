package com.enicar.soc.repositories;

import com.enicar.soc.entities.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query("SELECT f FROM Folder f WHERE f.name = :name")
    public Folder getFolderByName(@Param("name") String name);
}
