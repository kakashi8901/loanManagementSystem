package net.statestreet.lms.dto;

public interface ILoanDto {

    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getState();
    String getGender();
    Double getLoanAmount();
    Boolean getLoanStatus();


}
