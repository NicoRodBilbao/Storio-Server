package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SignInHistory",schema="storio")
public class SignInHistory implements Serializable {
    private User user;
    @Id
    private Date lastSignIn;
    public SignInHistory() {
        super();
    }
}
