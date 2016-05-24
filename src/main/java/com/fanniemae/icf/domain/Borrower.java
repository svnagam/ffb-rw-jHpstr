package com.fanniemae.icf.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Borrower.
 */
@Entity
@Table(name = "borrower")
public class Borrower implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "borrower_id", nullable = false)
    private Long borrowerId;

    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "borrower_first_name", length = 20, nullable = false)
    private String borrowerFirstName;

    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "borrower_last_name", length = 20, nullable = false)
    private String borrowerLastName;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "borrower_middle_name")
    private String borrowerMiddleName;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "borrower_suffix")
    private String borrowerSuffix;

    @NotNull
    @Size(min = 9, max = 9)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "borrower_ssn", length = 9, nullable = false)
    private String borrowerSsn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerFirstName() {
        return borrowerFirstName;
    }

    public void setBorrowerFirstName(String borrowerFirstName) {
        this.borrowerFirstName = borrowerFirstName;
    }

    public String getBorrowerLastName() {
        return borrowerLastName;
    }

    public void setBorrowerLastName(String borrowerLastName) {
        this.borrowerLastName = borrowerLastName;
    }

    public String getBorrowerMiddleName() {
        return borrowerMiddleName;
    }

    public void setBorrowerMiddleName(String borrowerMiddleName) {
        this.borrowerMiddleName = borrowerMiddleName;
    }

    public String getBorrowerSuffix() {
        return borrowerSuffix;
    }

    public void setBorrowerSuffix(String borrowerSuffix) {
        this.borrowerSuffix = borrowerSuffix;
    }

    public String getBorrowerSsn() {
        return borrowerSsn;
    }

    public void setBorrowerSsn(String borrowerSsn) {
        this.borrowerSsn = borrowerSsn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Borrower borrower = (Borrower) o;
        if(borrower.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, borrower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Borrower{" +
            "id=" + id +
            ", borrowerId='" + borrowerId + "'" +
            ", borrowerFirstName='" + borrowerFirstName + "'" +
            ", borrowerLastName='" + borrowerLastName + "'" +
            ", borrowerMiddleName='" + borrowerMiddleName + "'" +
            ", borrowerSuffix='" + borrowerSuffix + "'" +
            ", borrowerSsn='" + borrowerSsn + "'" +
            '}';
    }
}
