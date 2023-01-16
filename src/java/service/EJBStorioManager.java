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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class EJBStorioManager implements StorioManagerLocal {

    private static final Logger LOGGER = Logger.getLogger("javafxserverside");

    @PersistenceContext
    private EntityManager em;

    /**
     * Find and list all the packs
     *
     * @return packs
     */
    @Override
    public List<Pack> findALlPacks() {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listAllPacks").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception reading all packs:", e.getMessage());
        }
        return packs;
    }

    /**
     * Find and list the packs that are available
     *
     * @param state
     * @return packs
     */
    @Override
    public List<Pack> findPacksByState(PackState state) {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listPacksByState").setParameter("state", state).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception reading all packs available:", e.getMessage());
        }
        return packs;
    }

    /**
     * Find and list packs by a Type of pack
     *
     * @param type
     * @return packs
     */
    @Override
    public List<Pack> findPacksByType(PackType type) {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listPacksByType").setParameter("type", type).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception reading all packs available:", e.getMessage());
        }
        return packs;
    }

    /**
     * Find and list packs by a Type of pack
     *
     * @param id
     * @return packs
     */
    @Override
    public List<Pack> listBookingByPack(Integer id) {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listPacksByType").setParameter("id", id).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception reading all packs available:", e.getMessage());
        }
        return packs;
    }

    
    /**
     * Create a pack
     *
     * @param pack
     */
    @Override
    public void createPack(Pack pack) {
        try {
            em.persist(pack);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception creating pack.",
                    e.getMessage());
        }
    }

    /**
     * Returns the pack updated
     *
     * @param pack
     */
    @Override
    public void updatePack(Pack pack) {
        try {
            if(!em.contains(pack))
                em.merge(pack);
            em.flush();
            LOGGER.info("PackManager: Pack Updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception update user.",
                    e.getMessage());
        }
    }

    /**
     * Find a pack and remove it
     *
     * @param pack
     */
    @Override
    public void deletePack(Pack pack) {
        try {
            em.remove(em.merge(pack));
            LOGGER.info("PackManager: Pack deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception deleting user.", e.getMessage());
        }
    }

    /**
     * Find a pack by it's id
     *
     * @param id
     * @return pack
     */
    @Override
    public Pack findPackById(Integer id) {
        Pack pack = null;
        try {
            LOGGER.info("PackManager: Finding pack by login.");
            pack = em.find(Pack.class, id);
            if (pack != null) {
                LOGGER.log(Level.INFO, "PackManager: pack", pack.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception Finding pack by login:", e.getMessage());
        }
        return pack;
    }
    
    /**
     * This method creates a new booking in the data store.
     * @param booking
     */
    @Override
    public void createBooking(Booking booking){
        try{
            em.persist(booking);
        }catch(Exception e){
            
        }
    }
    
    /**
     * This method gets a list with all bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findAllBookings() {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findAllBookings").getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bookings;
    }
    
    /**
     * This method gets a booking with a selected id in the data store. 
     * @return A Booking entity object
     */
    @Override
    public Booking findBookingById(Integer id) {
        Booking booking = null;
        try{
            booking = em.find(Booking.class, id);
        }catch(Exception e){
        }
        return booking;
    }

    /**
     * This method gets a list with all bookings of an user in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findUserOwnBookings(Integer id) {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findUserOwnBookings").getResultList();
        }catch(Exception e){
        }
        return bookings;
    }

    /**
     * This method gets a list with all packs asociated to a booking.
     * @param id
     * @return A List of Pack entity objects..
     */
    @Override
    public List<Pack> listPacksForBooking(Integer id) {
        List<Pack> packs = null;
        try{
            Booking booking = em.find(Booking.class, id);
            List<Pack> listAllPacks = em.createNamedQuery("listAllPacks").setParameter("bookingId", id).getResultList();
            packs = new ArrayList<Pack>();
            for (Pack p: listAllPacks){
                if(p.getBookings().contains(booking)){
                    packs.add(p);
                }
            }
        }catch(Exception e){
        }
        return packs;
    }

    /**
     * This method updates a booking data in the data store.
     * @param booking The Booking entity object containing modified account data.
     */
    @Override
    public void updateBooking(Booking booking) {
         try{
            if(!em.contains(booking))
                em.merge(booking);
            em.flush();
        }catch(Exception e){
        }
    }

    /**
     * This method removes an account from the data store.
     * @param booking The Booking entity object to be removed.
     */
    @Override
    public void removeBooking(Booking booking) {
        try{
            em.remove(em.merge(booking));
        }catch(Exception e){
        }
    }

    @Override
    public List<Booking> findBookingsByState(BookingState state) {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findBookingsByState").setParameter("bookingState", state).getResultList();
        }catch(Exception e){
        }
        return bookings;
    }

    
}
