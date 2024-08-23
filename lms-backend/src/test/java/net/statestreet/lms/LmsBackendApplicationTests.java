package net.statestreet.lms;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import net.statestreet.lms.entity.Loan;

import net.statestreet.lms.exception.ResourceNotFoundException;

import net.statestreet.lms.repository.LoanRepository;
import net.statestreet.lms.service.impl.LoanServiceImpl;
public class LmsBackendApplicationTests {
    @Mock
    private LoanRepository loanRepository;//Mock dependency
    @Mock
    private ModelMapper modelMapper; // Ensure ModelMapper is mocked
    @InjectMocks
    private LoanServiceImpl loanService;//Inject mocks into service
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);//initializes all the mocks
    }
    @Test
    public void testCreateLoan_NullLoanDto() {
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(null);
        });
    }

//    @Test
//    public void testCreateLoan_Success() {
//        // Given
//        LoanDto loanDto = new LoanDto(); // Create a valid LoanDto
//        Loan loan = new Loan(); // Create a Loan entity
//        loan.setId(1L); // Set a unique identifier
//
//        when(modelMapper.map(loanDto, Loan.class)).thenReturn(loan);
//        when(loanRepository.save(loan)).thenReturn(loan);
//
//        // When
//        LoanDto result = loanService.createLoan(loanDto);
//
//        // Then
//        assertNotNull(result); // Ensure the returned LoanDto is not null
//        assertEquals(loan.getId(), result.getId()); // Ensure IDs match
//        verify(loanRepository).save(loan); // Verify that the repository's save method was called
//    }
    

//    @Test
//    public void testGetLoanById() {
//    	// Initialize ModelMapper
//        ModelMapper modelMapper = new ModelMapper();
//
//        // Given
//        Long loanId = 1L;//ID to test
//        Loan loan = new Loan(/* create loan object */);
//        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
//     // Create an instance of LoanServiceImpl and pass LoanRepository and ModelMapper to its constructor
//        LoanServiceImpl loanService = new LoanServiceImpl(loanRepository, modelMapper);
//        
//        // When
//        LoanDto result = loanService.getLoanById(loanId);
//        
//        // Then
//        assertNotNull(result);//Ensure it's not null
//        assertEquals(loan.getId(), result.getId());//check ID matches
//       
//    }
   
    @Test
    public void testSanctionLoan_NonExistentLoan() {
        // Given
        Long loanId = 999L; // Non-existent loan ID

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty()); // Mock loan not found

        // When and Then
        assertThrows(ResourceNotFoundException.class, () -> {
            loanService.sanctionLoan(loanId); // This should throw an exception
        });

        verify(loanRepository).findById(loanId); // Ensure findById was called
        verify(loanRepository, never()).save(any(Loan.class)); // Ensure save was not called
    }

    
   

    
    @Test
    public void testGetAllSanctionedLoans_RepositoryThrowsException() {
        // Given
        when(loanRepository.findByLoanStatusTrue()).thenThrow(new RuntimeException("Database error"));

        // When / Then
        assertThrows(RuntimeException.class, () -> {
            loanService.getAllSanctionedLoans(); // Should throw RuntimeException due to database error
        });

        verify(loanRepository).findByLoanStatusTrue(); // Ensure repository call happened
    }
   

    

    @Test
    public void testGetAllPendingLoans_RepositoryThrowsException() {
        // Given
        when(loanRepository.findByLoanStatusFalse()).thenThrow(new RuntimeException("Database error"));

        // When / Then
        assertThrows(RuntimeException.class, () -> {
            loanService.getAllPendingLoans(); // Should throw RuntimeException due to repository error
        });

        verify(loanRepository).findByLoanStatusFalse(); // Ensure repository call happened
    }
    
}


    
    

