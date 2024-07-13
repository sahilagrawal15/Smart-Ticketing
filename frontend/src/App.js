import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import RegisterUser from './components/RegisterUser';
import BookTicket from './components/BookTicket';
import ProcessPayment from './components/ProcessPayment';

const App = () => {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/register">Register User</Link></li>
            <li><Link to="/book-ticket">Book Ticket</Link></li>
            <li><Link to="/process-payment">Process Payment</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<h2>Welcome to the Smart Ticketing System</h2>} />
          <Route path="/register" element={<RegisterUser />} />
          <Route path="/book-ticket" element={<BookTicket />} />
          <Route path="/process-payment" element={<ProcessPayment />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
