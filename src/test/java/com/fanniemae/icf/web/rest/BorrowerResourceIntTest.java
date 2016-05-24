package com.fanniemae.icf.web.rest;

import com.fanniemae.icf.FfbRwApp;
import com.fanniemae.icf.domain.Borrower;
import com.fanniemae.icf.repository.BorrowerRepository;
import com.fanniemae.icf.service.BorrowerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BorrowerResource REST controller.
 *
 * @see BorrowerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FfbRwApp.class)
@WebAppConfiguration
@IntegrationTest
public class BorrowerResourceIntTest {


    private static final Long DEFAULT_BORROWER_ID = 1L;
    private static final Long UPDATED_BORROWER_ID = 2L;
    private static final String DEFAULT_BORROWER_FIRST_NAME = "A";
    private static final String UPDATED_BORROWER_FIRST_NAME = "B";
    private static final String DEFAULT_BORROWER_LAST_NAME = "A";
    private static final String UPDATED_BORROWER_LAST_NAME = "B";
    private static final String DEFAULT_BORROWER_MIDDLE_NAME = "AAAAA";
    private static final String UPDATED_BORROWER_MIDDLE_NAME = "BBBBB";
    private static final String DEFAULT_BORROWER_SUFFIX = "AAAAA";
    private static final String UPDATED_BORROWER_SUFFIX = "BBBBB";
    private static final String DEFAULT_BORROWER_SSN = "AAAAAAAAA";
    private static final String UPDATED_BORROWER_SSN = "BBBBBBBBB";

    @Inject
    private BorrowerRepository borrowerRepository;

    @Inject
    private BorrowerService borrowerService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBorrowerMockMvc;

    private Borrower borrower;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BorrowerResource borrowerResource = new BorrowerResource();
        ReflectionTestUtils.setField(borrowerResource, "borrowerService", borrowerService);
        this.restBorrowerMockMvc = MockMvcBuilders.standaloneSetup(borrowerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        borrower = new Borrower();
        borrower.setBorrowerId(DEFAULT_BORROWER_ID);
        borrower.setBorrowerFirstName(DEFAULT_BORROWER_FIRST_NAME);
        borrower.setBorrowerLastName(DEFAULT_BORROWER_LAST_NAME);
        borrower.setBorrowerMiddleName(DEFAULT_BORROWER_MIDDLE_NAME);
        borrower.setBorrowerSuffix(DEFAULT_BORROWER_SUFFIX);
        borrower.setBorrowerSsn(DEFAULT_BORROWER_SSN);
    }

