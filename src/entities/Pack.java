package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Pack",schema="storio")
public class Pack implements Serializable {
    @Id
    private Integer id;
    private String description;
    @OneToMany
    private List<Item> items;
    private PackState state;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
    private PackType type;
    @ManyToMany
    private List<Booking> bookings;

    public Pack() {
        super();
    }
}
