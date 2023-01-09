/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.HistoryId;
import entities.HistoryUser;
import java.util.List;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author 2dam
 */
@Stateless
@Path("entities.historyuser")
public class HistoryUserFacadeREST extends AbstractFacade<HistoryUser> {

	@PersistenceContext(unitName = "StorioPU")
	private EntityManager em;

	private HistoryId getPrimaryKey(PathSegment pathSegment) {
		/*
		 * pathSemgent represents a URI path segment and any associated matrix parameters.
		 * URI path part is supposed to be in form of 'somePath;storio_userId=storio_userIdValue;signInId=signInIdValue'.
		 * Here 'somePath' is a result of getPath() method invocation and
		 * it is ignored in the following code.
		 * Matrix parameters are used as field names to build a primary key instance.
		 */
		entities.HistoryId key = new entities.HistoryId();
		javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
		java.util.List<String> storio_userId = map.get("storio_userId");
		if (storio_userId != null && !storio_userId.isEmpty()) {
			key.setStorio_userId(new java.lang.Integer(storio_userId.get(0)));
		}
		java.util.List<String> signInId = map.get("signInId");
		if (signInId != null && !signInId.isEmpty()) {
			key.setSignInId(new java.lang.Integer(signInId.get(0)));
		}
		return key;
	}

	public HistoryUserFacadeREST() {
		super(HistoryUser.class);
	}

	@POST
        @Override
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void create(HistoryUser entity) {
		super.create(entity);
	}

	@PUT
        @Path("{id}")
        @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void edit(@PathParam("id") PathSegment id, HistoryUser entity) {
		super.edit(entity);
	}

	@DELETE
        @Path("{id}")
	public void remove(@PathParam("id") PathSegment id) {
		entities.HistoryId key = getPrimaryKey(id);
		super.remove(super.find(key));
	}

	@GET
        @Path("{id}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public HistoryUser find(@PathParam("id") PathSegment id) {
		entities.HistoryId key = getPrimaryKey(id);
		return super.find(key);
	}

	@GET
        @Override
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<HistoryUser> findAll() {
		return super.findAll();
	}

	@GET
        @Path("{from}/{to}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<HistoryUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
