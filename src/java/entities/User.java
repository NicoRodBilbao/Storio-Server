package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User",schema="storio")
@MappedSuperclass
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private UserStatus status;
    
    private String login;
    
    private Integer phoneNumber;
    
    private String fullName;
    
    private String password;
    
    private UserPrivilege privilege;
    
    private String email;
    
    @OneToMany(mappedBy="user")
    private List<Booking> bookings;
    
    @OneToMany(mappedBy="user")
    private List<SignInHistory> history;
    
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<SignInHistory> getHistory() {
        return history;
    }

    public void setHistory(List<SignInHistory> history) {
        this.history = history;
    }

    @Override
	public int hashCode() {
		return Objects.hash(bookings, email, fullName, history, id, login, password, phoneNumber, privilege, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(bookings, other.bookings) && Objects.equals(email, other.email)
				&& Objects.equals(fullName, other.fullName) && Objects.equals(history, other.history)
				&& Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& privilege == other.privilege && status == other.status;
	}

	@Override
    public String toString() {
        return "User{" + "id=" + id + ", status=" + status + ", login=" + login + ", phoneNumber=" + phoneNumber + ", fullName=" + fullName + ", password=" + password + ", privilege=" + privilege + ", email=" + email + ", bookings=" + bookings + ", history=" + history + '}';
    }
    
   
}
