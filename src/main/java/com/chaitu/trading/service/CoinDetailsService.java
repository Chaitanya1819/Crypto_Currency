package com.chaitu.trading.service;

import com.chaitu.trading.dto.CoinGeckoDTO;
import com.chaitu.trading.entity.CoinDetails;
import com.chaitu.trading.repository.CoinDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinDetailsService {

    @Autowired
    private CoinDetailsRepository repository;

    @Value("${coingecko.api.key}")
    private String coingeckoApiKey;

    public List<CoinDetails> getAllCoins() {
        return repository.findAll();
    }

    public CoinDetails getCoinById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void fetchAndSaveCoinDetails() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.coingecko.com/api/v3/coins/markets" +
                "?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-cg-pro-api-key", coingeckoApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CoinGeckoDTO[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CoinGeckoDTO[].class);

        List<CoinDetails> coinDetailsList = Arrays.stream(response.getBody())
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        repository.saveAll(coinDetailsList);
    }

    private CoinDetails convertToEntity(CoinGeckoDTO dto) {
        CoinDetails coin = new CoinDetails();
        coin.setId(dto.getId());
        coin.setName(dto.getName());
        coin.setSymbol(dto.getSymbol());
        coin.setImage(dto.getImage());

        coin.setCurrentPrice(BigDecimal.valueOf(dto.getCurrentPrice()));
        coin.setMarketCap(BigDecimal.valueOf(dto.getMarketCap()));
        coin.setMarketCapRank(dto.getMarketCapRank());
        coin.setFullyDilutedValuation(BigDecimal.valueOf(dto.getFullyDilutedValuation()));
        coin.setTotalVolume(BigDecimal.valueOf(dto.getTotalVolume()));
        coin.setHigh24h(BigDecimal.valueOf(dto.getHigh24h()));
        coin.setLow24h(BigDecimal.valueOf(dto.getLow24h()));
        coin.setPriceChange24h(BigDecimal.valueOf(dto.getPriceChange24h()));
        coin.setPriceChangePercentage24h(BigDecimal.valueOf(dto.getPriceChangePercentage24h()));
        coin.setMarketCapChange24h(BigDecimal.valueOf(dto.getMarketCapChange24h()));
        coin.setMarketCapChangePercentage24h(BigDecimal.valueOf(dto.getMarketCapChangePercentage24h()));
        coin.setCirculatingSupply(BigDecimal.valueOf(dto.getCirculatingSupply()));
        coin.setTotalSupply(BigDecimal.valueOf(dto.getTotalSupply()));
        coin.setMaxSupply(BigDecimal.valueOf(dto.getMaxSupply()));
        coin.setAth(BigDecimal.valueOf(dto.getAth()));
        coin.setAthChangePercentage(BigDecimal.valueOf(dto.getAthChangePercentage()));
        coin.setAthDate(dto.getAthDate());
        coin.setAtl(BigDecimal.valueOf(dto.getAtl()));
        coin.setAtlChangePercentage(BigDecimal.valueOf(dto.getAtlChangePercentage()));
        coin.setAtlDate(dto.getAtlDate());
        coin.setLastUpdated(dto.getLastUpdated());
        coin.setRoi(dto.getRoi());

        return coin;
    }



}
