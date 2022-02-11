package com.ufrj.pp.web.rest;

import com.ufrj.pp.service.SuitProcessService;
import com.ufrj.pp.service.dto.SuitProcessDTO;
import com.ufrj.pp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ufrj.pp.domain.SuitProcess}.
 */
@RestController
@RequestMapping("/api")
public class SuitProcessResource {

    private final Logger log = LoggerFactory.getLogger(SuitProcessResource.class);

    private static final String ENTITY_NAME = "suitProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuitProcessService suitProcessService;

    public SuitProcessResource(SuitProcessService suitProcessService) {
        this.suitProcessService = suitProcessService;
    }

    /**
     * {@code POST  /suit-processes} : Create a new suitProcess.
     *
     * @param suitProcessDTO the suitProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suitProcessDTO, or with status {@code 400 (Bad Request)} if the suitProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suit-processes")
    public ResponseEntity<SuitProcessDTO> create(@RequestBody SuitProcessDTO suitProcessDTO) throws URISyntaxException {
        log.debug("REST request to save SuitProcess : {}", suitProcessDTO);
        if (suitProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new suitProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuitProcessDTO result = suitProcessService.create(suitProcessDTO);
        return ResponseEntity
            .created(new URI("/api/suit-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /suit-processes} : get all the suitProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suitProcesss in body.
     */
    @GetMapping("/suit-processes")
    public List<SuitProcessDTO> getAllSuitProcesss() {
        log.debug("REST request to get all SuitProcesss");
        return suitProcessService.findAll();
    }

    /**
     * {@code GET  /suit-processes/:id} : get the "id" suitProcess.
     *
     * @param processInstanceId the id of the suitProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suitProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suit-processes/{processInstanceId}")
    public ResponseEntity<SuitProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get SuitProcess by processInstanceId : {}", processInstanceId);
        Optional<SuitProcessDTO> suitProcessDTO = suitProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(suitProcessDTO);
    }
}
