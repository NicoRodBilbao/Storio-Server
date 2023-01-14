/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Admin;
import entities.Booking;
import entities.BookingState;
import entities.Client;
import entities.Pack;
import entities.User;
import entities.UserPrivilege;
import entities.UserStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class EJBStorioManager implements StorioManagerLocal{
    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "StorioPU")
    private EntityManager em;
    
    
    /**
     * This method creates a new booking in the data store.
     * @param booking
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
    public List<Booking> findUserOwnedBookings(Long id) {
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

	@Override
	public void createUser(User user) {
        try{
            em.persist(user);
        } catch(Exception e){}
	}

	@Override
	public void editUser(User user) {
	}

	@Override
	public void removeUser(User user) {
        try{
            em.remove(em.merge(user));
        } catch(Exception e){}
	}

	@Override
	public User findUserById(Object id) {
		User user = null;
		try {
			user = (User) em.createNamedQuery("findUserById")
				.setParameter("userId", id)
				.getSingleResult();
		} catch (Exception e) {

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
		} catch (Exception e) {}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUserByPhoneNumber")
				.getResultList();
		} catch (Exception e) {
		}
		return users;
	}

	@Override
	public List<User> findUsersByPrivilege(UserPrivilege privilege) {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUserByPhoneNumber")
				.setParameter("userPrivilege", privilege)
				.getResultList();
		} catch (Exception e) {}
		return users;
	}

	@Override
	public List<User> findUsersByStatus(UserStatus status) {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUserByStatus")
				.setParameter("userStatus", status)
				.getResultList();
		} catch (Exception e) {}
		return users;
	}

	@Override
	public List<User> findUsersByFullName(String fullName) {
		List<User> users = null;
		try {
			users = em.createNamedQuery("findUserByFullName")
				.setParameter("userFullName", fullName)
				.getResultList();
		} catch (Exception e) {}
		return users;
	}

	/*

	CLIENT METHODS

	*/

	@Override
	public void createClient(Client client) {
		try {
			em.persist(client);
		} catch (Exception e) {}
	}

	@Override
	public void editClient(Client client) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void removeClient(Client client) {
		try {
            em.remove(em.merge(client));
		} catch(Exception e) {}
	}

	@Override
	public Client findClientById(Object id) {
		Client client = null;
		try {
			client = (Client) em.createNamedQuery("findClientById")
				.setParameter("clientId", id)
				.getSingleResult();
		} catch (Exception e) {}
		return client;
	}

	@Override
	public List<Client> findAllClients() {
		List<Client> clients = null;
		try {
			clients = em.createNamedQuery("findAllClients")
				.getResultList();
		} catch (Exception e) {}
		return clients;
	}

	/*

	ADMIN METHODS

	*/

	@Override
	public void createAdmin(Admin admin) {
		try {
			em.persist(admin);
		} catch (Exception e) {}
	}

	@Override
	public void editAdmin(Admin admin) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void removeAdmin(Admin admin) {
		try {
			em.remove(em.merge(admin));
		} catch (Exception e) {}
	}

	@Override
	public Admin findAdminById(Object id) {
		Admin admin = null;
		try {
			admin = (Admin) em.createNamedQuery("findAdminById")
				.setParameter("adminId", id)
				.getSingleResult();
		} catch (Exception e) {}
		return admin;
	}

	@Override
	public List<Admin> findAllAdmins() {
		List<Admin> admins = null;
		try {
			admins = em.createNamedQuery("findAllAdmins")
				.getResultList();
		} catch (Exception e) {}
		return admins;
	}
    
}