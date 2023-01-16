package service;

import entities.Item;
import exceptions.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
 * RESTful service for the class Item.
 * @author Nicolás Rodríguez
 */
@Stateless
@Path("entities.item")
public class ItemFacadeREST {
    /**
     * EJB Object implementing business logic.
     */
    @EJB(beanName = "EJBStorioManager1")
    private StorioManagerLocal ejb;
    /**
     * Logger for this class
     */
    private static final Logger LOGGER=Logger.getLogger(ItemFacadeREST.class.getName());
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createItem(Item item) {
        try {
            LOGGER.log(Level.INFO, "Creating Item {0}.", item.getId());
            ejb.createItem(item);
        } catch(CreateException e) {
            LOGGER.log(Level.SEVERE, "Could not create the Item {0}.\n{1}", new Object[]{item.getId(), e.getLocalizedMessage()});
            throw new InternalServerErrorException();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateItem(Item item) {
        try {
            LOGGER.log(Level.INFO, "Updating Item {0}.", item.getId());
            ejb.updateItem(item);
        } catch(UpdateException e) {
            LOGGER.log(Level.SEVERE, "Could not update item {0}.{1}", new Object[]{item.getId(), e.getLocalizedMessage()});
            throw new InternalServerErrorException();
        }
    }

    @DELETE
    @Path("{id}")
    public void removeItem(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Removing Item {0}", id);
            Item item = new Item();
            item.setId(id);
            ejb.removeItem(item);
        } catch(RemoveException e) {
            LOGGER.log(Level.SEVERE, "Could not remove Item {0}", id);
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item find(@PathParam("id") Integer  id) {
        try {
            LOGGER.log(Level.INFO, "Finding info about Item {0}.", id);
            return ejb.findItem(id);
        } catch (FindException e) {
            LOGGER.log(Level.SEVERE, "Could not find the Item.{0}", e.getLocalizedMessage());
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findAllItems() {
        try {
            LOGGER.info("Reading the data from all Items.");
            return ejb.findAllItems();
        }catch(FindException e) {
            LOGGER.severe("Error when listing all of the data from Items.");
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Path("/AllItemsWithoutPack")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findAllItemsWIthoutPack() {
        try {
            LOGGER.log(Level.INFO, "Reading the data from Items without Pack.");
            return ejb.findAllItemsWIthoutPack();
        }catch(FindException e) {
            LOGGER.log(Level.SEVERE, "Error when listing all the Items without Pack.");
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Path("{id}/AllModelsItems")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findAllModelsItems(@PathParam("id")Integer id) {
        try {
            LOGGER.log(Level.INFO, "Reading the data from Model {0} Items.", id);
            return ejb.findAllModelsItems(id);
        }catch(FindException e) {
            LOGGER.log(Level.SEVERE, "Error when listing all of the data from Model {0}.", id);
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Path("{id}/AllPacksItems")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> findAllPacksItems(@PathParam("id")Integer id) {
        try {
            LOGGER.log(Level.INFO, "Reading the data from Pack {0} Items.", id);
            return ejb.findAllPacksItems(id);
        }catch(FindException e) {
            LOGGER.log(Level.SEVERE, "Error when listing all of the data from Pack {0}.", id);
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        try {
            LOGGER.info("Counting all Items.");
            return ejb.countItems();
        } catch (FindException e) {
            LOGGER.info("Error when counting all Items.");
            throw new InternalServerErrorException();
        }
        
    }
}
