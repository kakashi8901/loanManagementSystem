import React from 'react'

const HeaderComponent = () => {
  return (
    <div>
      <header>
        <nav className="navbar">
          
          <ul>
            <li>
            <a className="navbar-brand" href="/">
            Loan Management System
          </a>
            </li>
            <li>
              <a href="/add-loanDto">Add Loan</a>
            </li>
            <li>
              <a href="/approved-loans">Approved Loans</a>
            </li>
            <li>
              <a href="/pending-loans">Pending Loans</a>
            </li>
          </ul>
        </nav>
      </header>
    </div>
  );
}

export default HeaderComponent