import React, { useEffect, useState } from "react";
import {
  deleteLoan,
  listLoans,
  sanctionLoan,
} from "../services/LoanService";
import { useNavigate } from "react-router-dom";
import {Pagination, PaginationItem, PaginationLink, Container } from 'reactstrap'

const ListLoanComponent = () => {
  


  const [loanContent, setLoanContent] = useState({
    content: [],
    totalPages: "",
    totalElements: "",
    pageSize: "",
    lastPage: false,
    pageNumber: "",
  });

  const [currentPage, setCurrentPage] = useState(0);

  const navigator = useNavigate();

  useEffect(() => {
    changePage(currentPage);
  }, [currentPage]);

  const changePage = (pageNumber = 0, pageSize = 5) => {
    if (pageNumber > loanContent.pageNumber && loanContent.lastPage) {
      return;
    }
    if (pageNumber < loanContent.pageNumber && loanContent.pageNumber == 0) {
      return;
    }
    listLoans(pageNumber, pageSize)
      .then((response) => {
        setLoanContent({
          content: response.data.content, 
          totalPages: response.data.totalPages,
          totalElements: response.data.totalElements,
          pageSize: response.data.pageable.pageSize,
          lastPage: response.data.last,
          pageNumber: response.data.pageable.pageNumber,
        });
 
        console.log("Loans data:", response.data); // Logging the data
      })
      .catch((error) => {
        toast.error("Error in loading posts");
      });
  };
  

  function updateLoan(id) {
    navigator(`/edit-loanDto/${id}`);
  }

  function viewLoan(id) {
    navigator(`/viewloan/${id}`);
  }

  function approveLoan(id) {
    sanctionLoan(id)
      .then((response) => {
        changePage(currentPage);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function removeLoan(id) {
    console.log(id);

    deleteLoan(id)
      .then((response) => {
        changePage(currentPage);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  return (
    <div className="table-container">
      <div class="box">
      <h2 className="text-center">List of Loans</h2>

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
  {loanContent.content.map((content) => (
    <tr key={content.id}>
      <td>{content.id}</td>
      <td>{content.firstName}</td>
      <td>{content.lastName}</td>
      <td>{content.email}</td>
      <td>{content.loanAmount}</td>
      <td>{content.state}</td>
      <td>{content.gender}</td>
      <td>
      <button
          className="btn btn-light"
          onClick={() => viewLoan(content.id)}
        >
          View
        </button>
        <button
          className="btn btn-info"
          onClick={() => updateLoan(content.id)}
        >
          Update
        </button>

        {content.loanStatus === false && (
          <button
            className="btn btn-success"
            onClick={() => approveLoan(content.id)}
          >
            Approve
          </button>
        )}

        <button
          className="btn btn-danger"
          onClick={() => removeLoan(content.id)}
        >
          Delete
        </button>
      </td>
    </tr>
  ))}
</tbody>

      </table>

      
        <Pagination size="lg" className="glassmorphic-pagination">
          <PaginationItem
            onClick={() => changePage(loanContent.pageNumber - 1)}
            disabled={loanContent.pageNumber == 0}
          >
            <PaginationLink previous>Previous</PaginationLink>
          </PaginationItem>

          {[...Array(loanContent.totalPages)].map((item, index) => (
            <PaginationItem
              onClick={() => changePage(index)}
              active={index == loanContent.pageNumber}
              key={index}
            >
              <PaginationLink>{index + 1}</PaginationLink>
            </PaginationItem>
          ))}

          <PaginationItem
            onClick={() => changePage(loanContent.pageNumber + 1)}
            disabled={loanContent.lastPage}
          >
            <PaginationLink next>Next</PaginationLink>
          </PaginationItem>
        </Pagination>
      
      </div>
    </div>
  );
};

export default ListLoanComponent;
