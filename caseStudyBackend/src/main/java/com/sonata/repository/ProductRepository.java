package com.sonata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonata.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE "
            + "(:categoryId IS NULL OR p.category.categoryId = :categoryId) AND "
            + "(:minPrice IS NULL OR p.price >= :minPrice) AND "
            + "(:maxPrice IS NULL OR p.price <= :maxPrice) AND "
            + "(:brand IS NULL OR p.brand = :brand) AND "
            + "(:ratings IS NULL OR p.ratings >= :ratings)")
    List<Product> filterProducts(@Param("categoryId") Integer categoryId,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("brand") String brand,
                                 @Param("ratings") Integer ratings);

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByOrderByCreatedAtDesc();

    List<Product> findAllByOrderByPopularityDesc();

    @Query("SELECT p FROM Product p WHERE "
            + "(LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR "
            + "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))) AND "
            + "(:categoryId IS NULL OR p.category.categoryId = :categoryId) AND "
            + "(:minPrice IS NULL OR p.price >= :minPrice) AND "
            + "(:maxPrice IS NULL OR p.price <= :maxPrice) AND "
            + "(:brand IS NULL OR p.brand = :brand) AND "
            + "(:ratings IS NULL OR p.ratings >= :ratings)")
    List<Product> advancedSearch(@Param("query") String query,
                                 @Param("categoryId") Integer categoryId,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("brand") String brand,
                                 @Param("ratings") Integer ratings);
}
