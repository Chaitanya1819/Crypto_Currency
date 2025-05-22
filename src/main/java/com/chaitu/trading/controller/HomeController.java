package com.chaitu.trading.controller;

import com.chaitu.trading.entity.CoinDetails;
import com.chaitu.trading.service.CoinDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coins")
@CrossOrigin(origins = "*")  // allow frontend requests

public class HomeController {

    @Autowired
    private CoinDetailsService service;

    @GetMapping
    public List<CoinDetails> getAllCoins() {
        return service.getAllCoins();
    }

    @GetMapping("/{id}")
    public CoinDetails getCoinById(@PathVariable String id) {
        return service.getCoinById(id);
    }

    @PostMapping("/refresh")
    public String refreshCoinData() {
        service.fetchAndSaveCoinDetails();
        return "Coin data refreshed successfully!";
    }
}
