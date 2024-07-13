import React, { useState } from 'react';
import axios from 'axios';

const BookTicket = () => {
  const [userId, setUserId] = useState('');
  const [origin, setOrigin] = useState('');
  const [destination, setDestination] = useState('');
  const [travelDate, setTravelDate] = useState('');
  const [seatNumber, setSeatNumber] = useState('');
  const [price, setPrice] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const ticket = { userId, origin, destination, travelDate, seatNumber, price };
    try {
      await axios.post(`${process.env.REACT_APP_TICKET_SERVICE_URL}/api/tickets/book`, ticket);
      alert('Ticket booked successfully!');
    } catch (error) {
      setError('There was an error booking the ticket.');
      console.error('There was an error booking the ticket!', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Book Ticket</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">User ID:</label>
          <input type="text" className="form-control" value={userId} onChange={(e) => setUserId(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Origin:</label>
          <input type="text" className="form-control" value={origin} onChange={(e) => setOrigin(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Destination:</label>
          <input type="text" className="form-control" value={destination} onChange={(e) => setDestination(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Travel Date:</label>
          <input type="datetime-local" className="form-control" value={travelDate} onChange={(e) => setTravelDate(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Seat Number:</label>
          <input type="text" className="form-control" value={seatNumber} onChange={(e) => setSeatNumber(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Price:</label>
          <input type="number" className="form-control" value={price} onChange={(e) => setPrice(e.target.value)} />
        </div>
        <button type="submit" className="btn btn-primary">Book Ticket</button>
      </form>
      {error && <p className="text-danger mt-2">{error}</p>}
    </div>
  );
};

export default BookTicket;
