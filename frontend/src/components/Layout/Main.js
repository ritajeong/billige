import React from "react";
import { Link } from "react-router-dom";

const Main = () => {
  return (
    <div>
      <Link to="/lent">대여하기 페이지로</Link>
      <br />
      <Link to="/detail">제품상세 페이지로</Link>
      <br />
    </div>
  );
};

export default Main;
