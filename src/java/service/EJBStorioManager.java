/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Booking;
import entities.Pack;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class EJBStorioManager implements StorioManagerLocal{
    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "StorioPU")
    private EntityManager em;
    
    
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
            System.out.println("Antes");
            bookings = em.createNamedQuery("findAllBookings").getResultList();
            System.out.println("Despues");
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
    public Booking findBookingById(Long id) {
        Booking booking = null;
        try{
            booking = em.find(Booking.class, id);
        }catch(Exception e){
        }
        return booking;
    }
    
    /**
     * This method gets a list with all approved bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findApprovedBookings() {
        List<Booking> bookings = null;
        String state = "APPROVED";
        try{
            bookings = em.createNamedQuery("findApprovedBookings").setParameter("bookingState", state).getResultList();
        }catch(Exception e){
        }
        return bookings;
    }
    
    /**
     * This method gets a list with all handed bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findHandedBookings() {
        List<Booking> bookings = null;
        String state = "HANDED";
        try{
            bookings = em.createNamedQuery("findHandedBookings").setParameter("bookingState", state).getResultList();
        }catch(Exception e){
        }
        return bookings;
    }

    /**
     * This method gets a list with all bookings of an user in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findUserOwnBookings(Long id) {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findUserOwnBookings").getResultList();
        }catch(Exception e){
        }
        return bookings;
    }

    /**
     * This method gets a number of how many bookings are in the data store. 
     * @return A Integer
     */
    @Override
    public Integer countBookings() {
        Integer numBookings = null;
        try{
            numBookings = (Integer) em.createNamedQuery("countBookings").getSingleResult();
        }catch(Exception e){
        }
        return numBookings;
    }

    /**
     * This method gets a list with all packs asociated to a booking.
     * @param id
     * @return A List of Pack entity objects..
     */
    @Override
    public List<Pack> listPacksForBooking(Long id) {
        List<Pack> packs = null;
        try{
            packs = em.createNamedQuery("findPacksForBooking").setParameter("bookingId", id).getResultList();
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
    
    

    

    
    
}