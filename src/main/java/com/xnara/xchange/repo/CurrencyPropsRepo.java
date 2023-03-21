package com.xnara.xchange.repo;

import com.xnara.xchange.domain.CurrencyProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyPropsRepo extends JpaRepository<CurrencyProps, Integer> {

    Optional<CurrencyProps> findByCurrencyCode(String currencyCode);
}
