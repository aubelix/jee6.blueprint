package jee6.blueprint.dto.billing;

import java.util.List;

import jee6.blueprint.dto.BillingDTO;
import jee6.blueprint.dto.ResponseDTO;

public class BillingListResponseDTO extends ResponseDTO {
	private List<BillingDTO> billings;

	public BillingListResponseDTO() {
	}

	public BillingListResponseDTO(List<BillingDTO> billings) {
		super();
		this.billings = billings;
	}

	public List<BillingDTO> getBillings() {
		return billings;
	}

	public void setBillings(List<BillingDTO> billings) {
		this.billings = billings;
	}

	@Override
	public String toString() {
		return "BillingListResponseDTO [billings=" + billings + "]";
	}

}
