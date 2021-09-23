import React from "react";
import billige from "../../assets/image/billige.PNG";
import eventImage from "../../assets/image/event.png";
import "./Layout.css";
const Side = () => {
  return (
    <div className="sidebar">
      <img className="logo" src={billige} alt="billige" width="300px" />
      <br />
      <img className="eventimage" src={eventImage} alt="event" width="300px" />
      <br />

      <div className="mock">
        <p>이미지</p>
      </div>
    </div>
  );
};

export default Side;
