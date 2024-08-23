import React, { useEffect, useState } from "react";
import { deleteLoan,sanctionLoan,listApprovedLoans } from "../services/LoanService";
import { useNavigate } from "react-router-dom";

const ListApprovedLoanComponent = () => {
    const [loans, setLoans] = useState([]);

    const navigator = useNavigate();

    useEffect(() => {
        getApprovedLoans();
      }, []);

      function getApprovedLoans() {
        listApprovedLoans()
          .then((response) => {
            setLoans(response.data);
          })
          .catch((error) => {
            console.error(error);
          });
      }
      function updateLoan(id) {
        navigator(`/edit-loanDto/${id}`);
      }
    
      function approveLoan(id) {
        sanctionLoan(id)
        .then((response) => {
          getApprovedLoans();
          })
          .catch((error) => {
            console.error(error);
          });
      }
    
      function removeLoan(id) {
        console.log(id);
    
        deleteLoan(id)
          .then((response) => {
            getApprovedLoans();
          })
          .catch((error) => {
            console.error(error);
          });
      }
    
      return (
        <div className="table-container">
          <div class="box">
          <h2 className="text-center">List of Approved Loans</h2>

          <table className="table table-striped table-bordered table-custom">
            <thead>
              <tr>
                <th>Loan Id</th>
                <th>Customer First Name</th>
                <th>Customer Last Name</th>
                <th>Customer Email Id</th>
                <th>Loan Amount</th>
                <th>Customer Place</th>
                <th>Customer Gender</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {loans.map((loanDto) => (
                <tr key={loanDto.id}>
                  <td>{loanDto.id}</td>
                  <td>{loanDto.firstName}</td>
                  <td>{loanDto.lastName}</td>
                  <td>{loanDto.email}</td>
                  <td>{loanDto.loanAmount}</td>
                  <td>{loanDto.state}</td>
                  <td>{loanDto.gender}</td>
                  <td>
                    <button
                      className="btn btn-info"
                      onClick={() => updateLoan(loanDto.id)}
                    >
                      Update
                    </button>
    
                    {loanDto.loanStatus === false && (
                      <button
                        className="btn btn-success"
                        onClick={() => approveLoan(loanDto.id)}
                      >
                        Approve
                      </button>
                    )}
    
                    <button
                      className="btn btn-danger"
                      onClick={() => removeLoan(loanDto.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          </div>
        </div>
      );
    };
    
    export default ListApprovedLoanComponent;