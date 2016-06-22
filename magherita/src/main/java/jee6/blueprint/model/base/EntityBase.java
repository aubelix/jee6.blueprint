package jee6.blueprint.model.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class EntityBase implements Serializable {
	private static final long serialVersionUID = -5579667581450362176L;
	public static final String PARAM_ID = "id";
	public static final String ID_COLUMN_DEFINTION = "varchar(36)";

	@Id
	@Column(length = 36)
	protected String id = java.util.UUID.randomUUID().toString();

	public EntityBase() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EntityBase))
			return false;
		EntityBase other = (EntityBase) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [id=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}
}
