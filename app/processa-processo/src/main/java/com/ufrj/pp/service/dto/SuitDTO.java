package com.ufrj.pp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.ufrj.pp.domain.Suit} entity.
 */
public class SuitDTO implements Serializable {

    private Long id;

    private String suitNumber;

    private String name;

    private LocalDate date;

    private String claimed;

    private String lawyer;

    private String court;

    private String value;

    private Boolean shouldGroup;

    private Boolean shouldReceiveNotification;

    private LawyerDTO watcher;

    private GroupDTO group;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClaimed() {
        return claimed;
    }

    public void setClaimed(String claimed) {
        this.claimed = claimed;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getShouldGroup() {
        return shouldGroup;
    }

    public void setShouldGroup(Boolean shouldGroup) {
        this.shouldGroup = shouldGroup;
    }

    public Boolean getShouldReceiveNotification() {
        return shouldReceiveNotification;
    }

    public void setShouldReceiveNotification(Boolean shouldReceiveNotification) {
        this.shouldReceiveNotification = shouldReceiveNotification;
    }

    public LawyerDTO getWatcher() {
        return watcher;
    }

    public void setWatcher(LawyerDTO watcher) {
        this.watcher = watcher;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
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
            ", name='" + getName() + "'" +
            ", date='" + getDate() + "'" +
            ", claimed='" + getClaimed() + "'" +
            ", lawyer='" + getLawyer() + "'" +
            ", court='" + getCourt() + "'" +
            ", value='" + getValue() + "'" +
            ", shouldGroup='" + getShouldGroup() + "'" +
            ", shouldReceiveNotification='" + getShouldReceiveNotification() + "'" +
            ", watcher=" + getWatcher() +
            ", group=" + getGroup() +
            "}";
    }
}
