package com.wagdynavas.jam.repository;

import com.wagdynavas.jam.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    @Query("""
            SELECT c FROM CLIENT c WHERE c.xClientId = :xClientId
        """)
    Optional<Client> getByXClientId(String xClientId);
}
