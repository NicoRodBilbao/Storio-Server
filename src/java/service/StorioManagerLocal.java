package service;

import entities.*;
import exceptions.*;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for StorioManager EJB.
 * @author Nicolás Rodríguez
 */
@Local
public interface StorioManagerLocal { 
    public void createReport (Report report) throws CreateException;
    
    public void createItem (Item item) throws CreateException;
    
    public void createModel (Model model) throws CreateException;
    
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
}
