import React, { useState } from 'react';
import axios from 'axios';

const RegisterUser = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const user = { username, password, email, role: 'USER' };
    try {
      await axios.post(`${process.env.REACT_APP_USER_SERVICE_URL}/api/users/register`, user);
      alert('User registered successfully!');
    } catch (error) {
      setError('There was an error registering the user.');
      console.error('There was an error registering the user!', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Register User</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Username:</label>
          <input type="text" className="form-control" value={username} onChange={(e) => setUsername(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Password:</label>
          <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div className="mb-3">
          <label className="form-label">Email:</label>
          <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <button type="submit" className="btn btn-primary">Register</button>
      </form>
      {error && <p className="text-danger mt-2">{error}</p>}
    </div>
  );
};

export default RegisterUser;
