/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.logging.Logger;
import entities.Pack;
import entities.PackState;
import entities.PackType;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
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
@Path("entities.pack")
public class PackFacadeREST{

    @PersistenceContext(unitName = "StorioPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger("javafxserverside");

    @EJB
    private StorioManagerLocal ejb;

    /**
     * create a pack
     * @param pack
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Pack pack) {
        try {
            LOGGER.log(Level.INFO, "PackREST service: create.", pack);
            ejb.createPack(pack);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception creating pack", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * update a pack by id
     * @param pack
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Pack pack) {
        try {
            LOGGER.log(Level.INFO, "PackREST service: update.", pack);
            ejb.updatePack(pack);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception updating pack", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * delete a pack by id
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "PackREST service: delete Pack.", id);
            ejb.deletePack(ejb.findPackById(id));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception updating pack", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * fins a pack by id and return the pack
     * 
     * @param id
     * @return pack
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON })
    public Pack findPackById(@PathParam("id") Integer id) {
        Pack pack = null;
        try {
            LOGGER.log(Level.INFO, "PackREST service: find pack", id);
            pack = ejb.findPackById(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception reading pack by id, {0}", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return pack;
    }

    /**
     * search for all the pack and return a list of them
     * 
     * @return packs
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pack> findAll() {
        List<Pack> packs = null;
        try {
            LOGGER.log(Level.INFO, "PackREST service: find all packs.");
            packs = ejb.findALlPacks();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception reading all packs, {0}", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return packs;
    }

    /**
     * search for packs by state and return a list of them
     * 
     * @param state
     * @return packs
     */
    @GET
    @Path("state/{state}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pack> findPacksByState(@PathParam("state") String state) {
        List<Pack> packs = null;
        try {
            LOGGER.log(Level.INFO, "PackREST service: find packs by state.");
            packs = ejb.findPacksByState(PackState.valueOf(state));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception reading all packs available", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return packs;
    }
    
     /**
     * search for packs by type and return a list of them
     * 
     * @param type
     * @return packs
     */
    @GET
    @Path("type/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pack> findPacksByType(@PathParam("type") String type) {
        List<Pack> packs=null;
        try {
            LOGGER.log(Level.INFO,"PackREST service: find packs by type.");
            packs=ejb.findPacksByType(PackType.valueOf(type));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception reading all packs by type", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return packs;
    }
    
    /**
     * search for packs by type and return a list of them
     * 
     * @param id
     * @return packs
     */
    @GET
    @Path("booking/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pack> listBookingByPack(@PathParam("id") Integer id) {
        List<Pack> packs=null;
        try {
            LOGGER.log(Level.INFO,"PackREST service: find packs by type.");
            packs=ejb.listPacksByBooking(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "PackREST service: Exception reading all packs by type", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return packs;
    }
    
     
 
    protected EntityManager getEntityManager() {
        return em;
    }

}
