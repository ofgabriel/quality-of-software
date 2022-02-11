package com.ufrj.pp.service;

import com.ufrj.pp.domain.SuitProcess;
import com.ufrj.pp.repository.SuitProcessRepository;
import com.ufrj.pp.repository.SuitRepository;
import com.ufrj.pp.service.dto.SuitProcessDTO;
import com.ufrj.pp.service.mapper.SuitProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SuitProcess}.
 */
@Service
@Transactional
public class SuitProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "suit_monitoring";

    private final Logger log = LoggerFactory.getLogger(SuitProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final SuitRepository suitRepository;

    private final SuitProcessRepository suitProcessRepository;

    private final SuitProcessMapper suitProcessMapper;

    public SuitProcessService(
        ProcessInstanceService processInstanceService,
        SuitRepository suitRepository,
        SuitProcessRepository suitProcessRepository,
        SuitProcessMapper suitProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.suitRepository = suitRepository;
        this.suitProcessRepository = suitProcessRepository;
        this.suitProcessMapper = suitProcessMapper;
    }

    /**
     * Save a suitProcess.
     *
     * @param suitProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public SuitProcessDTO create(SuitProcessDTO suitProcessDTO) {
        log.debug("Request to save SuitProcess : {}", suitProcessDTO);

        SuitProcess suitProcess = suitProcessMapper.toEntity(suitProcessDTO);

        //Saving the domainEntity
        suitRepository.save(suitProcess.getSuit());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Suit#" + suitProcess.getSuit().getId(),
            suitProcess
        );
        suitProcess.setProcessInstance(processInstance);

        //Saving the process entity
        suitProcess = suitProcessRepository.save(suitProcess);
        return suitProcessMapper.toDto(suitProcess);
    }

    /**
     * Get all the suitProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SuitProcessDTO> findAll() {
        log.debug("Request to get all SuitProcesss");
        return suitProcessRepository.findAll().stream().map(suitProcessMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one suitProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SuitProcessDTO> findOne(Long id) {
        log.debug("Request to get SuitProcess : {}", id);
        return suitProcessRepository.findById(id).map(suitProcessMapper::toDto);
    }

    /**
     * Get one suitProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SuitProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get SuitProcess by  processInstanceId: {}", processInstanceId);
        return suitProcessRepository.findByProcessInstanceId(processInstanceId).map(suitProcessMapper::toDto);
    }
}
