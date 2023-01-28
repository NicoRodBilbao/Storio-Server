/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entities.*;
import exceptions.*;
import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.InternalServerErrorException;

/**
 * Local interface for StorioManager EJB.
 *
 * @author Nicolás Rodríguez
 */


@Local
public interface StorioManagerLocal {

    /**
     * Search all pack created and return the list of them
     *
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> findALlPacks() throws FindException;

    /**
     * Search packs by the state and return the list of them
     *
     * @param state
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> findPacksByState(PackState state) throws FindException;

    /**
     * Search packs by the state and return the list of them
     *
     * @param id
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> listPacksByBooking(Integer id) throws FindException;

    /**
     * Search packs by the type of pack and return the list of them
     *
     * @param type
     * @return packs
     * @throws exceptions.FindException
     */
    public List<Pack> findPacksByType(PackType type) throws FindException;

    /**
     * Create a new pack
     *
     * @param pack
     * @throws exceptions.CreateException
     */
    public void createPack(Pack pack) throws CreateException;

    /**
     * update the pack
     *
     * @param pack
     * @throws exceptions.UpdateException
     */
    public void updatePack(Pack pack) throws UpdateException;

    /**
     * by the id find one pack
     *
     * @param id
     * @return pack
     * @throws exceptions.FindException
     */
    public Pack findPackById(Integer id) throws FindException;

    /**
     * delete the pack
     *
     * @param pack
     * @throws exceptions.RemoveException
     */
    public void deletePack(Pack pack) throws RemoveException;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createReport(Report report) throws CreateException;

    public void createItem(Item item) throws CreateException;

    public void createModel(Model model) throws CreateException;

    public void updateReport(Report report) throws UpdateException;

    public void updateItem(Item item) throws UpdateException;

    public void updateModel(Model model) throws UpdateException;

    public void removeReport(Report report) throws RemoveException;

    public void removeItem(Item item) throws RemoveException;

    public void removeModel(Model model) throws RemoveException;

    public Report findReport(Integer id) throws FindException;

    public List<Report> findAllItemsReports(Integer id) throws FindException;

    public Item findItem(Integer id) throws FindException;

    public Model findModel(Integer id) throws FindException;

    public List<Report> findAllReports() throws FindException;

    public List<Item> findAllItems() throws FindException;

    public List<Item> findAllItemsWIthoutPack() throws FindException;

    public List<Item> findAllModelsItems(Integer id) throws FindException;

    public List<Item> findAllPacksItems(Integer id) throws FindException;

    public List<Model> findAllModels() throws FindException;

    public Integer countReports() throws FindException;

    public Integer countItems() throws FindException;

    public Integer countModels() throws FindException;

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
     * @param id
     * @return A Booking entity object
     */
    public Booking findBookingById(Integer id);

    /**
     * This method gets a list with all handed bookings in the data store.
     *
     * @param state
     * @return A List of Booking entity objects..
     */
    public List<Booking> findBookingsByState(BookingState state);

    /**
     * This method gets a list with all bookings of one user in the data store.
     *
     * @param id
     * @return A List of Booking entity objects..
     */
    public List<Booking> findUserOwnBookings(Integer id);

    /**
     * This method gets a list with all packs asociated to a booking.
     *
     * @param id
     * @return A List of Pack entity objects..
     */
    public List<Pack> listPacksForBooking(Integer id);

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

	/**
	 * Hashes the user's password using the MD5 algorithm
	 * @param user The user whose password will be hashed
	 * @return The same user, but with the hashed password
	 * @throws InternalServerErrorException 
	 */
	public User hashPassword(User user) throws InternalServerErrorException;

    /**
     * This method returns the total count of users
     *
     * @return The number of USERs, it doesn't discriminate by user
     * @throws exceptions.FindException
     */
    public Integer countUsers() throws FindException;

	/**
	 * Returns OK if the login and password are correct
	 * @param login Username
	 * @param password PlainText Password
	 * @return OK only if the credentials are the correct ones
	 * @throws exceptions.FindException if incorrect login credentials
	 */
	public boolean loginUser(String login, String password) throws FindException;

    /**
     * This method adds a user to the data store
     *
     * @param user The user to be added
     * @throws exceptions.CreateException
     */
    public void createUser(User user) throws CreateException;

