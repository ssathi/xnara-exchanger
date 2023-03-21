package com.xnara.xchange.service;

import com.xnara.xchange.controller.dto.ExchangeRequest;
import com.xnara.xchange.controller.dto.ExchangeResponse;
import com.xnara.xchange.exception.InvalidRequestException;
import com.xnara.xchange.locale.StatementFormatter;
import com.xnara.xchange.repo.CurrencyPropsRepo;
import com.xnara.xchange.repo.CurrencyRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

import static org.springframework.util.StringUtils.hasText;

@Service
public class ExchangeService {

    @Autowired
    private CurrencyRateRepo rateRepo;

    @Autowired
    private CurrencyPropsRepo propsRepo;

    @Autowired
    private StatementFormatter formatter;

    public ExchangeResponse getRate(ExchangeRequest request) throws InvalidRequestException {

        var fromRateOpt = rateRepo.findByCurrencyCode(request.getCurrCode());

        if (fromRateOpt.isEmpty()) {
            throw new InvalidRequestException("Invalid currency: " + request.getCurrCode());
        }

        var targetRateOpt = rateRepo.findByCurrencyCode(request.getTargetCode());

        if (targetRateOpt.isEmpty()) {
            throw new InvalidRequestException("Invalid target currency: " + request.getTargetCode());
        }

        if (!hasText(request.getLanguage())) {
            throw new InvalidRequestException("Language is required: " + request.getLanguage());
        }

        var fromRate  = fromRateOpt.get();
        var targetRate = targetRateOpt.get();

        var targetAmount = request.getAmount()
                .divide(fromRate.getRateModifier(), 2, RoundingMode.DOWN)                // since it is currency round down to 2 digits
                .multiply(targetRate.getRateModifier());

        var targetPropsOpt = propsRepo.findByCurrencyCode(request.getTargetCode());

        if (targetPropsOpt.isPresent()) {

            var targetPros = targetPropsOpt.get();

            var result = targetPros.getSymbol()
                    + " "
                    + targetAmount.setScale(targetPros.getDecimalPlaces(), RoundingMode.DOWN);

            var message = formatter.formatMessage(request.getLanguage(), result);

            return new ExchangeResponse(result, message);
        } else {

            /*
            * if target currency don't have any properties
            * set the symbol to the currency code
            * */

            var result = request.getTargetCode()
                    + " "
                    + targetAmount.setScale(2, RoundingMode.DOWN);

            var message = formatter.formatMessage(request.getLanguage(), result);

            return new ExchangeResponse(result, message);
        }
    }
}
