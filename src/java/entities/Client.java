package entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Client extends User {

    private UserPrivilege userPrivilege;

    @OneToMany(mappedBy="client")
    private List<Booking> bookings;

    public Client() {
        super();
    }
    
}
