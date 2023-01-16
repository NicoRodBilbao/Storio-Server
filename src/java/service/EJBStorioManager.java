package service;

import entities.Item;
import entities.Model;
import entities.Pack;
import entities.Report;
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Reports

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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Items

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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Models
    
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
}
