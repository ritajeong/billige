import React from "react";
import backIcon from "../../assets/icons/back.png";
import { Link, useLocation, withRouter } from "react-router-dom";
import "./Layout.css";
import CurrentPage from "../CurrentPage";
const Header = (props) => {
  const location = useLocation();
  const beforePage = () => {
    props.history.goBack();
  };

  return (
    <div className="header">
      {location.pathname === "/" ? (
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
          <CurrentPage url={location.pathname} />
        </div>
      )}
    </div>
  );
};

export default withRouter(Header);
