package jee6.blueprint.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import jee6.blueprint.model.base.EntityBase;

@Entity
@Table(name="billings")
@NamedQueries({
	@NamedQuery(name=Billing.FIND_ALL, query="SELECT b FROM Billing b")
})
public class Billing extends EntityBase {
	private static final long serialVersionUID = -5675423913776827193L;
	
	public static final String FIND_ALL = "Billings.findAll";

	@Column(name="account_number", nullable=false)
	private String accountNumber;
	
	@Column(name="amount", nullable=false)
	private BigDecimal amount;
	
	public Billing() {
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
	
}
