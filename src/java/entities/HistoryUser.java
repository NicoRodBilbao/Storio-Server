package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="history_user", schema="storio")
@XmlRootElement
public class HistoryUser implements Serializable {

	@EmbeddedId 
	private HistoryId historyId;	

	@MapsId("storio_userId")
	@ManyToOne
	private User storio_user;

	@MapsId("signInId")
	@ManyToOne
	private SignInHistory signInHistory;

	@Temporal(TemporalType.TIMESTAMP)
	private Date signIn;
        
	public HistoryUser() {
		super();
	}

	public HistoryId getHistoryId() {
		return historyId;
	}

	public void setHistoryId(HistoryId historyId) {
		this.historyId = historyId;
	}

	public User getStorio_user() {
		return storio_user;
	}

	public void setStorio_user(User storio_user) {
		this.storio_user = storio_user;
	}

	public SignInHistory getSignInHistory() {
		return signInHistory;
	}

	public void setSignInHistory(SignInHistory signInHistory) {
		this.signInHistory = signInHistory;
	}

	public Date getSignIn() {
		return signIn;
	}

	public void setSignIn(Date signIn) {
		this.signIn = signIn;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 41 * hash + Objects.hashCode(this.historyId);
		hash = 41 * hash + Objects.hashCode(this.storio_user);
		hash = 41 * hash + Objects.hashCode(this.signInHistory);
		hash = 41 * hash + Objects.hashCode(this.signIn);
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
		final HistoryUser other = (HistoryUser) obj;
		if (!Objects.equals(this.historyId, other.historyId)) {
			return false;
		}
		if (!Objects.equals(this.storio_user, other.storio_user)) {
			return false;
		}
		if (!Objects.equals(this.signInHistory, other.signInHistory)) {
			return false;
		}
		if (!Objects.equals(this.signIn, other.signIn)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "HistoryUser{" + "historyId=" + historyId + ", storio_user=" + storio_user + ", signInHistory=" + signInHistory + ", signIn=" + signIn + '}';
	}

}
