package entities;

import java.util.Date;

@Embeddable
public class HistoryId {

    private User user;

    @Temporal(javax.persistance.TemporalType.Date)
    @ManyToOne
    private Date lastSignIn;

}
