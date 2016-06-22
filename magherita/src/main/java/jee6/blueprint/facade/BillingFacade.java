package jee6.blueprint.facade;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jee6.blueprint.dto.BillingDTO;
import jee6.blueprint.dto.billing.BillingListResponseDTO;
import jee6.blueprint.mapper.BillingMapper;
import jee6.blueprint.model.Billing;
import jee6.blueprint.model.user.role.Role;
import jee6.blueprint.service.BillingService;

@Path("billings")
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BillingFacade {

	@Inject
	private BillingService billingService;
	
	// http://localhost:8080/magherita/rest/billings/
	@GET
	@PermitAll // easier in browser to check
	@Produces(MediaType.APPLICATION_JSON)
	public BillingListResponseDTO generateBilling() {
		List<Billing> billings = billingService.readBillings();
		List<BillingDTO> list = BillingMapper.map(billings);
		return new BillingListResponseDTO(list);
	}

	@POST
	@Path("create/")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ Role.ADMIN_ROLE, Role.USER_ROLE })
	public String createBilling(BillingDTO billingDTO) {
		Billing billing = billingService.create(billingDTO);
		return billing.getId();
	}

}
