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
import Logout from './components/Login/Logout';

function App() {  

  return (
    <Routes>
      <Route path="/" element={<Dashboard />}></Route>
      <Route path="/login" element={<Login />}/>
      <Route path="/logout" element={<Logout />}/>
      <Route path="/register" element={<Register />}/>
      <Route path="/reservation" element={
        <PrivateRoute>
         <Reservation />
        </PrivateRoute>}/>
    </Routes>
    
  );
}

export default App;
