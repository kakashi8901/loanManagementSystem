package net.statestreet.lms.service.impl;

import lombok.AllArgsConstructor;
import net.statestreet.lms.dto.ILoanDto;

import net.statestreet.lms.entity.Loan;
import net.statestreet.lms.exception.ResourceNotFoundException;

import net.statestreet.lms.repository.LoanRepository;
import net.statestreet.lms.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
	


    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Loan createLoan(Loan iloanDto) {
    	 Loan loan = new Loan(
				 iloanDto.getId(),
				 iloanDto.getFirstName(),
				 iloanDto.getLastName(),
				 iloanDto.getEmail(),
				 iloanDto.getState(),
				 iloanDto.getGender(),
				 iloanDto.getLoanAmount(),
	                false // setting loan status to false while using constructor
	        );

		 
        Loan savedLoan = loanRepository.save(loan);
        return savedLoan;
    }

    @Override
    public ILoanDto getLoanById(Long loanId) {

		ILoanDto loan= loanRepository.getByLoanId(loanId);
		if(loan==null)
			throw  new ResourceNotFoundException("Loan", "id",loanId+"");


		return  loan;
    }


    @Override
    public List<ILoanDto> getAllLoans() {
        List<ILoanDto> loans = loanRepository.findAllBy();
		return loans;
    }

    @Override
    public Loan updateLoan(Long loanId, Loan updatedLoan) {

        Loan oldLoan = loanRepository.findById(loanId).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "id",loanId+""));

   	 Loan loan = new Loan(
   			oldLoan.getId(),
   			updatedLoan.getFirstName(),
   			updatedLoan.getLastName(),
   			updatedLoan.getEmail(),
   			updatedLoan.getState(),
   			updatedLoan.getGender(),
   			updatedLoan.getLoanAmount(),
   			oldLoan.getLoanStatus()
     );
        

        Loan updatedLoanObj = loanRepository.save(loan);

        return updatedLoanObj;
    }

    @Override
    public void deleteLoan(Long loanId) {

        Loan loan = loanRepository.findById(loanId).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "id",loanId+""));

        loanRepository.deleteById(loanId);
    }

	@Override
	public void sanctionLoan(Long loanId) {
		 Loan loan = loanRepository.findById(loanId).orElseThrow(
	                () -> new ResourceNotFoundException("Loan", "id",loanId+""));
	        Loan updatedLoan = new Loan(
	                loan.getId(),
	                loan.getFirstName(),
	                loan.getLastName(),
	                loan.getEmail(),
	                loan.getState(),
	                loan.getGender(),
	                loan.getLoanAmount(),
	                true // setting loan status to true
	        );

		  loanRepository.save(updatedLoan);
		  
		
	}


	@Override
	public List<ILoanDto> getAllSanctionedLoans() {
		List<ILoanDto> loans= loanRepository.findByLoanStatusTrue();
		return  loans;

	}

	@Override
	public List<ILoanDto> getAllPendingLoans() {
		List<ILoanDto> loans = loanRepository.findByLoanStatusFalse();
		return loans;
	}

	@Override
	public Page<ILoanDto> getAllPagedLoans(Integer pageNumber, Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<ILoanDto> pages = loanRepository.findAllBy(p);

/**        This was a custom paging object now its replaced by JPAPageObject
		List<Loan>loans=pages.getContent();
		LoanPaging paging=new LoanPaging();

		 List<LoanDto> dtoLoans=loans.stream().map((loan) -> LoanMapper.mapToLoanDto(loan))
                .collect(Collectors.toList());
	    	paging.setContent(dtoLoans);
	    	paging.setPageNumber(pages.getNumber());
	    	paging.setPageSize(pages.getSize());
	    	paging.setTotalElements(pages.getTotalElements());
	    	paging.setTotalPages(pages.getTotalPages());
	    	paging.setLastPage(pages.isLast());

	    	return paging;
 **/

        return pages;
		
	}


}
