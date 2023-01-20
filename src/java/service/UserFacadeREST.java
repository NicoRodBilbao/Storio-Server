/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.sun.mail.imap.IMAPBodyPart;
import entities.User;
import entities.UserPrivilege;
import entities.UserStatus;
import exceptions.CreateException;
import exceptions.FindException;
import exceptions.RemoveException;
import exceptions.UpdateException;
import java.util.List;
import java.util.Properties;
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
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
            users = ejb.findUsersByPrivilege(privilege);
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
        return null;
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

    @GET
    @Path("mail/sendMail/{email}")
    public void sendEmail(@PathParam("email") String email) {
        String smtp_host = "smtp.gmail.com";
        Integer smtp_port = 587;
        User user = null;
        String password = "evyyadvsnksgsujh";
        try {
            Logger.getLogger(UserFacadeREST.class.getName()).info("Finding email:" + email);
            user = ejb.findUserByEmail(email);
            Logger.getLogger(UserFacadeREST.class.getName()).info("Setting properties");

            Session session;
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.user", "storio.service@gmail.com");
            properties.put("mail.smtp.clave", password);
            properties.put("mail.smtp.auth", "true");
            
            Logger.getLogger(UserFacadeREST.class.getName()).info("Opening session");
            session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            Logger.getLogger(UserFacadeREST.class.getName()).info("A");

            try {
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting message");
                message.setFrom(new InternetAddress((String) properties.get("mail.smtp.user")));
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting recipient");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting subject");
                message.setSubject("Prueba");
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting body");
                message.setText("Sexo");
                Logger.getLogger(UserFacadeREST.class.getName()).info("Transporting");
                Transport t = session.getTransport("smtp");
                t.connect("smtp.gmail.com","storio.service@gmail.com", password);
                Logger.getLogger(UserFacadeREST.class.getName()).info("Sending");
                t.sendMessage(message, message.getAllRecipients());
                Logger.getLogger(UserFacadeREST.class.getName()).info("Closing");
                t.close();
            } catch (MessagingException me) {
                //Aqui se deberia o mostrar un mensaje de error o en lugar
                //de no hacer nada con la excepcion, lanzarla para que el modulo
                //superior la capture y avise al usuario con un popup, por ejemplo.
                return;
            }

        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, "a", ex);
        }
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
