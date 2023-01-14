/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Booking;
import entities.BookingState;
import entities.Pack;
import entities.User;
import entities.Client;
import entities.Admin;
import entities.UserPrivilege;
import entities.UserStatus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 2dam
 */
@Local
public interface StorioManagerLocal {

	/*

	BOOKING METHODS

	*/

	/**
	 * This method creates a new booking in the data store.
	 *
	 * @param booking
	 */
	public void createBooking(Booking booking);

	/**
	 * This method gets a list with all bookings in the data store.
	 *
	 * @return A List of Booking entity objects..
	 */
	public List<Booking> findAllBookings();

	/**
	 * This method gets a booking with a selected id in the data store.
	 *
	 * @return A Booking entity object
	 */
	public Booking findBookingById(Integer id);

	/**
	 * This method gets a list with all handed bookings in the data store.
	 *
	 * @return A List of Booking entity objects..
	 */
	public List<Booking> findBookingsByState(BookingState state);

	/**
	 * This method gets a list with all bookings of one user in the data
	 * store.
	 *
	 * @return A List of Booking entity objects..
	 */
	public List<Booking> findUserOwnedBookings(Long id);

	/**
	 * This method gets a list with all packs asociated to a booking.
	 *
	 * @return A List of Pack entity objects..
	 */
	public List<Pack> listPacksForBooking(Long id);

	/**
	 * This method updates a booking data in the data store.
	 *
	 * @param booking The Booking entity object containing modified account
	 * data.
	 */
	public void updateBooking(Booking booking);

	/**
	 * This method removes an account from the data store.
	 *
	 * @param booking The Booking entity object to be removed.
	 */
	public void removeBooking(Booking booking);

	/* 
	USER METHODS

	*/

	/**
	 * This method adds a user to the data store
	 * @param user The user to be added 
	 */
	public void createUser(User user);

	/**
	 * This method edits a user from the data store
	 * @param user The modified user 
	 */
	public void editUser(User user);

	/**
	 * This method removes a user from the data store
	 * @param user The user to be removed
	 */
	public void removeUser(User user);

	/**
	 * This method returns a user whose id matches
	 * the given parameter
	 * @param id The user to be added 
	 * @return The matching user, if found
	 */
	public User findUserById(Object id);

	/**
	 * This method finds a user with a matching email
	 * @param email The email to search for
	 * @return The matching user, if found
	 */
	public User findUserByEmail(String email);

	/**
	 * This method finds a user with a matching phone number
	 * @param phoneNumber
	 * @return The matching user, if found
	 */
	public User findUserByPhone(Integer phoneNumber);

	/**
	 * This method returns all users from the data store
	 * @return A list containing all the users
	 */
	public List<User> findAllUsers();

	/**
	 * This method returns all users of a certain type
	 * @param privilege The privilege level to filter users
	 * @return A list of users that are of the requested type
	 */
	public List<User> findUsersByPrivilege(UserPrivilege privilege);

	/**
	 * This method returns all users matching the given status
	 * @param status The status by which to filter the users
	 * @return A list containing all the users of the given status
	 */
	public List<User> findUsersByStatus(UserStatus status);

	/**
	 * Returns a list of users matching a string
	 * @param fullName A string to filter the users
	 * @return A list containing all the users that match the string
	 */
	public List<User> findUsersByFullName(String fullName);

	/* 

	CLIENT METHODS

	*/

	/**
	 * Adds a client to the data store
	 * @param client The client to be added
	 */
	public void createClient(Client client);

	/**
	 * Edits a client from the data store
	 * @param client The modified client 
	 */
	public void editClient(Client client);

	/**
	 * Removes a client from the data store
	 * @param client The client to be removed
	 */
	public void removeClient(Client client);

	/**
	 * Finds a client with the matching id
	 * @param id The client id
	 * @return The matching client if found or nothing
	 */
	public Client findClientById(Object id);

	/**
	 * Returns all clients
	 * @return A list containing all clients
	 */
	public List<Client> findAllClients();

	/* 

	ADMIN METHODS

	*/

	/**
	 * Adds an admin to the data store
	 * @param admin 
	 */
	public void createAdmin(Admin admin);

	/**
	 * Edits an admin from the data store
	 * @param admin The modified admin
	 */
	public void editAdmin(Admin admin);

	/**
	 * Deletes an admin from the data store
	 * @param admin The admin to be deleted
	 */
	public void removeAdmin(Admin admin);

	/**
	 * Returns an admin whose id matches the given one
	 * @param id The id to match
	 * @return An admin matching the id
	 */
	public Admin findAdminById(Object id);

	/**
	 * Returns all admins
	 * @return A list containing all the admins
	 */
	public List<Admin> findAllAdmins();

}
