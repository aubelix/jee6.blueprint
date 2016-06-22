package jee6.blueprint.model.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jee6.blueprint.model.base.EntityBase;
import jee6.blueprint.model.user.role.UserRole;

@Entity
@Table(name="users")
public class User extends EntityBase {
	private static final long serialVersionUID = 2911530388691013592L;

	@Column(name = "user_name", unique = true, length = 40)
	private String userName;

	@Column(name = "password_hash")
	private String passwordHash;

	@OneToMany(mappedBy = "user")
	private List<UserRole> roles;

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", passwordHash=" + passwordHash + "]";
	}

}
