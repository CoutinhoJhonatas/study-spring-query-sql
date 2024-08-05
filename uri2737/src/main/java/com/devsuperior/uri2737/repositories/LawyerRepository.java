package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.dto.LawyerMinDTO;
import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2737.entities.Lawyer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    @Query(nativeQuery = true,
            value = "(SELECT l.name, l.customers_number AS customersNumber " +
                    "FROM lawyers l " +
                    "WHERE l.customers_number = " +
                        "(SELECT MAX(l2.customers_number) " +
                        "FROM lawyers l2))" +
                    "UNION ALL " +
                    "(SELECT l.name, l.customers_number " +
                    "FROM lawyers l " +
                    "WHERE l.customers_number = " +
                        "(SELECT MIN(l2.customers_number) " +
                        "FROM lawyers l2)) " +
                    "UNION ALL " +
                    "(SELECT 'Average', ROUND(AVG(l.customers_number)) " +
                    "FROM lawyers l)")
    List<LawyerMinProjection> search1();


    @Query(nativeQuery = true,
            value = "(SELECT l.name, l.customers_number AS customersNumber " +
                    "FROM lawyers l " +
                    "ORDER BY l.customers_number DESC " +
                    "LIMIT 1) " +
                    "UNION ALL " +
                    "(SELECT l.name, l.customers_number " +
                    "FROM lawyers l " +
                    "ORDER BY l.customers_number ASC " +
                    "LIMIT 1) " +
                    "UNION ALL " +
                    "(SELECT 'Average', ROUND(AVG(l.customers_number)) " +
                    "FROM lawyers l)")
    List<LawyerMinProjection> search2();

}
