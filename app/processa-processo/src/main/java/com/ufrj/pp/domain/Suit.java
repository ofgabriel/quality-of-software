package com.ufrj.pp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Suit.
 */
@Entity
@Table(name = "suit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Suit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "suit_number")
    private String suitNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Suit id(Long id) {
        this.id = id;
        return this;
    }

    public String getSuitNumber() {
        return this.suitNumber;
    }

    public Suit suitNumber(String suitNumber) {
        this.suitNumber = suitNumber;
        return this;
    }

    public void setSuitNumber(String suitNumber) {
        this.suitNumber = suitNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Suit)) {
            return false;
        }
        return id != null && id.equals(((Suit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Suit{" +
            "id=" + getId() +
            ", suitNumber='" + getSuitNumber() + "'" +
            "}";
    }
}
