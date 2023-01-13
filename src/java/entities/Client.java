package entities;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "client", schema = "storio")
@XmlRootElement
public class Client extends User {

	@OneToMany(mappedBy="client")
	@XmlTransient
	private List<Booking> bookings;

	public Client() {
		super();
	}

	@XmlTransient
	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return super.toString(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public int hashCode() {
		return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getEmail() {
		return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setPrivilege(UserPrivilege privilege) {
		super.setPrivilege(privilege); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public UserPrivilege getPrivilege() {
		return super.getPrivilege(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(password); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getPassword() {
		return super.getPassword(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setFullName(String fullName) {
		super.setFullName(fullName); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getFullName() {
		return super.getFullName(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setPhoneNumber(Integer phoneNumber) {
		super.setPhoneNumber(phoneNumber); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Integer getPhoneNumber() {
		return super.getPhoneNumber(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setLogin(String login) {
		super.setLogin(login); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getLogin() {
		return super.getLogin(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setStatus(UserStatus status) {
		super.setStatus(status); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public UserStatus getStatus() {
		return super.getStatus(); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setId(Integer id) {
		super.setId(id); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Integer getId() {
		return super.getId(); //To change body of generated methods, choose Tools | Templates.
	}

}
