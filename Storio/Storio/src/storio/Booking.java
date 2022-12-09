package storio;

import java.util.Date;
import java.util.List;

public class Booking {
    private Integer id;
    private Client user;
    private List<Pack> packs;
    private Date startDate;
    private Date endDate;
    private String description;
    private BookingState state;

    public Booking() {
        super();
    }
}
