/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Pack;
import entities.PackState;
import entities.PackType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 2dam
 */
@Local
public interface StorioManagerLocal {

    /**
     * Search all pack created and return the list of them
     *
     * @return packs
     */
    public List<Pack> findALlPacks();
    
    /**
     * Search packs by the state and return the list of them
     *
     * @param state
     * @return packs
     */
    public List<Pack> findPacksByState(PackState state);
    
    /**
     * Search packs by the state and return the list of them
     *
     * @param id
     * @return packs
     */
    public List<Pack> listBookingByPack(Integer id);

    /**
     * Search packs by the type of pack and return the list of them
     *
     * @param type
     * @return packs
     */
    public List<Pack> findPacksByType(PackType type);

    /**
     * Create a new pack
     *
     * @param pack
     */
    public void createPack(Pack pack);

    /**
     * update the pack
     *
     * @param pack
     */
    public void updatePack(Pack pack);

    /**
     * by the id find one pack
     * @param id
     * @return pack
     */
    public Pack findPackById(Integer id);

    /**
     * delete the pack
     * @param pack 
     */
    public void deletePack(Pack pack);

}
