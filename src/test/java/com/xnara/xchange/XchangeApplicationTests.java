package com.xnara.xchange;

import com.xnara.xchange.controller.dto.ExchangeRequest;
import com.xnara.xchange.exception.InvalidRequestException;
import com.xnara.xchange.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XchangeApplicationTests {

	@Autowired
	private ExchangeService service;

	@Test
	void testUSDToSGD() throws InvalidRequestException {

		var req = new ExchangeRequest();
		req.setAmount(new BigDecimal("23.00"));
		req.setCurrCode("USD");
		req.setLanguage("en");
		req.setTargetCode("SGD");

		var res = service.getRate(req);

		assertEquals("S$ 31.44", res.getResult());
		assertEquals("You will receive S$ 31.44", res.getStatement());
	}

	@Test
	void testSGDToUSD() throws InvalidRequestException {

		var req = new ExchangeRequest();
		req.setAmount(new BigDecimal("23.00"));
		req.setCurrCode("SGD");
		req.setLanguage("en");
		req.setTargetCode("USD");

		var res = service.getRate(req);

		assertEquals("$ 16.82", res.getResult());
	}


	@Test
	void testLocale() throws InvalidRequestException {

		var req = new ExchangeRequest();
		req.setAmount(new BigDecimal("23.00"));
		req.setCurrCode("USD");
		req.setLanguage("ta");
		req.setTargetCode("SGD");

		var res = service.getRate(req);

		assertEquals("S$ 31.44", res.getResult());

		/*
		* Deliberately asserting NOT equals As there might be issues with front encoding.
		*
		* */

		assertNotEquals("You will receive S$ 31.44", res.getStatement());
	}

	@Test
	public void testInvalidCurrency() {

		var req = new ExchangeRequest();
		req.setAmount(new BigDecimal("23.00"));
		req.setCurrCode("XXX");
		req.setLanguage("ta");
		req.setTargetCode("SGD");

		var exception = assertThrows(
				InvalidRequestException.class,
				() -> service.getRate(req)
		);
		assertEquals("Invalid currency: XXX", exception.getMessage());
	}

	@Test
	public void testInvalidTargetCurrency() {

		var req = new ExchangeRequest();
		req.setAmount(new BigDecimal("23.00"));
		req.setCurrCode("SGD");
		req.setLanguage("ta");
		req.setTargetCode("YYY");

		var exception = assertThrows(
				InvalidRequestException.class,
				() -> service.getRate(req)
		);
		assertEquals("Invalid target currency: YYY", exception.getMessage());
	}

	@Test
	public void testInvalidLanguage() {

		var req = new ExchangeRequest();
		req.setAmount(new BigDecimal("23.00"));
		req.setCurrCode("USD");
		req.setTargetCode("SGD");

		var exception = assertThrows(
				InvalidRequestException.class,
				() -> service.getRate(req)
		);
		assertEquals("Language is required: null", exception.getMessage());
	}

}
