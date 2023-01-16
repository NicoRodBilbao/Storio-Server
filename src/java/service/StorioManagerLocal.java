package service;

import entities.*;
import exceptions.*;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for StorioManager EJB.
 * @author Nicolás Rodríguez
 */
@Local
public interface StorioManagerLocal { 
    public void createReport (Report report) throws CreateException;
    
    public void createItem (Item item) throws CreateException;
    
    public void createModel (Model model) throws CreateException;
    
    public void updateReport(Report report) throws UpdateException;
    
    public void updateItem(Item item) throws UpdateException;
    
    public void updateModel(Model model) throws UpdateException;
    
    public void removeReport(Report report) throws RemoveException;
    
    public void removeItem(Item item) throws RemoveException;
    
    public void removeModel(Model model) throws RemoveException;
    
    public Report findReport(Integer id) throws FindException;
    
    public List<Report> findAllItemsReports(Integer id) throws FindException;
    
    public Item findItem(Integer id) throws FindException;
    
    public Model findModel(Integer id) throws FindException;
    
    public List<Report> findAllReports() throws FindException;
    
    public List<Item> findAllItems() throws FindException;
    public List<Item> findAllItemsWIthoutPack() throws FindException;
        
    public List<Item> findAllModelsItems(Integer id) throws FindException;
    
    public List<Item> findAllPacksItems(Integer id) throws FindException;
    
    public List<Model> findAllModels() throws FindException;
    
    public Integer countReports() throws FindException;
    
    public Integer countItems() throws FindException;
    
    public Integer countModels() throws FindException;

    /**
     * This method creates a new booking in the data store.
     * @param booking
     */
    public void createBooking (Booking booking);
    
    /**
     * This method gets a list with all bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    public List<Booking> findAllBookings();
    
    /**
     * This method gets a booking with a selected id in the data store. 
     * @return A Booking entity object
     */
    public Booking findBookingById(Integer id);
    
    /**
     * This method gets a list with all handed bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    public List<Booking> findBookingsByState(BookingState state);
    
    /**
     * This method gets a list with all bookings of one user in the data store. 
     * @return A List of Booking entity objects..
     */
    public List<Booking> findUserOwnBookings(Long id);
    
    /**
     * This method gets a list with all packs asociated to a booking.
     * @return A List of Pack entity objects..
     */
    public List<Pack> listPacksForBooking(Long id);
    /**
     * This method updates a booking data in the data store.
     * @param booking The Booking entity object containing modified account data.
     */
    public void updateBooking(Booking booking);
    
    /**
     * This method removes an account from the data store.
     * @param booking The Booking entity object to be removed.
     */
    public void removeBooking(Booking booking);
}
