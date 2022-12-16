package entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
public class HistoryId {

    private User user;

	@Temporal(javax.persistence.TemporalType.DATE)
    @ManyToOne
    private Date lastSignIn;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLastSignIn() {
		return lastSignIn;
	}

	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastSignIn, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoryId other = (HistoryId) obj;
		return Objects.equals(lastSignIn, other.lastSignIn) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "HistoryId [user=" + user + ", lastSignIn=" + lastSignIn + "]";
	}

}
