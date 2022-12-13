package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

    private UserPrivilege userPrivilege;

    @OneToMany(mappedBy="client")
    private List<Booking> bookings;

    public Client() {
        super();
    }
    
}
