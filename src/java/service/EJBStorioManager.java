package service;

import entities.Admin;
import entities.Item;
import entities.Model;
import entities.Pack;
import entities.Report;
import entities.Booking;
import entities.BookingState;
import entities.Client;
import entities.Pack;
import entities.User;
import entities.UserPrivilege;
import entities.UserStatus;
import exceptions.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB for all entities in the application.
 * @author Nicolás Rodríguez
 */
@Stateless(name = "EJBStorioManager1")
public class EJBStorioManager implements StorioManagerLocal {

    /**
     * Entity Manager for StorioPU persistance unit.
     */
    @PersistenceContext(unitName = "StorioPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(EJBStorioManager.class.getName());

	/*

	REPORT METHODS

	*/

    @Override
    public void createReport(Report report) throws CreateException { 
        try {
            LOGGER.log(Level.INFO, "Creating Report{0}.", report.getId());
            em.persist(report);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when creating Report.\n{0}", e.getLocalizedMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateReport(Report report) throws UpdateException {
        try {
            if (!em.contains(report)) {
                LOGGER.log(Level.INFO, "Updating Report {0}.", report.getId());
            }
            em.merge(report);
            em.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when updating Report.\n{0}", e.getLocalizedMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void removeReport(Report report) throws RemoveException {
        try {
            LOGGER.log(Level.INFO, "Deleting Report {0}.", report.getId());
            em.remove(em.merge(report));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when removing Report.\n{0}", e.getLocalizedMessage());
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public Report findReport(Integer id) throws FindException {
        Report report = null;
        try {
            LOGGER.log(Level.INFO, "Finding Report {0}.", id);
            report = em.find(Report.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when finding Report.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return report;
    }

    @Override
    public List<Report> findAllReports() throws FindException {
        List<Report> reports = null;
        try {
            LOGGER.info("Listing all the Reports.");
            reports = em.createNamedQuery("findAllReports").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Reports.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return reports;
    }

    @Override
    public Integer countReports() throws FindException {
        return findAllReports().size();
    }

    @Override
    public List<Report> findAllItemsReports(Integer id) throws FindException {
        List<Report> reports = null;
        try {
            LOGGER.log(Level.INFO, "Listing all the Reports from Item {0}.", id);
            reports = em.createNamedQuery("findAllItemsReports").setParameter("item", em.find(Item.class, id)).getResultList();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Reports from Item{0}.\n'{'{1}", new Object[]{id, e.getLocalizedMessage()});
            throw new FindException(e.getMessage());
        }
        return reports;
    }

	/*

	ITEM METHODS

	*/

    @Override
    public void createItem(Item item) throws CreateException {
        try {
            LOGGER.log(Level.INFO, "Creating Item {0}.", item.getId());
            em.persist(item);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when creating Item.\n{0}", e.getLocalizedMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateItem(Item item) throws UpdateException {
        try {
            LOGGER.log(Level.INFO, "Updating Item{0}.", item.getId());
            if (!em.contains(item)) {
                em.merge(item);
            }
            em.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when updating Item.\n{0}", e.getLocalizedMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void removeItem(Item item) throws RemoveException {
        try {
            LOGGER.log(Level.INFO, "Deleting Item {0}.", item.getId());
            em.remove(em.merge(item));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when removing Item.\n{0}", e.getLocalizedMessage());
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public Item findItem(Integer id) throws FindException {
        Item item = null;
        try {
            LOGGER.log(Level.INFO, "Finding Item {0}.", id);
            item = em.find(Item.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when finding Item.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return item;
    }

    @Override
    public List<Item> findAllItems() throws FindException {
        List<Item> items = null;
        try {
            LOGGER.info("Listing all the Items.");
            items = em.createNamedQuery("findAllItems").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Items.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return items;
    }
    
    @Override
    public List<Item> findAllItemsWIthoutPack() throws FindException {
        List<Item> items = null;
        try {
            LOGGER.info("Listing all the Items without Pack.");
            items = em.createNamedQuery("findAllItemsWithoutPack").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Items without Pack.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return items;
    }
    
    @Override
        public List<Item> findAllModelsItems(Integer id) throws FindException {
        List<Item> items = null;
        try {
            LOGGER.log(Level.INFO, "Listing all the Model {0} Items.", id);
            items = em.createNamedQuery("findAllModelsItems").setParameter("model", em.find(Model.class, id)).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Model {0} Items .\n{1}", new Object[]{id, e.getLocalizedMessage()});
            throw new FindException(e.getMessage());
        }
        return items;
    }
        
    @Override
    public List<Item> findAllPacksItems(Integer id) throws FindException {
        List<Item> items = null;
        try {
            LOGGER.log(Level.INFO, "Listing all the Pack {0} Items.", id);
            items = em.createNamedQuery("findAllPacksItems").setParameter("pack", em.find(Pack.class, id)).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Pack {0} Items .\n{1}", new Object[]{id, e.getLocalizedMessage()});
            throw new FindException(e.getMessage());
        }
        return items;
    }

    @Override
    public Integer countItems() throws FindException {
        LOGGER.info("Counting all the Items.");
        return findAllItems().size();
    }

	/*

	MODEL METHODS

	*/

    
    @Override
    public void createModel(Model model) throws CreateException {
        try {
            LOGGER.log(Level.INFO, "Creating Model {0}.", model.getId());
            em.persist(model);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when creating Model.\n{0}", e.getLocalizedMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateModel(Model model) throws UpdateException {
        try {
            LOGGER.log(Level.INFO, "Updating Model {0}.", model.getId());
            if (!em.contains(model)) {
                em.merge(model);
            }
            em.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when updating Model.\n{0}", e.getLocalizedMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void removeModel(Model model) throws RemoveException {
        try {
            LOGGER.log(Level.INFO, "Deleting Model {0}.", model.getId());
            em.remove(em.merge(model));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when removing Model.\n{0}", e.getLocalizedMessage());
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public Model findModel(Integer id) throws FindException {
        Model model = null;
        try {
            LOGGER.log(Level.INFO, "Finding Model {0}.", id);
            model = em.find(Model.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when finding Model.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return model;
    }

    @Override
    public List<Model> findAllModels() throws FindException {
        List<Model> models = null;
        try {
            LOGGER.info("Listing all the Models.");
            models = em.createNamedQuery("findAllModels").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when listing all Models.\n{0}", e.getLocalizedMessage());
            throw new FindException(e.getMessage());
        }
        return models;
    }

    @Override
    public Integer countModels() throws FindException {
        LOGGER.info("Counting all the Models.");
        return findAllModels().size();
    }
     /**
     * This method creates a new booking in the data store.
     * @param booking
     */
    
	/*

	BOOKING METHODS

	*/

    @Override
    public void createBooking(Booking booking){
        try{
            em.persist(booking);
        }catch(Exception e){
            
        }
    }
    
    /**
     * This method gets a list with all bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findAllBookings() {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findAllBookings").getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bookings;
    }
    
    /**
     * This method gets a booking with a selected id in the data store. 
     * @return A Booking entity object
     */
    @Override
    public Booking findBookingById(Integer id) {
        Booking booking = null;
        try{
            booking = em.find(Booking.class, id);
        }catch(Exception e){
        }
        return booking;
    }
    /**
     * This method gets a list with all handed bookings in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findBookingsByState(BookingState state) {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findBookingsByState").setParameter("bookingState", state).getResultList();
        }catch(Exception e){
        }
        return bookings;
    }

    /**
     * This method gets a list with all bookings of an user in the data store. 
     * @return A List of Booking entity objects..
     */
    @Override
    public List<Booking> findUserOwnBookings(Long id) {
        List<Booking> bookings = null;
        try{
            bookings = em.createNamedQuery("findUserOwnedBookings").getResultList();
        }catch(Exception e){
        }
        return bookings;
    }

    /**
     * This method gets a list with all packs asociated to a booking.
     * @param id
     * @return A List of Pack entity objects..
     */
    @Override
    public List<Pack> listPacksForBooking(Long id) {
        List<Pack> packs = null;
        try{
            packs = em.createNamedQuery("findPacksForBooking").setParameter("bookingId", id).getResultList();
        }catch(Exception e){
        }
        return packs;
    }

    /**
     * This method updates a booking data in the data store.
     * @param booking The Booking entity object containing modified account data.
     */
    @Override
    public void updateBooking(Booking booking) {
         try{
            if(!em.contains(booking))
                em.merge(booking);
            em.flush();
        }catch(Exception e){
        }
    }

    /**
     * This method removes an account from the data store.
     * @param booking The Booking entity object to be removed.
     */
    @Override
    public void removeBooking(Booking booking) {
        try{
            em.remove(em.merge(booking));
        }catch(Exception e){
        }
    }

	/*

	USER METHODS

	*/

	private void userExists(User user) throws Exception {
		if(this.findUserByEmail(user.getLogin()) != null)
			throw new Exception();
	}

	@Override
	public Integer countUsers() {
		return this.findAllUsers().size();
	}

	@Override
	public void createUser(User user) {
        try{
			userExists(user);
            em.persist(user);
        } catch(Exception e){
			LOGGER.log(Level.SEVERE, "UserManager: Exception creating user: {0}", e.getMessage());
		}
	}

	@Override
	public void editUser(User user) {
		try {
			if(!em.contains(user))
                em.merge(user);
            em.flush();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception updating user: {0}", e.getMessage());
		}
	}

	@Override
	public void removeUser(User user) {
        try{
            em.remove(em.merge(user));
        } catch(Exception e){
			LOGGER.log(Level.SEVERE, "UserManager: Exception removing user: {0}", e.getMessage());
		}
	}

	@Override
	public User findUserById(Object id) {
		User user = null;
		try {
			user = (User) em.createNamedQuery("findUserById")
				.setParameter("userId", id)
				.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding user: {0}", e.getMessage());
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = null;
		try {
			user = (User) em.createNamedQuery("findUserByEmail")
				.setParameter("userEmail", email)
				.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding user: {0}", e.getMessage());
		}
		return user;
	}

	@Override
	public User findUserByPhone(Integer phoneNumber) {
		User user = null;
		try {
			user = (User) em.createNamedQuery("findUserByPhoneNumber")
				.setParameter("userPhoneNumber", phoneNumber)
				.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding user: {0}", e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findAllUsers")
				.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding users: {0}", e.getMessage());
		}
		return users;
	}

	@Override
	public List<User> findUsersByPrivilege(UserPrivilege privilege) {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUsersByPrivilege")
				.setParameter("userPrivilege", privilege)
				.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding users: {0}", e.getMessage());
		}
		return users;
	}

	@Override
	public List<User> findUsersByStatus(UserStatus status) {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUsersByStatus")
				.setParameter("userStatus", status)
				.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding users: {0}", e.getMessage());
		}
		return users;
	}

	@Override
	public List<User> findUsersByFullName(String fullName) {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUsersByFullName")
				.setParameter("userFullName", fullName)
				.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding users: {0}", e.getMessage());
		}
		return users;
	}

	/*

	CLIENT METHODS

	*/

	@Override
	public Integer countClients() {
		return this.findAllClients().size();
	}

	@Override
	public void createClient(Client client) {
		try {
			em.persist(client);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception creating client:{0}", e.getMessage());
		}
	}

	@Override
	public void editClient(Client client) {
		try {
			if(!em.contains(client))
                em.merge(client);
            em.flush();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception updating client: {0}", e.getMessage());
		}
	}

	@Override
	public void removeClient(Client client) {
		try {
            em.remove(em.merge(client));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception removing client: {0}", e.getMessage());
		}
	}

	@Override
	public Client findClientById(Object id) {
		Client client = null;
		try {
			client = (Client) em.createNamedQuery("findClientById")
				.setParameter("clientId", id)
				.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding client: {0}", e.getMessage());
		}
		return client;
	}

	@Override
	public List<Client> findAllClients() {
		List<Client> clients = null;
		try {
			clients = em.createNamedQuery("findAllClients")
				.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding clients: {0}", e.getMessage());
		}
		return clients;
	}

	/*

	ADMIN METHODS

	*/

	@Override
	public Integer countAdmins() {
		return this.findAllAdmins().size();
	}

	@Override
	public void createAdmin(Admin admin) {
		try {
			em.persist(admin);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception creating admin: {0}", e.getMessage());
		}
	}

	@Override
	public void editAdmin(Admin admin) {
		try {
			if(!em.contains(admin))
                em.merge(admin);
            em.flush();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception updating admin: {0}", e.getMessage());
		}
	}

	@Override
	public void removeAdmin(Admin admin) {
		try {
			em.remove(em.merge(admin));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception removing admin: {0}", e.getMessage());
		}
	}

	@Override
	public Admin findAdminById(Object id) {
		Admin admin = null;
		try {
			admin = (Admin) em.createNamedQuery("findAdminById")
				.setParameter("adminId", id)
				.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding admin: {0}", e.getMessage());
		}
		return admin;
	}

	@Override
	public List<Admin> findAllAdmins() {
		List<Admin> admins = null;
		try {
			admins = em.createNamedQuery("findAllAdmins")
				.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "UserManager: Exception finding admins: {0}", e.getMessage());
		}
		return admins;
	}
    
}

