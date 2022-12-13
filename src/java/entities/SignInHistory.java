package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SignInHistory",schema="storio")
public class SignInHistory implements Serializable {

    @EmbeddedId HistoryId id;

    public SignInHistory() {
        super();
    }
}
