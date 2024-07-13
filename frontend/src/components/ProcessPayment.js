import React, { useState } from 'react';
import axios from 'axios';

const ProcessPayment = () => {
  const [ticketId, setTicketId] = useState('');
  const [amount, setAmount] = useState('');
  const [paymentDate, setPaymentDate] = useState('');
  const [status, setStatus] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payment = { ticketId, amount, paymentDate, status };
    try {
      await axios.post(`${process.env.REACT_APP_PAYMENT_SERVICE_URL}/api/payments/process`, payment);
      alert('Payment processed successfully!');
    } catch (error) {
      setError('There was an error processing the payment.');
      console.error('There was an error processing the payment!', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Process Payment</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Ticket ID:</label>
          <input type="text" className="form-control" value={ticketId} onChange={(e) => setTicketId(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Amount:</label>
          <input type="number" className="form-control" value={amount} onChange={(e) => setAmount(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Payment Date:</label>
          <input type="datetime-local" className="form-control" value={paymentDate} onChange={(e) => setPaymentDate(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Status:</label>
          <input type="text" className="form-control" value={status} onChange={(e) => setStatus(e.target.value)} />
        </div>
        <button type="submit" className="btn btn-primary">Process Payment</button>
      </form>
      {error && <p className="text-danger mt-2">{error}</p>}
    </div>
  );
};

export default ProcessPayment;
