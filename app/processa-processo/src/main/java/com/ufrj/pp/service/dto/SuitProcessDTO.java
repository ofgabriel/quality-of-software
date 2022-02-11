package com.ufrj.pp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.ufrj.pp.domain.SuitProcess} entity.
 */
public class SuitProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private SuitDTO suit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public SuitDTO getSuit() {
        return suit;
    }

    public void setSuit(SuitDTO suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuitProcessDTO)) {
            return false;
        }

        SuitProcessDTO suitProcessDTO = (SuitProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, suitProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SuitProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", suit=" + getSuit() +
            "}";
    }
}
