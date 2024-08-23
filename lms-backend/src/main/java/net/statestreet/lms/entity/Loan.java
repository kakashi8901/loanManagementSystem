package net.statestreet.lms.entity;



import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Value;



@Value
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    @NotBlank(message = "First Name Must Not be Blank")
    @Size(min=3,message = "First Name Minimum size must be 3")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name Must Not be Blank")
    @Size(min=1,message = "Last Name Minimum size must be 1")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email Must Not be Blank")
    @Email(message = "Email Should be in a right format!")
    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "State Must Not be Blank")
    @Size(min=3,message = "State Minimum size must be 3")
    @Column(name = "state", nullable = false)
    private String state;

    @NotBlank(message = "Gender Must Not be Blank")
    @Size(min=3,message = "Gender Minimum size must be 4")
    @Column(name = "gender")
    private String gender;

    @Min(value = 0,message =  "Loan Amount minimum value should be 0!")
    @Column(name="loan_amount", nullable=false)
    private Double loanAmount;


    @Column(name="loan_status", nullable=false)
    private Boolean loanStatus;

    
    // creating a no args constructor
    public Loan() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.state = null; 
        this.gender = null;
        this.loanAmount = null;
        this.loanStatus = null;
    }


}
