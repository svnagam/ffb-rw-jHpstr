package com.fanniemae.icf.service.impl;

import com.fanniemae.icf.service.BorrowerService;
import com.fanniemae.icf.domain.Borrower;
import com.fanniemae.icf.repository.BorrowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Borrower.
 */
@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService{

    private final Logger log = LoggerFactory.getLogger(BorrowerServiceImpl.class);
    
    @Inject
    private BorrowerRepository borrowerRepository;
    
    /**
     * Save a borrower.
     * 
     * @param borrower the entity to save
     * @return the persisted entity
     */
    public Borrower save(Borrower borrower) {
        log.debug("Request to save Borrower : {}", borrower);
        Borrower result = borrowerRepository.save(borrower);
        return result;
    }

    /**
     *  Get all the borrowers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Borrower> findAll(Pageable pageable) {
        log.debug("Request to get all Borrowers");
        Page<Borrower> result = borrowerRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one borrower by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Borrower findOne(Long id) {
        log.debug("Request to get Borrower : {}", id);
        Borrower borrower = borrowerRepository.findOne(id);
        return borrower;
    }

    /**
     *  Delete the  borrower by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Borrower : {}", id);
        borrowerRepository.delete(id);
    }
}
