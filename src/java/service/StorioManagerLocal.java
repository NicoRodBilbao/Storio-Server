/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Booking;
import entities.BookingState;
import entities.Pack;
import entities.PackState;
import entities.PackType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 2dam
 */
@Local
public interface StorioManagerLocal {

    /**
     * Search all pack created and return the list of them
     *
     * @return packs
     */
    public List<Pack> findALlPacks();
    
    /**
     * Search packs by the state and return the list of them
     *
     * @param state
     * @return packs
     */
    public List<Pack> findPacksByState(PackState state);
    
    /**
     * Search packs by the state and return the list of them
     *
     * @param id
     * @return packs
     */
    public List<Pack> listPacksByBooking(Integer id);

    /**
     * Search packs by the type of pack and return the list of them
     *
     * @param type
     * @return packs
     */
    public List<Pack> findPacksByType(PackType type);

    /**
     * Create a new pack
     *
     * @param pack
     */
    public void createPack(Pack pack);

    /**
     * update the pack
     *
     * @param pack
     */
    public void updatePack(Pack pack);

    /**
     * by the id find one pack
     * @param id
     * @return pack
     */
    public Pack findPackById(Integer id);

    /**
     * delete the pack
     * @param pack 
     */
    public void deletePack(Pack pack);

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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
     * @param id
     * @return A Booking entity object
     */
    public Booking findBookingById(Integer id);
    
    /**
     * This method gets a list with all handed bookings in the data store. 
     * @param state
     * @return A List of Booking entity objects..
     */
    public List<Booking> findBookingsByState(BookingState state);
    
    /**
     * This method gets a list with all bookings of one user in the data store. 
     * @param id
     * @return A List of Booking entity objects..
     */
    public List<Booking> findUserOwnBookings(Integer id);
    
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
