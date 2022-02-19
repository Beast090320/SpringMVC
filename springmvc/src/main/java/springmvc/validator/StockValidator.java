package springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springmvc.entity.Stock;
import yahoofinance.YahooFinance;

@Component
public class StockValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof Stock)) {
			return;
		}
		Stock stock = (Stock) target;
		// ��¦���� : �i�H�ϥ� ValidationUtils
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "stock.amount.empty");

		// �i������
		yahoofinance.Stock yStock = null;
		try {
			yStock = YahooFinance.get(stock.getSymbol());
			// �Q��
			double previousClose = yStock.getQuote().getPreviousClose().doubleValue();
			// �R�i���楲���O�Q�馬�L������10%����
			if (stock.getPrice() < previousClose * 0.9 || stock.getPrice() > previousClose * 1.1) {
				// rejectValue �䴩 properties
				errors.rejectValue("price", "stock.price.range");
				errors.reject("price", String.format("(%.1f ~ %.1f)",
						(previousClose * 0.9),
						previousClose * 1.1));

			}
			// �R�i�Ѽƥ����j��ε���1000
			if (stock.getAmount() < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			// �R�i�Ѽƥ����O1000������
			if (stock.getAmount() % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (yStock == null) {
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
	}
}
