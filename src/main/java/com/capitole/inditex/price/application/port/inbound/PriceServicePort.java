package com.capitole.inditex.price.application.port.inbound;
import com.capitole.inditex.price.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceServicePort {
    Price getPriceByDate(Long productId, Integer brandId, LocalDateTime date);
}
