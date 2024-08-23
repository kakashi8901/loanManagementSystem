package net.statestreet.lms.service;

import java.util.List;

import net.statestreet.lms.dto.ILoanDto;

import net.statestreet.lms.entity.Loan;
import org.springframework.data.domain.Page;

public interface LoanService {
    Loan createLoan(Loan loan);//createLoan replace with loan(name) entire page

    ILoanDto getLoanById(Long loanId);



    List<ILoanDto> getAllLoans();

    Loan updateLoan(Long loanId, Loan updatedLoan);

    void deleteLoan(Long loanId);
    
    void sanctionLoan(Long loanId);
    
    List<ILoanDto> getAllSanctionedLoans();

    Page<ILoanDto> getAllPagedLoans(Integer pageNumber, Integer pageSize);
    
    List<ILoanDto> getAllPendingLoans();
}
