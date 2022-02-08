package com.ufrj.pp.web.rest;

import com.ufrj.pp.repository.SuitRepository;
import com.ufrj.pp.service.SuitService;
import com.ufrj.pp.service.dto.SuitDTO;
import com.ufrj.pp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ufrj.pp.domain.Suit}.
 */
@RestController
@RequestMapping("/api")
public class SuitResource {

    private final Logger log = LoggerFactory.getLogger(SuitResource.class);

    private final SuitService suitService;

    private final SuitRepository suitRepository;

    public SuitResource(SuitService suitService, SuitRepository suitRepository) {
        this.suitService = suitService;
        this.suitRepository = suitRepository;
    }

    /**
     * {@code GET  /suits} : get all the suits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suits in body.
     */
    @GetMapping("/suits")
    public List<SuitDTO> getAllSuits() {
        log.debug("REST request to get all Suits");
        return suitService.findAll();
    }

    /**
     * {@code GET  /suits/:id} : get the "id" suit.
     *
     * @param id the id of the suitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suits/{id}")
    public ResponseEntity<SuitDTO> getSuit(@PathVariable Long id) {
        log.debug("REST request to get Suit : {}", id);
        Optional<SuitDTO> suitDTO = suitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suitDTO);
    }
}
