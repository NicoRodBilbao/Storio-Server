package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pack", schema = "storio")
@XmlRootElement
public class Pack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String description;
    @OneToMany(mappedBy = "pack")
    private List<Item> items;
    @Enumerated(EnumType.STRING)
    private PackState state;
    @Enumerated(EnumType.STRING)
    private PackType type;

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

    @XmlTransient
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

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.items);
        hash = 59 * hash + Objects.hashCode(this.state);
        hash = 59 * hash + Objects.hashCode(this.type);
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
        return true;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", description=" + description + ", items=" + items + ", state=" + state + ", type=" + type + ", bookings="+ '}';
    }

}
