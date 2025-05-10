package com.capitole.inditex.price.infrastructure.outbound.repository;
import com.capitole.inditex.price.infrastructure.outbound.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJPARepository extends JpaRepository<PriceEntity, Long> {
    @Query("select p from PriceEntity p where p.product_id = ?1 AND p.brand.id= ?2 AND (p.start_date<?3 AND p.end_date>?3)")
    List<PriceEntity> findPriceByDate(Long productId, Integer brandId, LocalDateTime date);
}
