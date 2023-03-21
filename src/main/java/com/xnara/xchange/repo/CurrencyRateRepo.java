package com.xnara.xchange.repo;

import com.xnara.xchange.domain.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRateRepo extends JpaRepository<CurrencyRate, Integer> {

    Optional<CurrencyRate> findByCurrencyCode(String currencyCode);

    @Query(value = "SELECT c.* FROM currencyrate c where c.is_base = 1", nativeQuery = true)
    Optional<CurrencyRate> findBaseCurrency();
}