    @Test
    @Transactional
    public void createBorrower() throws Exception {
        int databaseSizeBeforeCreate = borrowerRepository.findAll().size();

        // Create the Borrower

        restBorrowerMockMvc.perform(post("/api/borrowers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(borrower)))
                .andExpect(status().isCreated());

        // Validate the Borrower in the database
        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeCreate + 1);
        Borrower testBorrower = borrowers.get(borrowers.size() - 1);
        assertThat(testBorrower.getBorrowerId()).isEqualTo(DEFAULT_BORROWER_ID);
        assertThat(testBorrower.getBorrowerFirstName()).isEqualTo(DEFAULT_BORROWER_FIRST_NAME);
        assertThat(testBorrower.getBorrowerLastName()).isEqualTo(DEFAULT_BORROWER_LAST_NAME);
        assertThat(testBorrower.getBorrowerMiddleName()).isEqualTo(DEFAULT_BORROWER_MIDDLE_NAME);
        assertThat(testBorrower.getBorrowerSuffix()).isEqualTo(DEFAULT_BORROWER_SUFFIX);
        assertThat(testBorrower.getBorrowerSsn()).isEqualTo(DEFAULT_BORROWER_SSN);
    }

    @Test
    @Transactional
    public void checkBorrowerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = borrowerRepository.findAll().size();
        // set the field null
        borrower.setBorrowerId(null);

        // Create the Borrower, which fails.

        restBorrowerMockMvc.perform(post("/api/borrowers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(borrower)))
                .andExpect(status().isBadRequest());

        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorrowerFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = borrowerRepository.findAll().size();
        // set the field null
        borrower.setBorrowerFirstName(null);

        // Create the Borrower, which fails.

        restBorrowerMockMvc.perform(post("/api/borrowers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(borrower)))
                .andExpect(status().isBadRequest());

        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorrowerLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = borrowerRepository.findAll().size();
        // set the field null
        borrower.setBorrowerLastName(null);

        // Create the Borrower, which fails.

        restBorrowerMockMvc.perform(post("/api/borrowers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(borrower)))
                .andExpect(status().isBadRequest());

        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorrowerSsnIsRequired() throws Exception {
        int databaseSizeBeforeTest = borrowerRepository.findAll().size();
        // set the field null
        borrower.setBorrowerSsn(null);

        // Create the Borrower, which fails.

        restBorrowerMockMvc.perform(post("/api/borrowers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(borrower)))
                .andExpect(status().isBadRequest());

        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBorrowers() throws Exception {
        // Initialize the database
        borrowerRepository.saveAndFlush(borrower);

        // Get all the borrowers
        restBorrowerMockMvc.perform(get("/api/borrowers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(borrower.getId().intValue())))
                .andExpect(jsonPath("$.[*].borrowerId").value(hasItem(DEFAULT_BORROWER_ID.intValue())))
                .andExpect(jsonPath("$.[*].borrowerFirstName").value(hasItem(DEFAULT_BORROWER_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].borrowerLastName").value(hasItem(DEFAULT_BORROWER_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].borrowerMiddleName").value(hasItem(DEFAULT_BORROWER_MIDDLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].borrowerSuffix").value(hasItem(DEFAULT_BORROWER_SUFFIX.toString())))
                .andExpect(jsonPath("$.[*].borrowerSsn").value(hasItem(DEFAULT_BORROWER_SSN.toString())));
    }

    @Test
    @Transactional
    public void getBorrower() throws Exception {
        // Initialize the database
        borrowerRepository.saveAndFlush(borrower);

        // Get the borrower
        restBorrowerMockMvc.perform(get("/api/borrowers/{id}", borrower.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(borrower.getId().intValue()))
            .andExpect(jsonPath("$.borrowerId").value(DEFAULT_BORROWER_ID.intValue()))
            .andExpect(jsonPath("$.borrowerFirstName").value(DEFAULT_BORROWER_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.borrowerLastName").value(DEFAULT_BORROWER_LAST_NAME.toString()))
            .andExpect(jsonPath("$.borrowerMiddleName").value(DEFAULT_BORROWER_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.borrowerSuffix").value(DEFAULT_BORROWER_SUFFIX.toString()))
            .andExpect(jsonPath("$.borrowerSsn").value(DEFAULT_BORROWER_SSN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBorrower() throws Exception {
        // Get the borrower
        restBorrowerMockMvc.perform(get("/api/borrowers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBorrower() throws Exception {
        // Initialize the database
        borrowerService.save(borrower);

        int databaseSizeBeforeUpdate = borrowerRepository.findAll().size();

        // Update the borrower
        Borrower updatedBorrower = new Borrower();
        updatedBorrower.setId(borrower.getId());
        updatedBorrower.setBorrowerId(UPDATED_BORROWER_ID);
        updatedBorrower.setBorrowerFirstName(UPDATED_BORROWER_FIRST_NAME);
        updatedBorrower.setBorrowerLastName(UPDATED_BORROWER_LAST_NAME);
        updatedBorrower.setBorrowerMiddleName(UPDATED_BORROWER_MIDDLE_NAME);
        updatedBorrower.setBorrowerSuffix(UPDATED_BORROWER_SUFFIX);
        updatedBorrower.setBorrowerSsn(UPDATED_BORROWER_SSN);

        restBorrowerMockMvc.perform(put("/api/borrowers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedBorrower)))
                .andExpect(status().isOk());

        // Validate the Borrower in the database
        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeUpdate);
        Borrower testBorrower = borrowers.get(borrowers.size() - 1);
        assertThat(testBorrower.getBorrowerId()).isEqualTo(UPDATED_BORROWER_ID);
        assertThat(testBorrower.getBorrowerFirstName()).isEqualTo(UPDATED_BORROWER_FIRST_NAME);
        assertThat(testBorrower.getBorrowerLastName()).isEqualTo(UPDATED_BORROWER_LAST_NAME);
        assertThat(testBorrower.getBorrowerMiddleName()).isEqualTo(UPDATED_BORROWER_MIDDLE_NAME);
        assertThat(testBorrower.getBorrowerSuffix()).isEqualTo(UPDATED_BORROWER_SUFFIX);
        assertThat(testBorrower.getBorrowerSsn()).isEqualTo(UPDATED_BORROWER_SSN);
    }

    @Test
    @Transactional
    public void deleteBorrower() throws Exception {
        // Initialize the database
        borrowerService.save(borrower);

        int databaseSizeBeforeDelete = borrowerRepository.findAll().size();

        // Get the borrower
        restBorrowerMockMvc.perform(delete("/api/borrowers/{id}", borrower.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Borrower> borrowers = borrowerRepository.findAll();
        assertThat(borrowers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
