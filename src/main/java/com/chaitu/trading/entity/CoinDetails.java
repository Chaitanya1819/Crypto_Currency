package com.chaitu.trading.entity;

import com.chaitu.trading.dto.Roi;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "coin_details")
@Getter
@Setter
public class CoinDetails {
    @Id
    private String id;

    private String symbol;
    private String name;
    private String image;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "market_cap")
    private BigDecimal marketCap;

    @Column(name = "market_cap_rank")
    private Integer marketCapRank;

    @Column(name = "fully_diluted_valuation")
    private BigDecimal fullyDilutedValuation;

    @Column(name = "total_volume")
    private BigDecimal totalVolume;

    @Column(name = "high_24h")
    private BigDecimal high24h;

    @Column(name = "low_24h")
    private BigDecimal low24h;

    @Column(name = "price_change_24h")
    private BigDecimal priceChange24h;

    @Column(name = "price_change_percentage_24h")
    private BigDecimal priceChangePercentage24h;

    @Column(name = "market_cap_change_24h")
    private BigDecimal marketCapChange24h;

    @Column(name = "market_cap_change_percentage_24h")
    private BigDecimal marketCapChangePercentage24h;

    @Column(name = "circulating_supply")
    private BigDecimal circulatingSupply;

    @Column(name = "total_supply")
    private BigDecimal totalSupply;

    @Column(name = "max_supply")
    private BigDecimal maxSupply;

    private BigDecimal ath;

    @Column(name = "ath_change_percentage")
    private BigDecimal athChangePercentage;

    @Column(name = "ath_date")
    private String athDate;

    private BigDecimal atl;

    @Column(name = "atl_change_percentage")
    private BigDecimal atlChangePercentage;

    @Column(name = "atl_date")
    private String atlDate;

    @Embedded
    private Roi roi;

    @Column(name = "last_updated")
    private String lastUpdated;
}
