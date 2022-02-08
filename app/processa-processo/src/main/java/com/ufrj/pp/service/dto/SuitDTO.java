package com.ufrj.pp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ufrj.pp.domain.Suit} entity.
 */
public class SuitDTO implements Serializable {

    private Long id;

    private String suitNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuitNumber() {
        return suitNumber;
    }

    public void setSuitNumber(String suitNumber) {
        this.suitNumber = suitNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuitDTO)) {
            return false;
        }

        SuitDTO suitDTO = (SuitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, suitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SuitDTO{" +
            "id=" + getId() +
            ", suitNumber='" + getSuitNumber() + "'" +
            "}";
    }
}
