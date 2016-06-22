package jee6.blueprint.service.startup;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jee6.blueprint.model.user.role.Role;
import jee6.blueprint.service.GenericService;
import jee6.blueprint.service.UserService;

@Singleton
@Startup
public class StartupService extends GenericService {

	private Logger log = LoggerFactory.getLogger(StartupService.class);
	
	@Inject
	private UserService userService;
	
	@PostConstruct
	public void init() {
		log.warn("Creating Testusers ...");
		
		userService.create("testadmin", "test", Role.ADMIN);
		userService.create("testuser", "test", Role.USER);
		
		log.warn("Testusers created!");
	}
}
