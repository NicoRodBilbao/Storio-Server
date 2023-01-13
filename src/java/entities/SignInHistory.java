package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@Table(name="signinhistory",schema="storio")
//@XmlRootElement
public class SignInHistory implements Serializable {

   // @EmbeddedId HistoryId id;

    public SignInHistory() {
        super();
    }
}
