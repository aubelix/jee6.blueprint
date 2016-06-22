package jee6.blueprint.model.user.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jee6.blueprint.model.user.User;

@Entity
@IdClass(UserRoleId.class)
@Table(name="user_role")
public class UserRole {

	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name="role_name", length=40)
	private Role role;
	
	public UserRole() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
