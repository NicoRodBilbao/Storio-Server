package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    
    @NamedQuery(name="listAllPacks", query="SELECT p FROM Pack p"), 
    
    @NamedQuery(name="listPacksByState", query="SELECT p FROM Pack p WHERE p.state=:state"), 
    
    @NamedQuery(name="listPacksByType", query="SELECT p FROM Pack p WHERE p.type=:type"), 
    
    @NamedQuery(name="listBookingByPack", query="SELECT p FROM Pack p INNER JOIN p.bookings b WHERE b.id=:id")
    
})

@Table(name = "pack", schema = "storio")
@XmlRootElement
public class Pack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "pack")
    private List<Item> items;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePackAdd;
    @Enumerated(EnumType.STRING)
    private PackState state;
    @Enumerated(EnumType.STRING)
    private PackType type;
    @ManyToMany
    @JoinTable(name = "booking_pack", schema = "storio")
    private List<Booking> bookings;

    public Pack() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getDatePackAdd() {
        return datePackAdd;
    }

    public void setDatePackAdd(Date datePackAdd) {
        this.datePackAdd = datePackAdd;
    }

    public PackState getState() {
        return state;
    }

    public void setState(PackState state) {
        this.state = state;
    }

    public PackType getType() {
        return type;
    }

    public void setType(PackType type) {
        this.type = type;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.items);
        hash = 53 * hash + Objects.hashCode(this.datePackAdd);
        hash = 53 * hash + Objects.hashCode(this.state);
        hash = 53 * hash + Objects.hashCode(this.type);
        hash = 53 * hash + Objects.hashCode(this.bookings);
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
        final Pack other = (Pack) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        if (!Objects.equals(this.datePackAdd, other.datePackAdd)) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.bookings, other.bookings)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", name=" + name + ", description=" + description + ", items=" + items + ", datePackAdd=" + datePackAdd + ", state=" + state + ", type=" + type + ", bookings=" + bookings + '}';
    }
    
    

   

}
