/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Booking;
import entities.BookingState;
import entities.Pack;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 2dam
 */
@Path("entities.booking")
public class BookingFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private StorioManagerLocal ejb;
    /**
     * Logger for this class.
     */
    private Logger LOGGER = Logger.getLogger(BookingFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createBooking(Booking booking) {
        try {
            LOGGER.log(Level.INFO, "Creating booking {0}", booking.getId());
            ejb.createBooking(booking);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateBooking(Booking booking) {
        try {
            LOGGER.log(Level.INFO, "Updating booking {0}", booking.getId());
            ejb.updateBooking(booking);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void removeBooking(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Deleting booking {0}", id);
            ejb.removeBooking(ejb.findBookingById(id));
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Booking find(@PathParam("id") Integer id) {
        Booking booking = null;
        try {
            LOGGER.log(Level.INFO, "Reading data for booking {0}", id);
            booking = ejb.findBookingById(id);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
        return booking;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> findAll() {
        List<Booking> bookings = null;
        try {
            LOGGER.log(Level.INFO, "Reading data for all bookings");
            bookings = ejb.findAllBookings();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
        return bookings;
    }
    
    @GET
    @Path("findPacksForBooking/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pack> findPacksForBooking(@PathParam("id") Integer id) {
        List<Pack> packs = null;
         try {
            LOGGER.log(Level.INFO,"Reading pack data for booking{0}",id);
            packs = ejb.listPacksByBooking(id);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());       
        }
        return packs;
    }
    
    @GET
    @Path("findBookingsForUser/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> findUserOwnBookings(@PathParam("id") Integer id) {
        List<Booking> bookings = null;
         try {
            LOGGER.log(Level.INFO,"Reading booking data for user{0}",id);
            bookings = ejb.findUserOwnBookings(id);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());       
        }
        return bookings;
    }

     @GET
    @Path("findBookingsByState/{state}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> findBookingsByState(@PathParam("state") String state) {
        List<Booking> bookings = null;
         try {
            LOGGER.log(Level.INFO,"Reading pack data for booking {0}",state);
            bookings = ejb.findBookingsByState(BookingState.valueOf(state));
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());       
        }
        return bookings;
    }
}