package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.dto.CustomerMinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true,
            value = "SELECT c.name " +
                    "FROM customers c " +
                    "WHERE UPPER(c.state) = UPPER(:state)")
    List<CustomerMinProjection> search(String state);

    @Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) " +
            "FROM Customer obj " +
            "WHERE UPPER(obj.state) = UPPER(:state)")
    List<CustomerMinDTO> searchJpql(String state);

}
