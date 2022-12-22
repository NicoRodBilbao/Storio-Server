/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Booking;
import entities.Pack;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 2dam
 */
@Local
public interface StorioManagerLocal {
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
    public Booking findBookingById(Long id);
    
    /**
     * This method gets a list with all aproved bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    public List<Booking> findAprovedBookings();
    
    /**
     * This method gets a list with all handed bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    public List<Booking> findHandedBookings();
    
    /**
     * This method gets a list with all bookings of one user in the data store. 
     * @return A List of Booking entity objects..
     */
    public List<Booking> findUserOwnBookings(Long id);
    
    /**
     * This method gets a number of how many bookings are in the data store. 
     * @return A Integer
     */
    public Integer countBookings();
    
    /**
     * This method gets a list with all packs asociated to a booking.
     * @return A List of Pack entity objects..
     */
    //public List<Pack> listPacksForBooking(Long id);
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