    /**
     * This method edits a user from the data store
     *
     * @param user The modified user
     * @throws exceptions.UpdateException
     */
    public void editUser(User user) throws UpdateException;

    /**
     * This method removes a user from the data store
     *
     * @param user The user to be removed
     * @throws exceptions.RemoveException
     */
    public void removeUser(User user) throws RemoveException;

    /**
     * This method returns a user whose id matches the given parameter
     *
     * @param id The user to be added
     * @return The matching user, if found
     * @throws exceptions.FindException
     */
    public User findUserById(Integer id) throws FindException;

    /**
     * This method finds a user with a matching username
     *
     * @param login The username to search for
     * @return The matching user, if found
     * @throws exceptions.FindException
     */
    public User findUserByLogin(String login) throws FindException;

    /**
     * This method finds a user with a matching email
     *
     * @param email The email to search for
     * @return The matching user, if found
     * @throws exceptions.FindException
     */
    public User findUserByEmail(String email) throws FindException;

    /**
     * This method finds a user with a matching phone number
     *
     * @param phoneNumber
     * @return The matching user, if found
     * @throws exceptions.FindException
     */
    public User findUserByPhone(Integer phoneNumber) throws FindException;

    /**
     * This method returns all users from the data store
     *
     * @return A list containing all the users
     * @throws exceptions.FindException
     */
    public List<User> findAllUsers() throws FindException;

    /**
     * This method returns all users of a certain type
     *
     * @param privilege The privilege level to filter users
     * @return A list of users that are of the requested type
     * @throws exceptions.FindException
     */
    public List<User> findUsersByPrivilege(UserPrivilege privilege) throws FindException;

    /**
     * This method returns all users matching the given status
     *
     * @param status The status by which to filter the users
     * @return A list containing all the users of the given status
     * @throws exceptions.FindException
     */
    public List<User> findUsersByStatus(UserStatus status) throws FindException;

    /**
     * Returns a list of users matching a string
     *
     * @param fullName A string to filter the users
     * @return A list containing all the users that match the string
     * @throws exceptions.FindException
     */
    public List<User> findUsersByFullName(String fullName) throws FindException;

    /**
     * This method counts how many clients there are
     *
     * @return The number of USERs that are clients
     * @throws exceptions.FindException
     */
    public Integer countClients() throws FindException;

    /**
     * Adds a client to the data store
     *
     * @param client The client to be added
     * @throws exceptions.CreateException
     */
    public void createClient(Client client) throws CreateException;

    /**
     * Edits a client from the data store
     *
     * @param client The modified client
     * @throws exceptions.UpdateException
     */
    public void editClient(Client client) throws UpdateException;

    /**
     * Removes a client from the data store
     *
     * @param client The client to be removed
     * @throws exceptions.RemoveException
     */
    public void removeClient(Client client) throws RemoveException;

    /**
     * Finds a client with the matching id
     *
     * @param id The client id
     * @return The matching client if found or nothing
     * @throws exceptions.FindException
     */
    public Client findClientById(Object id) throws FindException;

    /**
     * Returns all clients
     *
     * @return A list containing all clients
     * @throws exceptions.FindException
     */
    public List<Client> findAllClients() throws FindException;

    /**
     * This method counts how many admins there are
     *
     * @return The number of USERs that are admins
     * @throws exceptions.FindException
     */
    public Integer countAdmins() throws FindException;

    /**
     * Adds an admin to the data store
     *
     * @param admin
     * @throws exceptions.CreateException
     */
    public void createAdmin(Admin admin) throws CreateException;

    /**
     * Edits an admin from the data store
     *
     * @param admin The modified admin
     * @throws exceptions.UpdateException
     */
    public void editAdmin(Admin admin) throws UpdateException;

    /**
     * Deletes an admin from the data store
     *
     * @param admin The admin to be deleted
     * @throws exceptions.RemoveException
     */
    public void removeAdmin(Admin admin) throws RemoveException;

    /**
     * Returns an admin whose id matches the given one
     *
     * @param id The id to match
     * @return An admin matching the id
     * @throws exceptions.FindException
     */
    public Admin findAdminById(Object id) throws FindException;

    /**
     * Returns all admins
     *
     * @return A list containing all the admins
     * @throws exceptions.FindException
     */
    public List<Admin> findAllAdmins() throws FindException;

}
