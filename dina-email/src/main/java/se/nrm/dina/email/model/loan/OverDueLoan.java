package se.nrm.dina.email.model.loan;

import java.util.Date; 


/**
 *
 * @author idali
 */
public final class OverDueLoan {
     
    private String loanNumber;
    private Date dueDate;
    private Date loanDate;
    private Person borrower; 
    private Person secondaryBorrower;
    private Person preparedBy; 
    
    public OverDueLoan() {
        
    }
    
    public OverDueLoan(String loanNumber, Date dueDate, Date loanDate, Person borrower, Person secondaryBorrower, Person preparedBy) {
        this.loanNumber = loanNumber;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        this.borrower = borrower; 
        this.secondaryBorrower = secondaryBorrower;
        this.preparedBy = preparedBy;
    }

    public Person getBorrower() {
        return borrower;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public Person getPreparedBy() {
        return preparedBy;
    }

    public Person getSecondaryBorrower() {
        return secondaryBorrower;
    } 
}
