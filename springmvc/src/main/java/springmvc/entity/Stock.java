package springmvc.entity;

public class Stock {
	private String symbol; // �Ѳ��N��: �����n�঳������N��
	private Double price; // �R�i����: �����O�Q������10%����
	private Integer amount; // �R�i�Ѽ�: ���� >= 1000 �B�O 1000 ������
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", price=" + price + ", amount=" + amount + "]";
	}
	
	
}