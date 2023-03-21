package com.xnara.xchange.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class StatementFormatter {

    private final static String STATEMENT_MESSAGE_KEY = "xnara.exchange.rate.response.message";

    @Autowired
    @Qualifier("texts")
    private ResourceBundleMessageSource source;

    public String formatMessage(String lang, String value) {
        var locale = new Locale(lang);

        var message = source.getMessage(STATEMENT_MESSAGE_KEY, null, locale);
        return message.replace("{{curr_value}}", value);
    }
}
