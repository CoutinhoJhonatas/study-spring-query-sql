package com.devsuperior.uri2621.repositores;

import com.devsuperior.uri2621.dtos.ProductDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true,
            value = "SELECT pr.name " +
                    "FROM products pr " +
                    "INNER JOIN providers p ON p.id = pr.id_providers " +
                    "WHERE p.name LIKE CONCAT(:beginName, '%') " +
                    "AND pr.amount BETWEEN :min AND :max")
    List<ProductProjection> search(String beginName, Integer min, Integer max);


    @Query("SELECT new com.devsuperior.uri2621.dtos.ProductDTO(p.name) " +
            "FROM Product p " +
            "WHERE p.provider.name LIKE CONCAT(:beginName, '%') " +
            "AND p.amount BETWEEN :min AND :max")
    List<ProductDTO> searchJpql(String beginName, Integer min, Integer max);

}
