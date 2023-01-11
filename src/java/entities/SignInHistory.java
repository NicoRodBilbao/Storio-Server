package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="signInHistory")
@XmlRootElement
public class SignInHistory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany(cascade=ALL, mappedBy="signInHistory")	
	private Set<HistoryUser> signIns;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public Set<HistoryUser> getSignIns() {
		return signIns;
	}

	public void setSignIns(Set<HistoryUser> signIns) {
		this.signIns = signIns;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 43 * hash + Objects.hashCode(this.id);
		hash = 43 * hash + Objects.hashCode(this.signIns);
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
		final SignInHistory other = (SignInHistory) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.signIns, other.signIns)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SignInHistory{" + "id=" + id + ", signIns=" + signIns + '}';
	}
	
}
