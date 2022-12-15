package entities;

import java.util.List;

@Entity
public class Admin extends User{

    private Boolean isSuperAdmin;

    public Admin() {
        super();
    }

	@Override
	public Integer getId() {
		return super.getId();
	}

	@Override
	public void setId(Integer id) {
		super.setId(id);
	}

	@Override
	public UserStatus getStatus() {
		return super.getStatus();
	}

	@Override
	public void setStatus(UserStatus status) {
		super.setStatus(status);
	}

	@Override
	public String getLogin() {
		return super.getLogin();
	}

	@Override
	public void setLogin(String login) {
		super.setLogin(login);
	}

	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}

	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	@Override
	public Integer getPhoneNumber() {
		return super.getPhoneNumber();
	}

	@Override
	public void setPhoneNumber(Integer phoneNumber) {
		super.setPhoneNumber(phoneNumber);
	}

	@Override
	public String getFullName() {
		return super.getFullName();
	}

	@Override
	public void setFullName(String fullName) {
		super.setFullName(fullName);
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(password);
	}

	@Override
	public UserPrivilege getPrivilege() {
		return super.getPrivilege();
	}

	@Override
	public void setPrivilege(UserPrivilege privilege) {
		super.setPrivilege(privilege);
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}

	@Override
	public List<SignInHistory> getHistory() {
		return super.getHistory();
	}

	@Override
	public void setHistory(List<SignInHistory> history) {
		super.setHistory(history);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
