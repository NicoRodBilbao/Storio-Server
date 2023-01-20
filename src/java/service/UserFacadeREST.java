/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import entities.UserPrivilege;
import entities.UserStatus;
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
@Path("entities.user")
public class UserFacadeREST {

    @PersistenceContext(unitName = "StorioPU")
    private EntityManager em;

    @EJB
    private StorioManagerLocal ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        try {
            ejb.createUser(entity);
        } catch (CreateException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, User entity) {
        try {
            ejb.editUser(entity);
        } catch (UpdateException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            try {
                ejb.removeUser(ejb.findUserById(id));
            } catch (RemoveException ex) {
                Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        User user = null;
        try {
            user = ejb.findUserById(id);
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findByEmail(@PathParam("email") String email) {
        User user = null;
        try {
            user = ejb.findUserByEmail(email);
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @GET
    @Path("phone/{phone}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findByPhone(@PathParam("phone") Integer phone) {
        User user = null;
        try {
            user = ejb.findUserByPhone(phone);
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        List<User> users = null; 
        try {
            users = ejb.findAllUsers();
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @GET
    @Path("privilege/{privilege}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUsersByPrivilege(@PathParam("privilege") UserPrivilege privilege) {
        List<User> users = null;
        try {
            users =  ejb.findUsersByPrivilege(privilege);
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @GET
    @Path("status/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUsersByStatus(@PathParam("status") UserStatus status) {
        List<User> users = null;
        try {
            users = ejb.findUsersByStatus(status);
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @GET
    @Path("fullName/{fullName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUsersByFullName(@PathParam("fullName") String fullName) {
        List<User> users = null;
        try {
            users = ejb.findUsersByFullName(fullName);
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        String count = null;
        try {
            count = String.valueOf(ejb.countUsers());
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
