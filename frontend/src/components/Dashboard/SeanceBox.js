import React from 'react';
import { Link } from 'react-router-dom';
import './seance.css';

const SeanceBox = ({ seance }) => {
    return (
      <div className="seance-box">
        <p>Title:<br/> {seance.movie.title}</p>
        <p>Duration:<br/> {seance.movie.duration}min</p>
        <p>Start Date:<br/> {seance.startDate}</p>
        <p>End Date:<br/> {seance.endDate}</p>
        <p>Room:<br/> {seance.room.name}</p>
        <p>Available seats:<br/> {seance.availableSeats}/{seance.room.seats}</p>
        <Link to='/reservation'
          state = {seance } >
          <button>Reservate</button>
        </Link>
      </div>
    );
  }

export default SeanceBox;