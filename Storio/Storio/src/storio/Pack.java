package storio;

import java.util.List;

public class Pack {
    private Integer id;
    private String description;
    private List<Item> items;
    private PackState state;
    private PackType type;
    private List<Booking> bookings;

    public Pack() {
        super();
    }
}
