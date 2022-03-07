package com.ufrj.pp.domain;

import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "claimed")
    private String claimed;

    @Column(name = "lawyer")
    private String lawyer;

    @Column(name = "court")
    private String court;

    @Column(name = "value")
    private String value;

    @Column(name = "should_group")
    private Boolean shouldGroup;

    @Column(name = "should_receive_notification")
    private Boolean shouldReceiveNotification;

    @ManyToOne
    private Lawyer watcher;

    @ManyToOne
    private Group group;

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

    public String getName() {
        return this.name;
    }

    public Suit name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Suit date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClaimed() {
        return this.claimed;
    }

    public Suit claimed(String claimed) {
        this.claimed = claimed;
        return this;
    }

    public void setClaimed(String claimed) {
        this.claimed = claimed;
    }

    public String getLawyer() {
        return this.lawyer;
    }

    public Suit lawyer(String lawyer) {
        this.lawyer = lawyer;
        return this;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public String getCourt() {
        return this.court;
    }

    public Suit court(String court) {
        this.court = court;
        return this;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getValue() {
        return this.value;
    }

    public Suit value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getShouldGroup() {
        return this.shouldGroup;
    }

    public Suit shouldGroup(Boolean shouldGroup) {
        this.shouldGroup = shouldGroup;
        return this;
    }

    public void setShouldGroup(Boolean shouldGroup) {
        this.shouldGroup = shouldGroup;
    }

    public Boolean getShouldReceiveNotification() {
        return this.shouldReceiveNotification;
    }

    public Suit shouldReceiveNotification(Boolean shouldReceiveNotification) {
        this.shouldReceiveNotification = shouldReceiveNotification;
        return this;
    }

    public void setShouldReceiveNotification(Boolean shouldReceiveNotification) {
        this.shouldReceiveNotification = shouldReceiveNotification;
    }

    public Lawyer getWatcher() {
        return this.watcher;
    }

    public Suit watcher(Lawyer Lawyer) {
        this.setWatcher(Lawyer);
        return this;
    }

    public void setWatcher(Lawyer Lawyer) {
        this.watcher = Lawyer;
    }

    public Group getGroup() {
        return this.group;
    }

    public Suit group(Group Group) {
        this.setGroup(Group);
        return this;
    }

    public void setGroup(Group Group) {
        this.group = Group;
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
            ", name='" + getName() + "'" +
            ", date='" + getDate() + "'" +
            ", claimed='" + getClaimed() + "'" +
            ", lawyer='" + getLawyer() + "'" +
            ", court='" + getCourt() + "'" +
            ", value='" + getValue() + "'" +
            ", shouldGroup='" + getShouldGroup() + "'" +
            ", shouldReceiveNotification='" + getShouldReceiveNotification() + "'" +
            "}";
    }
}
