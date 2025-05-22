package com.chaitu.trading.repository;

import com.chaitu.trading.entity.CoinDetails;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface CoinDetailsRepository extends JpaRepository<CoinDetails, String> {
}




