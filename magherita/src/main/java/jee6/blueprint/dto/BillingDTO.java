package jee6.blueprint.dto;

import java.math.BigDecimal;

public class BillingDTO {

	private String id;

	private String accountNumber;
	private BigDecimal amount;

	public BillingDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BillingDTO [id=" + id + ", accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}

}
