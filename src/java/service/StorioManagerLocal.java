/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.*;
import exceptions.*;
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
     * @throws exceptions.FindException
     */
    public List<Pack> findALlPacks() throws FindException;
    
    /**
     * Search packs by the state and return the list of them
     *
     * @param state
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> findPacksByState(PackState state) throws FindException;
    
    /**
     * Search packs by the state and return the list of them
     *
     * @param id
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> listPacksByBooking(Integer id) throws FindException;

    /**
     * Search packs by the type of pack and return the list of them
     *
     * @param type
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> findPacksByType(PackType type) throws FindException;

    /**
     * Create a new pack
     *
     * @param pack
     * @throws exceptions.CreateException
     */
    public void createPack(Pack pack) throws CreateException;

    /**
     * update the pack
     *
     * @param pack
     * @throws exceptions.UpdateException
     */
    public void updatePack(Pack pack) throws UpdateException;

    /**
     * by the id find one pack
     * @param id
     * @return pack
     * @throws exceptions.FindException
     */
    public Pack findPackById(Integer id) throws FindException;

    /**
     * delete the pack
     * @param pack 
     * @throws exceptions.RemoveException 
     */
    public void deletePack(Pack pack) throws RemoveException;

    
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
