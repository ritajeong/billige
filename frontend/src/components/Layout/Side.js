import React from "react";
import billige from "../../assets/image/billige.PNG";
import mockup from "../../assets/image/mock.jpg";
import eventImage from "../../assets/image/event.png";
import "./Layout.css";
const Side = () => {
  return (
    <div className="sidebar">
      <img className="logo" src={billige} alt="billige" width="300px" />
      <br />
      <img className="eventimage" src={eventImage} alt="event" width="300px" />
      <br />
      {/* + 이미지 넣을거 만들기 */}
      <div className="mock">
        <p>이미지</p>
      </div>
      {/* <img className="mock" src={mockup} alt="billige" width="250px" /> */}
    </div>
  );
};

export default Side;
