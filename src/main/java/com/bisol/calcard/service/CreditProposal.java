package com.bisol.calcard.service;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A CreditProposal.
 */
public class CreditProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String clientName;
    private Integer clientAge;
    private String taxpayerId;
    private Gender clientGender;
    private MaritalStatus maritalStatus;
    private Integer dependents;
    private BigDecimal income;
    private FederationUnit federationUnit;
    private CreditProposalStatus status;
    private LocalDate creationDate;
    private LocalDate processingDate;
    private RejectionReason rejectionReason;
    private BigDecimal aprovedMin;
    private BigDecimal aprovedMax;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public CreditProposal clientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getClientAge() {
        return clientAge;
    }

    public CreditProposal clientAge(Integer clientAge) {
        this.clientAge = clientAge;
        return this;
    }

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public CreditProposal taxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
        return this;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public Gender getClientGender() {
        return clientGender;
    }

    public CreditProposal clientGender(Gender clientGender) {
        this.clientGender = clientGender;
        return this;
    }

    public void setClientGender(Gender clientGender) {
        this.clientGender = clientGender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public CreditProposal maritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getDependents() {
        return dependents;
    }

    public CreditProposal dependents(Integer dependents) {
        this.dependents = dependents;
        return this;
    }

    public void setDependents(Integer dependents) {
        this.dependents = dependents;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public CreditProposal income(BigDecimal income) {
        this.income = income;
        return this;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public FederationUnit getFederationUnit() {
        return federationUnit;
    }

    public CreditProposal federationUnit(FederationUnit federationUnit) {
        this.federationUnit = federationUnit;
        return this;
    }

    public void setFederationUnit(FederationUnit federationUnit) {
        this.federationUnit = federationUnit;
    }

    public CreditProposalStatus getStatus() {
        return status;
    }

    public CreditProposal status(CreditProposalStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CreditProposalStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public CreditProposal creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getProcessingDate() {
        return processingDate;
    }

    public CreditProposal processingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
        return this;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }

    public CreditProposal rejectionReason(RejectionReason rejectionReason) {
        this.rejectionReason = rejectionReason;
        return this;
    }

    public void setRejectionReason(RejectionReason rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public BigDecimal getAprovedMin() {
        return aprovedMin;
    }

    public CreditProposal aprovedMin(BigDecimal aprovedMin) {
        this.aprovedMin = aprovedMin;
        return this;
    }

    public void setAprovedMin(BigDecimal aprovedMin) {
        this.aprovedMin = aprovedMin;
    }

    public BigDecimal getAprovedMax() {
        return aprovedMax;
    }

    public CreditProposal aprovedMax(BigDecimal aprovedMax) {
        this.aprovedMax = aprovedMax;
        return this;
    }

    public void setAprovedMax(BigDecimal aprovedMax) {
        this.aprovedMax = aprovedMax;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditProposal creditProposal = (CreditProposal) o;
        if (creditProposal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), creditProposal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CreditProposal{" +
            "id=" + getId() +
            ", clientName='" + getClientName() + "'" +
            ", clientAge=" + getClientAge() +
            ", taxpayerId='" + getTaxpayerId() + "'" +
            ", clientGender='" + getClientGender() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", dependents=" + getDependents() +
            ", income=" + getIncome() +
            ", federationUnit='" + getFederationUnit() + "'" +
            ", status='" + getStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", processingDate='" + getProcessingDate() + "'" +
            ", rejectionReason='" + getRejectionReason() + "'" +
            ", aprovedMin=" + getAprovedMin() +
            ", aprovedMax=" + getAprovedMax() +
            "}";
    }
}
