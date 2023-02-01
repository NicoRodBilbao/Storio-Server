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
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;

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

	@GET
	@Path("login/{login}/{password}")
	public Response login(@PathParam("login") String login, @PathParam("password") String password) {
		try {
			if(ejb.loginUser(login, password))
				return Response.ok().build();
		} catch (FindException ex) {
			Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.serverError().build();
	}

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(User entity) {
        try {
            ejb.createUser(entity);
			return Response.ok().build();
        } catch (CreateException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
		return Response.serverError().build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, User entity) {
        try {
			User oldUser = this.find(id);
			entity = ejb.hashPassword(entity);
			if(!oldUser.getPassword().equals(entity.getPassword()))
				this.sendEmailOnPasswdChange(entity.getEmail());
            ejb.editUser(entity);
			return Response.ok().build();
        } catch (UpdateException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
		return Response.serverError().build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            try {
                ejb.removeUser(ejb.findUserById(id));
				return Response.ok().build();
            } catch (RemoveException ex) {
                Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
		return Response.serverError().build();
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
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findByLogin(@PathParam("login") String login) {
        User user = null;
        try {
            user = ejb.findUserByLogin(login);
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

	private String generatePasswd() {
		Random r = new Random();
		IntStream nums = r.ints(12, 'A', 'z');
		return nums
				.mapToObj(n -> Character.toString((char) n))
				.collect(Collectors.joining());
	}
    
    @GET
    @Path("mail/sendMail/{email}")
    public void sendEmailOnPasswdChange(@PathParam("email") String email) {
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
                message.setSubject("Storio: Password reset");
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting body");
                message.setText(""
						+ "Hello " + user.getFullName() + "\n"
						+ "Your password has been changed succesfully!\n"
						+ "This is email has been sent automatically, please do not reply\n"
				);
                Logger.getLogger(UserFacadeREST.class.getName()).info("Transporting");
                Transport t = session.getTransport("smtp");
                t.connect("smtp.gmail.com","storio.service@gmail.com", password);
                Logger.getLogger(UserFacadeREST.class.getName()).info("Sending");
                t.sendMessage(message, message.getAllRecipients());
                Logger.getLogger(UserFacadeREST.class.getName()).info("Closing");
                t.close();
            } catch (MessagingException e) {
                Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, "a", e);
            }

        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("mail/resetPassword/{email}")
    public void resetPassword(@PathParam("email") String email) {
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
				String newPasswd = this.generatePasswd();
				user.setPassword(newPasswd);
				ejb.editUser(user);
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting message");
                message.setFrom(new InternetAddress((String) properties.get("mail.smtp.user")));
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting recipient");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting subject");
                message.setSubject("Storio: Password reset");
                Logger.getLogger(UserFacadeREST.class.getName()).info("Setting body");
                message.setText(""
						+ "Hello " + user.getFullName() + "\n"
						+ "As per your request your password has been reset!\n"
						+ "Here is your new password:\n"
						+ newPasswd + "\n"
						+ "You may change this password from within the application\n"
						+ "This is email has been sent automatically, please do not reply\n"
				);
                Logger.getLogger(UserFacadeREST.class.getName()).info("Transporting");
                Transport t = session.getTransport("smtp");
                t.connect("smtp.gmail.com","storio.service@gmail.com", password);
                Logger.getLogger(UserFacadeREST.class.getName()).info("Sending");
                t.sendMessage(message, message.getAllRecipients());
                Logger.getLogger(UserFacadeREST.class.getName()).info("Closing");
                t.close();
            } catch (MessagingException e) {
                Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, "a", e);
            } catch (UpdateException ex) {
				Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, "Error changing user's password", ex);
			}

        } catch (FindException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
