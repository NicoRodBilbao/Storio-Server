package entities;

import java.util.List;

@Entity
public class Client extends User {

    private UserPrivilege userPrivilege;

    @OneToMany(mappedBy="client")
    private List<Booking> bookings;

    public Client() {
        super();
    }
    
}
