package com.ufrj.pp.service;

import com.ufrj.pp.domain.Suit;
import com.ufrj.pp.repository.SuitRepository;
import com.ufrj.pp.service.dto.SuitDTO;
import com.ufrj.pp.service.mapper.SuitMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Suit}.
 */
@Service
@Transactional
public class SuitService {

    private final Logger log = LoggerFactory.getLogger(SuitService.class);

    private final SuitRepository suitRepository;

    private final SuitMapper suitMapper;

    public SuitService(SuitRepository suitRepository, SuitMapper suitMapper) {
        this.suitRepository = suitRepository;
        this.suitMapper = suitMapper;
    }

    /**
     * Save a suit.
     *
     * @param suitDTO the entity to save.
     * @return the persisted entity.
     */
    public SuitDTO save(SuitDTO suitDTO) {
        log.debug("Request to save Suit : {}", suitDTO);
        Suit suit = suitMapper.toEntity(suitDTO);
        suit = suitRepository.save(suit);
        return suitMapper.toDto(suit);
    }

    /**
     * Partially update a suit.
     *
     * @param suitDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SuitDTO> partialUpdate(SuitDTO suitDTO) {
        log.debug("Request to partially update Suit : {}", suitDTO);

        return suitRepository
            .findById(suitDTO.getId())
            .map(
                existingSuit -> {
                    suitMapper.partialUpdate(existingSuit, suitDTO);
                    return existingSuit;
                }
            )
            .map(suitRepository::save)
            .map(suitMapper::toDto);
    }

    /**
     * Get all the suits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SuitDTO> findAll() {
        log.debug("Request to get all Suits");
        return suitRepository.findAll().stream().map(suitMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one suit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SuitDTO> findOne(Long id) {
        log.debug("Request to get Suit : {}", id);
        return suitRepository.findById(id).map(suitMapper::toDto);
    }

    /**
     * Delete the suit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Suit : {}", id);
        suitRepository.deleteById(id);
    }
}
