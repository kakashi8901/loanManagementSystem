import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/loans';
const REST_API_BASE_STATE_URL = 'http://localhost:8080/api/';

export const listLoans = (pageNumber,pageSize) => {
 
    return axios.get(`${REST_API_BASE_URL}/page?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  };

export const createLoan = (loan) => axios.post(REST_API_BASE_URL, loan);

export const getLoan = (loanId) => axios.get(REST_API_BASE_URL + '/' + loanId);

export const updateLoan = (loanId, loan) => axios.put(REST_API_BASE_URL + '/' + loanId, loan);

export const deleteLoan = (loanId) => axios.delete(REST_API_BASE_URL + '/' + loanId);

// below url if for sanction loan based on id 
export const sanctionLoan = (loanId) => axios.put(REST_API_BASE_URL + '/' +"produce"+"/"+ loanId);
//below url is for getting list of approved loans
export const listApprovedLoans = () => axios.get(REST_API_BASE_URL + '/sanctionedloan');

//below url is for getting list of pending loans
export const listPendingLoans = () => axios.get(REST_API_BASE_URL + '/pendingloan');

export const getAllStates = () => axios.get(REST_API_BASE_STATE_URL+'statesinindia/states')