package jee6.blueprint.service;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

import jee6.blueprint.model.user.User;
import jee6.blueprint.model.user.role.Role;
import jee6.blueprint.model.user.role.UserRole;

public class UserService extends GenericService {

	public User create(String username, String password, Role role) {
		User user = new User();

		user.setUserName(username);
		String passwordHash = hashPassword(password);
		user.setPasswordHash(passwordHash);

		em.persist(user);

		createRole(user, role);

		return user;
	}

	private String hashPassword(String password) {
		// use safer hash for production!
		return DigestUtils.sha1Hex(password);
	}

	private void createRole(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		em.persist(userRole);

		user.setRoles(Arrays.asList(userRole));
	}

}
