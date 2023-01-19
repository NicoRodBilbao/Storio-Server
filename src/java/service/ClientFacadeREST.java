/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Client;
import exceptions.CreateException;
import exceptions.FindException;
import exceptions.RemoveException;
import exceptions.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author Joana
 */
@Stateless
@Path("entities.client")
public class ClientFacadeREST {

	@PersistenceContext(unitName = "StorioPU")
	private EntityManager em;

	@EJB
	private StorioManagerLocal ejb;

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void create(Client entity) {
            try {
                ejb.createClient(entity);
            } catch (CreateException ex) {
                Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void edit(@PathParam("id") Integer id, Client entity) {
            try {
                ejb.editClient(entity);
            } catch (UpdateException ex) {
                Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
            try {
                try {
                    ejb.removeClient(ejb.findClientById(id));
                } catch (RemoveException ex) {
                    Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FindException ex) {
                Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Client find(@PathParam("id") Integer id) {
            Client client = null;
            try {
                client = ejb.findClientById(id);
            } catch (FindException ex) {
                Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
            return client;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Client> findAll() {
            List<Client> clients = null;
            try {
                clients = ejb.findAllClients();
            } catch (FindException ex) {
                Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
            return clients;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String countREST() {
            String count  = null;
            try {
                count = String.valueOf(ejb.countClients());
            } catch (FindException ex) {
                Logger.getLogger(ClientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
	}

	protected EntityManager getEntityManager() {
		return em;
	}

}
