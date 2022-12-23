/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Pack;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class PackEJB implements StorioManagerLocal {

    private static final Logger LOGGER = Logger.getLogger("javafxserverside");

    @PersistenceContext
    private EntityManager em;

    /**
     * Find and list all the packs
     *
     * @return packs
     */
    @Override
    public List<Pack> findALlPacks() {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listAllPacks").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception reading all packs:", e.getMessage());
        }
        return packs;
    }

    /**
     * Find and list the packs that are available
     *
     * @param state
     * @return packs
     */
    @Override
    public List<Pack> findPacksByState(String state) {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listPacksByState").setParameter("state", state).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception reading all packs available:", e.getMessage());
        }
        return packs;
    }

    /**
     * Find and list packs by a Type of pack
     *
     * @param type
     * @return packs
     */
    @Override
    public List<Pack> findPacksByType(String type) {
        List<Pack> packs = null;
        try {
            LOGGER.info("Reading Packs");
            packs = em.createNamedQuery("listAllPacksByType").setParameter("type", type).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception reading all packs available:", e.getMessage());
        }
        return packs;
    }

    /**
     * Create a pack
     *
     * @param pack
     */
    @Override
    public void createPack(Pack pack) {
        try {
            em.persist(pack);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception creating pack.",
                    e.getMessage());
        }
    }

    /**
     * Returns the pack updated
     *
     * @param pack
     */
    @Override
    public void updatePack(Pack pack) {
        try {
            if(!em.contains(pack))
                em.merge(pack);
            em.flush();
            LOGGER.info("PackManager: Pack Updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception update user.",
                    e.getMessage());
        }
    }

    /**
     * Find a pack and remove it
     *
     * @param pack
     */
    @Override
    public void deletePack(Pack pack) {
        try {
            em.remove(em.merge(pack));
            LOGGER.info("PackManager: Pack deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception deleting user.", e.getMessage());
        }
    }

    /**
     * Find a pack by it's id
     *
     * @param id
     * @return pack
     */
    @Override
    public Pack findPackById(Integer id) {
        Pack pack = null;
        try {
            LOGGER.info("PackManager: Finding pack by login.");
            pack = em.find(Pack.class, id);
            if (pack != null) {
                LOGGER.log(Level.INFO, "PackManager: pack", pack.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PackManager: Exception Finding pack by login:", e.getMessage());
        }
        return pack;
    }
}
