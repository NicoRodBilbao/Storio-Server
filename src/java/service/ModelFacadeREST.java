package service;

import entities.Model;
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
 * RESTful service for the class Model.
 * @author Nicolás Rodríguez
 */
@Stateless
@Path("entities.model")
public class ModelFacadeREST {
     /**
     * EJB Object implementing business logic.
     */
    @EJB(beanName = "EJBStorioManager1")
    private StorioManagerLocal ejb;
    /**
     * Logger for this class
     */
    private static final Logger LOGGER=Logger.getLogger(ModelFacadeREST.class.getName());
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createModel(Model model) {
        try {
            LOGGER.log(Level.INFO, "Creating Model {0}.", model.getId());
            ejb.createModel(model);
        } catch(CreateException e) {
            LOGGER.log(Level.SEVERE, "Could not create the Model {0}.\n{1}", new Object[]{model.getId(), e.getLocalizedMessage()});
            throw new InternalServerErrorException();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateModel(Model model) {
        try {
            LOGGER.log(Level.INFO, "Updating Model {0}.", model.getId());
            ejb.updateModel(model);
        } catch(UpdateException e) {
            LOGGER.log(Level.SEVERE, "Could not update model {0}.{1}", new Object[]{model.getId(), e.getLocalizedMessage()});
            throw new InternalServerErrorException();
        }
    }

    @DELETE
    @Path("{id}")
    public void removeModel(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Removing Model {0}", id);
            Model model = new Model();
            model.setId(id);
            ejb.removeModel(model);
        } catch(RemoveException e) {
            LOGGER.log(Level.SEVERE, "Could not remove Model {0}", id);
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Model find(@PathParam("id") Integer  id) {
        try {
            LOGGER.log(Level.INFO, "Finding info about Model {0}.", id);
            return ejb.findModel(id);
        } catch (FindException e) {
            LOGGER.log(Level.SEVERE, "Could not find the Model.{0}", e.getLocalizedMessage());
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Model> findAll() {
        try {
            LOGGER.info("Reading the data from all Models.");
            return ejb.findAllModels();
        }catch(FindException e) {
            LOGGER.severe("Error when listing all of the data from Models.");
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        try {
            LOGGER.info("Counting all the Models.");
            return ejb.countModels();
        } catch (FindException e) {
            LOGGER.info("Error when counting all Models.");
            throw new InternalServerErrorException();
        }
    }
}
