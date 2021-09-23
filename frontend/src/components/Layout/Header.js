import React from "react";
import backIcon from "../../assets/icons/back.png";
import { Link } from "react-router-dom";

import CurrentPage from "./CurrentPage";

import "./Layout.css";
const Header = (props) => {
  const beforePage = () => {
    props.history.goBack();
  };

  return (
    <div className="header">
      {props.location.pathname === "/" ? (
        <div className="header-location">
          <Link to="/location">서울특별시 강남구</Link>
        </div>
      ) : (
        <div className="header-pages">
          <img
            src={backIcon}
            alt="homeIcon"
            className="header-icon"
            onClick={beforePage}
          />
          <CurrentPage url={props.location.pathname} />
        </div>
      )}
    </div>
  );
};

export default Header;
