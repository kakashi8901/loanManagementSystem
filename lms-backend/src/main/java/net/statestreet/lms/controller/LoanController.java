package net.statestreet.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.statestreet.lms.dto.ILoanDto;
import net.statestreet.lms.entity.Loan;
import net.statestreet.lms.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/loans")//changed
public class LoanController {

    @Autowired
    private LoanService loanService;







    // Create Loan
    @PostMapping
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody  Loan loanDto){

        Loan savedLoan = loanService.createLoan(loanDto);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    // Getting loan by id
    @GetMapping("{id}")
    public ResponseEntity<ILoanDto> getLoanById(@PathVariable("id") Long loanId){
        ILoanDto loanDto = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loanDto);
    }

    // Get All loan Api
    @GetMapping
    public ResponseEntity<List<ILoanDto>> getAllLoans(){
        List<ILoanDto> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    // Update Loan REST API
    @PutMapping("{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") Long loanId,
                                              @Valid @RequestBody  Loan updatedLoan){
          Loan iloanDto = loanService.updateLoan(loanId, updatedLoan);
          return ResponseEntity.ok(iloanDto);
    }

    // Delete Loan REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable("id") Long loanId){
    	loanService.deleteLoan(loanId);
        return ResponseEntity.ok("Loan deleted successfully!.");
    }
    
    @PutMapping("/sanction/{id}")
    public ResponseEntity<String> sanctionLoan(@PathVariable("id") Long loanId){
    	loanService.sanctionLoan(loanId);
    	return ResponseEntity.ok("Loan is Sanctioned Sucessfully");
    }
    
    @GetMapping("/sanctionedloan")
    public ResponseEntity<List<ILoanDto>> getSanctionedLoans(){
    	List<ILoanDto> allSanctionedLoans = loanService.getAllSanctionedLoans();
    	return ResponseEntity.ok(allSanctionedLoans);
    }
    
    @GetMapping("/pendingloan")
    public ResponseEntity<List<ILoanDto>> getPendingLoans(){
    	List<ILoanDto> allPendingLoans = loanService.getAllPendingLoans();
    	return ResponseEntity.ok(allPendingLoans);
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<ILoanDto>> getAllLoans(
    		@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,
    		@RequestParam(value="pageSize",defaultValue="5",required=false) Integer pageSize
    		){
    	 Page<ILoanDto> allPagedLoans = loanService.getAllPagedLoans(pageNumber, pageSize);
    	
    	return ResponseEntity.ok(allPagedLoans);
    }
    
    // Kafka producer
//    @PutMapping("/produce/{id}")
//    public ResponseEntity<String> produceLoan(@PathVariable("id") Long loanId){
//    	kafkaService.publishLoan(loanId);
//    	return ResponseEntity.ok("Loan is Sanctioned Sucessfully");
//    }
}
