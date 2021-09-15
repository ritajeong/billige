import React from "react";
import { Link } from "react-router-dom";

import "./Layout.css";
const Header = () => {
  return (
    <div className="header">
      헤더
      <Link to="/location">위치</Link>
    </div>
  );
};

export default Header;
