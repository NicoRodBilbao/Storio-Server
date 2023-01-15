/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import entities.UserPrivilege;
import entities.UserStatus;
import java.util.List;
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
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {

	@PersistenceContext(unitName = "StorioPU")
	private EntityManager em;

	public UserFacadeREST() {
		super(User.class);
	}

	@EJB
	private StorioManagerLocal ejb;

	@POST
	@Override
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void create(User entity) {
		ejb.createUser(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void edit(@PathParam("id") Integer id, User entity) {
		ejb.editUser(entity);
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		ejb.removeUser(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User find(@PathParam("id") Integer id) {
		return ejb.findUserById(id);
	}

	@GET
	@Path("email/{email}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User findByEmail(@PathParam("email") String email) {
		return ejb.findUserById(email);
	}

	@GET
	@Path("phone/{phone}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User findByPhone(@PathParam("phone") Integer phone) {
		return ejb.findUserById(phone);
	}

	@GET
	@Override
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> findAll() {
		return ejb.findAllUsers();
	}

	@GET
	@Path("privilege/{privilege}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> findUsersByPrivilege(@PathParam("privilege") UserPrivilege privilege) {
		return ejb.findUsersByPrivilege(privilege);
	}

	@GET
	@Path("status/{status}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> findUsersByStatus(@PathParam("status") UserStatus status) {
		return ejb.findUsersByStatus(status);
	}

	@GET
	@Path("{from}/{to}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(new int[]{from, to});
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
