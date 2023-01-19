package service;

import entities.Report;
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
 * RESTful service for the class Report.
 * @author Nicolás Rodríguez
 */
@Stateless
@Path("entities.report")
public class ReportFacadeREST {
    /**
     * EJB Object implementing business logic.
     */
    @EJB(beanName = "EJBStorioManager1")
    private StorioManagerLocal ejb;
    /**
     * Logger for this class
     */
    private static final Logger LOGGER=Logger.getLogger(ReportFacadeREST.class.getName());
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createReport(Report report) {
        try {
            LOGGER.log(Level.INFO, "Creating Report {0}.", report.getId());
            ejb.createReport(report);
        } catch(CreateException e) {
            LOGGER.log(Level.SEVERE, "Could not create the Report {0}.\n{1}", new Object[]{report.getId(), e.getLocalizedMessage()});
            throw new InternalServerErrorException();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateReport(Report report) {
        try {
            LOGGER.log(Level.INFO, "Updating Report {0}.", report.getId());
            ejb.updateReport(report);
        } catch(UpdateException e) {
            LOGGER.log(Level.SEVERE, "Could not update report {0}.{1}", new Object[]{report.getId(), e.getLocalizedMessage()});
            throw new InternalServerErrorException();
        }
    }

    @DELETE
    @Path("{id}")
    public void removeReport(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Removing report {0}", id);
            Report rep = new Report();
            rep.setId(id);
            ejb.removeReport(rep);
        } catch(RemoveException e) {
            LOGGER.log(Level.SEVERE, "Could not remove Report {0}", id);
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Report find(@PathParam("id") Integer  id) {
        try {
            LOGGER.log(Level.INFO, "Finding info about report {0}.", id);
            return ejb.findReport(id);
        } catch (FindException e) {
            LOGGER.log(Level.SEVERE, "Could not find the Report.{0}.", e.getLocalizedMessage());
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        try {
            LOGGER.info("Reading the data from all Reports.");
            return ejb.findAllReports();
        }catch(FindException e) {
            LOGGER.severe("Error when listing all of the data from reports.");
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        try{
            LOGGER.info("Counting all the Reports.");
            return ejb.countReports();
        }catch(FindException e){
            LOGGER.info("Error when counting all the Reports. "+e.getLocalizedMessage());
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Path("/{id}/listItem") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAllItemsReports(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Reading the data from all Reports from Item {0}.", id);
        return ejb.findAllItemsReports(id);
         }catch(FindException e) {
            LOGGER.log(Level.SEVERE, "Error when listing all of the data of the Reports from Item {0}.", id);
            throw new InternalServerErrorException();
        }
    }
}