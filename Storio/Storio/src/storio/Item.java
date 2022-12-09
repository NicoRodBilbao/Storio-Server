package storio;

import java.util.Date;
import java.util.List;

public class Item {
    private Integer id;
    private Model model;
    private Date dateAdded;
    private String issues;
    private List<Report> report;
    private Pack pack;

    public Item() {
        super();
    }
}
