package at.bankaustria.magherita.test;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import jee6.blueprint.dto.BillingDTO;

public class InvokeService {

	public static void main(String[] args) throws Exception {
		System.out.println("Calling service ...");

		ClientRequest request = new ClientRequest("http://localhost:8080/magherita/rest/billings/create");
		request.header("Authorization", "Basic dGVzdGFkbWluOnRlc3Q=");

		BillingDTO data = new BillingDTO();
		data.setAccountNumber("12345");
		data.setAmount(BigDecimal.valueOf(100000000d));
		// we're expecting a String back
		ClientResponse<String> response = request.body(MediaType.APPLICATION_JSON, data).post(String.class);
		
		System.out.println("Generated Billing with id: " + response.getEntity());
	}

}
