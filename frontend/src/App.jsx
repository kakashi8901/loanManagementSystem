import './App.css'
import LoanComponent from './components/LoanComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListLoanComponent from './components/ListLoanComponent'
import ListApprovedLoanComponent from './components/ListApprovedLoanComponent'
import ListPendingLoanComponent from './components/ListPendingLoanComponent'
import LoanSummary from './components/LoanSummary'


import {BrowserRouter, Routes, Route} from 'react-router-dom'

function App() {

  return (
    <>
      <BrowserRouter>
      <div className='body'>
      <HeaderComponent />
          <Routes>
            {/* // http://localhost:3000 */}
              <Route path='/' element = { <ListLoanComponent />}></Route>
              {/* // http://localhost:3000/employees */}
              <Route path='/loans' element = { <ListLoanComponent /> }></Route>

              {/* // http://localhost:3000/approved-employees */}
              <Route path='/approved-loans' element = { <ListApprovedLoanComponent /> }></Route>

              {/* // http://localhost:3000/pending-employees */}
              <Route path='/pending-loans' element = { <ListPendingLoanComponent /> }></Route>

              {/* // http://localhost:3000/add-employee */}
              <Route path='/add-loanDto' element = { <LoanComponent />}></Route>

              {/* // http://localhost:3000/edit-employee/1 */}
              <Route path='/edit-loanDto/:id' element = { <LoanComponent /> }></Route>

              <Route path='/viewloan/:id' element = { <LoanSummary /> }></Route>
          </Routes>
        <FooterComponent />
      </div>
        
      </BrowserRouter>
    </>
  )
}

export default App
