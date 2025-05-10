package com.capitole.inditex.price.application.port.outbound;
import com.capitole.inditex.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findPriceByDate(Long productId, Integer brandId, LocalDateTime date);
}
