package com.ufrj.pp.web.rest;

import com.ufrj.pp.repository.LawyerRepository;
import com.ufrj.pp.service.LawyerService;
import com.ufrj.pp.service.dto.LawyerDTO;
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
 * REST controller for managing {@link com.ufrj.pp.domain.Lawyer}.
 */
@RestController
@RequestMapping("/api")
public class LawyerResource {

    private final Logger log = LoggerFactory.getLogger(LawyerResource.class);

    private final LawyerService lawyerService;

    private final LawyerRepository lawyerRepository;

    public LawyerResource(LawyerService lawyerService, LawyerRepository lawyerRepository) {
        this.lawyerService = lawyerService;
        this.lawyerRepository = lawyerRepository;
    }

    /**
     * {@code GET  /lawyers} : get all the lawyers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lawyers in body.
     */
    @GetMapping("/lawyers")
    public List<LawyerDTO> getAllLawyers() {
        log.debug("REST request to get all Lawyers");
        return lawyerService.findAll();
    }

    /**
     * {@code GET  /lawyers/:id} : get the "id" lawyer.
     *
     * @param id the id of the lawyerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lawyerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lawyers/{id}")
    public ResponseEntity<LawyerDTO> getLawyer(@PathVariable Long id) {
        log.debug("REST request to get Lawyer : {}", id);
        Optional<LawyerDTO> lawyerDTO = lawyerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lawyerDTO);
    }
}
