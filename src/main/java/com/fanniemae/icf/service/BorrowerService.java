package com.fanniemae.icf.service;

import com.fanniemae.icf.domain.Borrower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Borrower.
 */
public interface BorrowerService {

    /**
     * Save a borrower.
     * 
     * @param borrower the entity to save
     * @return the persisted entity
     */
    Borrower save(Borrower borrower);

    /**
     *  Get all the borrowers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Borrower> findAll(Pageable pageable);

    /**
     *  Get the "id" borrower.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Borrower findOne(Long id);

    /**
     *  Delete the "id" borrower.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);
}
