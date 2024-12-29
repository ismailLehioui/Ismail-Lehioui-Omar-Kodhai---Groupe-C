package com.enicar.soc.repositories;

import com.enicar.soc.entities.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    @Query("SELECT l FROM Lawyer l WHERE l.firstName = :fname AND l.lastName = :lname")
    Lawyer getLawyerByName(@Param("fname") String fname ,@Param("lname") String lname);
    @Query("SELECT l FROM Lawyer l WHERE l.email = :email")
    Lawyer getLawyerByEmail(@Param("email") String email);
}
