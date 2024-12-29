package com.enicar.soc.repositories;

import com.enicar.soc.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.firstName = :fname AND c.lastName = :lname")
    Client getClientByName(@Param("fname") String fname , @Param("lname") String lname);
    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Client getClientByEmail(@Param("email") String email);
}
