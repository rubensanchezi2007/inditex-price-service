package com.capitole.inditex.price.infrastructure.outbound.entities;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Prices")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    BrandEntity brand;
    @Column
    private LocalDateTime start_date;
    @Column
    private LocalDateTime end_date;
    @Column
    private Integer price_list;
    @Column
    private Long product_id;
    @Column
    private Integer priority;
    @Column
    private BigDecimal total_price;
    @Column
    private String currency;
}
