
import React, { useEffect,useState } from "react"
import {getLoan} from "../services/LoanService";
import {  useParams  } from 'react-router-dom'


export function LoanSummary() {

  
  const { id } = useParams();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [loanAmount, setLoanAmount] = useState("");
  const [state, setState] = useState("");
  const [gender, setGender] = useState("");


  useEffect(() => {
    if (id) {
      getLoan(id)
        .then((response) => {
          setFirstName(response.data.firstName);
          setLastName(response.data.lastName);
          setEmail(response.data.email);
          setLoanAmount(response.data.loanAmount);
          setState(response.data.state);
          setGender(response.data.gender);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }, [id]);


  const handleClick = () => {
    window.location.href = '/'; // Redirects to the home page
  };

  
return (
  
    
      <div className="glassmorphic-card">
        <h2 className="text-center m-4">Loan Details</h2>
        <div className="glassmorphic-card">
          <div className="glassmorphic-card-body">
            <h3>Details of Loan id: {id}</h3>
            
            <ul>
              <li>
                <b>Firstname:</b>
                {firstName}
              </li>
              <li>
                <b>Lastname:</b>
                {lastName}
              </li>
              <li>
                <b>Email:</b>
                {email}
              </li>
              <li>
                <b>Loan Amount:</b>
                {loanAmount}
              </li>
              <li>
                <b>State:</b>
                {state}
              </li>
              <li>
                <b>Gender:</b>
                {gender}
              </li>
            </ul>
          </div>

          <div className="container d-flex justify-content-center">
            <button className="btn btn-success" onClick={handleClick}>
              Back to Home
            </button>
          </div>
        </div>
      </div>
    
  
);
}

export default LoanSummary