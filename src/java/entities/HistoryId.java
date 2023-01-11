package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class HistoryId implements Serializable {

	private Integer storio_userId;
	private Integer signInId;

	public Integer getStorio_userId() {
		return storio_userId;
	}

	public void setStorio_userId(Integer storio_userId) {
		this.storio_userId = storio_userId;
	}

	public Integer getSignInId() {
		return signInId;
	}

	public void setSignInId(Integer signInId) {
		this.signInId = signInId;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 29 * hash + Objects.hashCode(this.storio_userId);
		hash = 29 * hash + Objects.hashCode(this.signInId);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final HistoryId other = (HistoryId) obj;
		if (!Objects.equals(this.storio_userId, other.storio_userId)) {
			return false;
		}
		if (!Objects.equals(this.signInId, other.signInId)) {
			return false;
		}
		return true;
	}

}
