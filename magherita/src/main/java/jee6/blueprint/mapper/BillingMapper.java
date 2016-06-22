package jee6.blueprint.mapper;

import java.util.ArrayList;
import java.util.List;

import jee6.blueprint.dto.BillingDTO;
import jee6.blueprint.model.Billing;

public class BillingMapper {

	public static List<BillingDTO> map(List<Billing> billings) {
		List<BillingDTO> list = new ArrayList<BillingDTO>();
		
		for (Billing b : billings) {
			list.add(map(b));
		}
		
		return list;
	}

	public static BillingDTO map(Billing billing) {
		BillingDTO dto = new BillingDTO();

		dto.setId(billing.getId());
		dto.setAccountNumber(billing.getAccountNumber());
		dto.setAmount(billing.getAmount());
		
		return dto;
	}
}
