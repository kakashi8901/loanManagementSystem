import React, { useState, useEffect } from "react";
import { createLoan, getLoan, getAllStates, updateLoan } from "../services/LoanService";
import { useNavigate, useParams } from "react-router-dom";

const LoanComponent = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [loanAmount, setLoanAmount] = useState("");
  const [state, setState] = useState("");
  const [gender, setGender] = useState("");
  const [statesInIndia, setStatesInIndia] = React.useState([]);


  

  const { id } = useParams();
  const [errors, setErrors] = useState({
    firstName: "",
    lastName: "",
    email: "",
    loanAmount: "",
    state: "",
    gender: "",
  });

  const navigator = useNavigate();
  const handleDropdownChange = (event) => {
    setState(event.target.value);
  };

  useEffect(() => {
    getAllStates()
    .then(response => {
      const filterStates = response.data.map(item => item.states);
      console.log(filterStates)
      setStatesInIndia(filterStates);
      console.log(statesInIndia)
    })



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

  function saveOrUpdateLoan(e) {
    e.preventDefault();

    if (validateForm()) {
      const loanDto = { firstName, lastName, email, loanAmount, state, gender };
      console.log(loanDto);

      if (id) {
        updateLoan(id, loanDto)
          .then((response) => {
            console.log(response.data);
            navigator("/loans");
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        createLoan(loanDto)
          .then((response) => {
            console.log(response.data);
            navigator("/loans");
          })
          .catch((error) => {
            console.error(error);
          });
      }
    }
  }

  function validateForm() {
    let valid = true;

    const errorsCopy = { ...errors };

    if (firstName.trim()) {
      errorsCopy.firstName = "";
    } else {
      errorsCopy.firstName = "First name is required";
      valid = false;
    }

    if (lastName.trim()) {
      errorsCopy.lastName = "";
    } else {
      errorsCopy.lastName = "Last name is required";
      valid = false;
    }

    if (email.trim()) {
      // Check if the email is in valid format using regular expression
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        errorsCopy.email = "Invalid email format";
        valid = false;
      } else {
        errorsCopy.email = "";
      }
    } else {
      errorsCopy.email = "Email is required";
      valid = false;
    }

    if (loanAmount) {
      errorsCopy.loanAmount = "";
    } else {
      errorsCopy.loanAmount = "Loan Amount is required";
      valid = false;
    }

    if (state) {
      errorsCopy.state = "";
    } else {
      errorsCopy.state = "State is required";
      valid = false;
    }

    if (gender) {
      errorsCopy.gender = "";
    } else {
      errorsCopy.gender = "gender is required";
      valid = false;
    }

    // to check the error logs use this code --> console.log("Errors:", errorsCopy);
    setErrors(errorsCopy);

    return valid;
  }

  function pageTitle() {
    if (id) {
      return <h2 className="text-center">Update Loan</h2>;
    } else {
      return <h2 className="text-center">Add Loan</h2>;
    }
  }
  return (
    <div className="container ">
      <br /> <br />
      <div className="row">
        <div className="glassmorphic-card col-md-6 offset-md-3 offset-md-3">
          {pageTitle()}
          <div className="glassmorphic-card-body">
            <form>
              <div className="form-group mb-2">
                <label className="form-label">First Name:</label>
                <input
                  type="text"
                  placeholder="Enter Customer First Name"
                  name="firstName"
                  value={firstName}
                  className={`form-control ${
                    errors.firstName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setFirstName(e.target.value)}
                ></input>
                {errors.firstName && (
                  <div className="invalid-feedback"> {errors.firstName} </div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Last Name:</label>
                <input
                  type="text"
                  placeholder="Enter Customer Last Name"
                  name="lastName"
                  value={lastName}
                  className={`form-control ${
                    errors.lastName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setLastName(e.target.value)}
                ></input>
                {errors.lastName && (
                  <div className="invalid-feedback"> {errors.lastName} </div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Email:</label>
                <input
                  type="text"
                  placeholder="Enter Customer Email"
                  name="email"
                  value={email}
                  className={`form-control ${errors.email ? "is-invalid" : ""}`}
                  onChange={(e) => setEmail(e.target.value)}
                ></input>
                {errors.email && (
                  <div className="invalid-feedback"> {errors.email} </div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Loan Amount:</label>
                <input
                  type="number"
                  placeholder="Enter Loan Amount"
                  name="loanAmount"
                  value={loanAmount}
                  className={`form-control ${
                    errors.loanAmount ? "is-invalid" : ""
                  }`}
                  onChange={(e) => {
                    const amount = parseInt(e.target.value);
                    if (!isNaN(amount) && amount >= 0 && amount <= 100000000) {
                      setLoanAmount(amount);
                    } else if (e.target.value === "") {
                      // Handle backspace event
                      setLoanAmount(""); // Clear the loanAmount state when input is empty
                    }
                  }}
                ></input>
                {errors.loanAmount && (
                  <div className="invalid-feedback"> {errors.loanAmount} </div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">State:</label>
                <select
                  value={state}
                  onChange={handleDropdownChange}
                  className={`form-control ${errors.state ? "is-invalid" : ""}`}
                >
                  <option value="">Select State</option>
                  {statesInIndia.map((state, index) => (
                    <option key={index} value={state}>
                      {state}
                    </option>
                  ))}
                </select>
                {errors.state && (
                  <div className="invalid-feedback"> {errors.state} </div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Gender:</label>
                <div
                  className={`form-control ${
                    errors.gender ? "is-invalid" : ""
                  }`}
                >
                  <div className="form-check">
                    <label className="form-check-label">
                      <input
                        type="radio"
                        className="form-check-input"
                        name="gender"
                        value="Male"
                        checked={gender === "Male"}
                        onChange={(e) => setGender(e.target.value)}
                      />
                      Male
                    </label>
                  </div>
                  <div className="form-check">
                    <label className="form-check-label">
                      <input
                        type="radio"
                        className="form-check-input"
                        name="gender"
                        value="Female"
                        checked={gender === "Female"}
                        onChange={(e) => setGender(e.target.value)}
                      />
                      Female
                    </label>
                  </div>
                  <div className="form-check">
                    <label className="form-check-label">
                      <input
                        type="radio"
                        className="form-check-input"
                        name="gender"
                        value="Others"
                        checked={gender === "Others"}
                        onChange={(e) => setGender(e.target.value)}
                      />
                      Others
                    </label>
                  </div>
                </div>
                {errors.gender && (
                  <div className="invalid-feedback"> {errors.gender} </div>
                )}
              </div>

              <button className="btn btn-success" onClick={saveOrUpdateLoan}>
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoanComponent;