import { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';

import './App.css';
import { useLocalState } from './util/useLocalStorage';
import Dashboard from './components/Dashboard';
import Homepage from './components/Homepage';
import Login from './components/Login';
import PrivateRoute from './components/PrivateRoute';
import Register from './components/Register';
import Reservation from './components/Reservation/Reservation';

function App() {  

  return (
    <Routes>
      <Route path="/dashboard" element={
        <PrivateRoute>
          <Dashboard />
        </PrivateRoute>}/>
      <Route path="/" element={<Homepage />}/>
      <Route path="/login" element={<Login />}/>
      <Route path="/register" element={<Register />}/>
      <Route path="/reservation" element={
        <PrivateRoute>
         <Reservation />
        </PrivateRoute>}/>
    </Routes>
  );
}

export default App;
