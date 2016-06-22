package jee6.blueprint.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import jee6.blueprint.dto.BillingDTO;
import jee6.blueprint.model.Billing;

@Stateless
public class BillingService extends GenericService {

	public Billing create(BillingDTO billingDTO) {
		Billing billing = new Billing();
		
		billing.setAccountNumber(billingDTO.getAccountNumber());
		billing.setAmount(billingDTO.getAmount());
		
		em.persist(billing);
		return billing;
	}

	public List<Billing> readBillings() {
		TypedQuery<Billing> query = em.createNamedQuery(Billing.FIND_ALL, Billing.class);
		return query.getResultList();
	}

}
