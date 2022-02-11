package com.ufrj.pp.service;

import com.ufrj.pp.domain.Lawyer;
import com.ufrj.pp.repository.LawyerRepository;
import com.ufrj.pp.service.dto.LawyerDTO;
import com.ufrj.pp.service.mapper.LawyerMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Lawyer}.
 */
@Service
@Transactional
public class LawyerService {

    private final Logger log = LoggerFactory.getLogger(LawyerService.class);

    private final LawyerRepository lawyerRepository;

    private final LawyerMapper lawyerMapper;

    public LawyerService(LawyerRepository lawyerRepository, LawyerMapper lawyerMapper) {
        this.lawyerRepository = lawyerRepository;
        this.lawyerMapper = lawyerMapper;
    }

    /**
     * Save a lawyer.
     *
     * @param lawyerDTO the entity to save.
     * @return the persisted entity.
     */
    public LawyerDTO save(LawyerDTO lawyerDTO) {
        log.debug("Request to save Lawyer : {}", lawyerDTO);
        Lawyer lawyer = lawyerMapper.toEntity(lawyerDTO);
        lawyer = lawyerRepository.save(lawyer);
        return lawyerMapper.toDto(lawyer);
    }

    /**
     * Partially update a lawyer.
     *
     * @param lawyerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LawyerDTO> partialUpdate(LawyerDTO lawyerDTO) {
        log.debug("Request to partially update Lawyer : {}", lawyerDTO);

        return lawyerRepository
            .findById(lawyerDTO.getId())
            .map(
                existingLawyer -> {
                    lawyerMapper.partialUpdate(existingLawyer, lawyerDTO);
                    return existingLawyer;
                }
            )
            .map(lawyerRepository::save)
            .map(lawyerMapper::toDto);
    }

    /**
     * Get all the lawyers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LawyerDTO> findAll() {
        log.debug("Request to get all Lawyers");
        return lawyerRepository.findAll().stream().map(lawyerMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one lawyer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LawyerDTO> findOne(Long id) {
        log.debug("Request to get Lawyer : {}", id);
        return lawyerRepository.findById(id).map(lawyerMapper::toDto);
    }

    /**
     * Delete the lawyer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Lawyer : {}", id);
        lawyerRepository.deleteById(id);
    }
}
