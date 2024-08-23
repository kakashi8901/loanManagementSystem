package net.statestreet.lms.repository;

import java.util.List;
import java.util.Optional;

import net.statestreet.lms.dto.ILoanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.statestreet.lms.entity.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LoanRepository extends JpaRepository<Loan, Long> {
	

	List<ILoanDto> findByLoanStatusTrue();
	List<ILoanDto> findByLoanStatusFalse();



	List<ILoanDto> findAllBy();
	Page<ILoanDto> findAllBy(Pageable p);

	@Query("SELECT l.id AS id, l.firstName AS firstName, l.lastName AS lastName, l.email AS email, l.state AS state, l.gender AS gender, l.loanAmount AS loanAmount, l.loanStatus AS loanStatus FROM Loan l WHERE l.id = :loanId")
	ILoanDto getByLoanId(@Param("loanId") Long loanid);


}
