package entities;

import java.io.Serializable;
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

}
