package jee6.blueprint.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EntityVersionableBase extends EntityBase {
	private static final long serialVersionUID = -5579667581450362176L;

	@Column(name = "record_version")
	@Version
	private long version;
	
	public EntityVersionableBase() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
