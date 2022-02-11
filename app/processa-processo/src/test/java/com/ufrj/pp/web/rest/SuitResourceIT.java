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
import java.time.LocalDate;
import java.time.ZoneId;
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

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLAIMED = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMED = "BBBBBBBBBB";

    private static final String DEFAULT_LAWYER = "AAAAAAAAAA";
    private static final String UPDATED_LAWYER = "BBBBBBBBBB";

    private static final String DEFAULT_COURT = "AAAAAAAAAA";
    private static final String UPDATED_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SHOULD_GROUP = false;
    private static final Boolean UPDATED_SHOULD_GROUP = true;

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
        Suit suit = new Suit()
            .suitNumber(DEFAULT_SUIT_NUMBER)
            .name(DEFAULT_NAME)
            .date(DEFAULT_DATE)
            .claimed(DEFAULT_CLAIMED)
            .lawyer(DEFAULT_LAWYER)
            .court(DEFAULT_COURT)
            .value(DEFAULT_VALUE)
            .shouldGroup(DEFAULT_SHOULD_GROUP);
        return suit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Suit createUpdatedEntity(EntityManager em) {
        Suit suit = new Suit()
            .suitNumber(UPDATED_SUIT_NUMBER)
            .name(UPDATED_NAME)
            .date(UPDATED_DATE)
            .claimed(UPDATED_CLAIMED)
            .lawyer(UPDATED_LAWYER)
            .court(UPDATED_COURT)
            .value(UPDATED_VALUE)
            .shouldGroup(UPDATED_SHOULD_GROUP);
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
            .andExpect(jsonPath("$.[*].suitNumber").value(hasItem(DEFAULT_SUIT_NUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].claimed").value(hasItem(DEFAULT_CLAIMED)))
            .andExpect(jsonPath("$.[*].lawyer").value(hasItem(DEFAULT_LAWYER)))
            .andExpect(jsonPath("$.[*].court").value(hasItem(DEFAULT_COURT)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].shouldGroup").value(hasItem(DEFAULT_SHOULD_GROUP.booleanValue())));
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
            .andExpect(jsonPath("$.suitNumber").value(DEFAULT_SUIT_NUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.claimed").value(DEFAULT_CLAIMED))
            .andExpect(jsonPath("$.lawyer").value(DEFAULT_LAWYER))
            .andExpect(jsonPath("$.court").value(DEFAULT_COURT))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.shouldGroup").value(DEFAULT_SHOULD_GROUP.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingSuit() throws Exception {
        // Get the suit
        restSuitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
