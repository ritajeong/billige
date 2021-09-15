import React from "react";
import { Link } from "react-router-dom";
import backIcon from "../../assets/icons/icons/back.png"

import "./Layout.css";
const Header = () => {
  

  return (
    <div className="header">
      {/* <div className="header-location">서울특별시 강남구</div> */}
      <div className="header-pages">
        <img src={backIcon} alt="homeIcon" className="header-icon"/>
        <div className="header-location">회원가입</div>
      </div>
    </div>
  );
};

export default Header;
