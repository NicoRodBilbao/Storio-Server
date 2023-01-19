package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="booking",schema="storio")
@NamedQueries({
    @NamedQuery(name="findAllBookings",
                query="SELECT b FROM Booking b"),
    @NamedQuery(name="findPacksForBooking",
                query="SELECT bp FROM Booking b JOIN b.packs bp WHERE b.id = :id"),
    @NamedQuery(name="findBookingsByState",
                query="SELECT b FROM Booking b WHERE b.state = :bookingState"),
    @NamedQuery(name="findClientOwnedBookings",
                query="SELECT b FROM Booking b WHERE b.client = :client"),
    })
@XmlRootElement
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Client client;

    @ManyToMany(mappedBy = "bookings", fetch = FetchType.EAGER)
    private List<Pack> packs;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private BookingState state;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Client getUser() {
        return client;
    }
    
    public void setUser(Client client) {
        this.client = client;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }
   

    public Booking() {
        super();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.client);
        hash = 89 * hash + Objects.hashCode(this.packs);
        hash = 89 * hash + Objects.hashCode(this.startDate);
        hash = 89 * hash + Objects.hashCode(this.endDate);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.state);
        return hash;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.packs, other.packs)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", client=" + client + ", packs=" + packs + ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description + ", state=" + state + '}';
    }
    
    
}