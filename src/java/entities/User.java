package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "storio_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlRootElement
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	private String login;

	private Integer phoneNumber;

	private String fullName;

	private String password;

	@Enumerated(EnumType.ORDINAL)
	private UserPrivilege privilege;

	private String email;

	@OneToMany(cascade=ALL,mappedBy="storio_user")
	private Set<HistoryUser> signIns;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(UserPrivilege privilege) {
		this.privilege = privilege;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.id);
		hash = 79 * hash + Objects.hashCode(this.status);
		hash = 79 * hash + Objects.hashCode(this.login);
		hash = 79 * hash + Objects.hashCode(this.phoneNumber);
		hash = 79 * hash + Objects.hashCode(this.fullName);
		hash = 79 * hash + Objects.hashCode(this.password);
		hash = 79 * hash + Objects.hashCode(this.privilege);
		hash = 79 * hash + Objects.hashCode(this.email);
		hash = 79 * hash + Objects.hashCode(this.signIns);
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
		final User other = (User) obj;
		if (!Objects.equals(this.login, other.login)) {
			return false;
		}
		if (!Objects.equals(this.fullName, other.fullName)) {
			return false;
		}
		if (!Objects.equals(this.password, other.password)) {
			return false;
		}
		if (!Objects.equals(this.email, other.email)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (this.status != other.status) {
			return false;
		}
		if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
			return false;
		}
		if (this.privilege != other.privilege) {
			return false;
		}
		if (!Objects.equals(this.signIns, other.signIns)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", status=" + status + ", login=" + login + ", phoneNumber=" + phoneNumber + ", fullName=" + fullName + ", password=" + password + ", privilege=" + privilege + ", email=" + email + ", signIns=" + signIns + '}';
	}

}
