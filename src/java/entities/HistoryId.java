package entities;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

//@Embeddable
public class HistoryId {

    private User user;

    //@Temporal(javax.persistence.TemporalType.DATE)
    //@ManyToOne
    private Date lastSignIn;

}
