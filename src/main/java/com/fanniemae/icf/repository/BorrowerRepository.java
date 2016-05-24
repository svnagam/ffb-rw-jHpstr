package com.fanniemae.icf.repository;

import com.fanniemae.icf.domain.Borrower;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Borrower entity.
 */
@SuppressWarnings("unused")
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {

}
