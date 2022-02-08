package com.ufrj.pp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ufrj.pp.IntegrationTest;
import com.ufrj.pp.domain.Suit;
import com.ufrj.pp.repository.SuitRepository;
import com.ufrj.pp.service.dto.SuitDTO;
import com.ufrj.pp.service.mapper.SuitMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SuitResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SuitResourceIT {

    private static final String DEFAULT_SUIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SUIT_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/suits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SuitRepository suitRepository;

    @Autowired
    private SuitMapper suitMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSuitMockMvc;

    private Suit suit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Suit createEntity(EntityManager em) {
        Suit suit = new Suit().suitNumber(DEFAULT_SUIT_NUMBER);
        return suit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Suit createUpdatedEntity(EntityManager em) {
        Suit suit = new Suit().suitNumber(UPDATED_SUIT_NUMBER);
        return suit;
    }

    @BeforeEach
    public void initTest() {
        suit = createEntity(em);
    }

    @Test
    @Transactional
    void getAllSuits() throws Exception {
        // Initialize the database
        suitRepository.saveAndFlush(suit);

        // Get all the suitList
        restSuitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suit.getId().intValue())))
            .andExpect(jsonPath("$.[*].suitNumber").value(hasItem(DEFAULT_SUIT_NUMBER)));
    }

    @Test
    @Transactional
    void getSuit() throws Exception {
        // Initialize the database
        suitRepository.saveAndFlush(suit);

        // Get the suit
        restSuitMockMvc
            .perform(get(ENTITY_API_URL_ID, suit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(suit.getId().intValue()))
            .andExpect(jsonPath("$.suitNumber").value(DEFAULT_SUIT_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingSuit() throws Exception {
        // Get the suit
        restSuitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
