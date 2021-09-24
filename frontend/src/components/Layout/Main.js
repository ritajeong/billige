import React from "react";
import { Link } from "react-router-dom";
import { Button } from "semantic-ui-react";
const Main = () => {
  return (
    <div>  
      <Link to="/rent">대여하기 페이지로</Link>
      <br />
      <Link to="/detail">제품상세 페이지로</Link>
      <br />
      <Link to="/location">위치검색 페이지로</Link>
      <br />

      <Button style={{ backgroundColor: "#497C5F", color: "white" }}>
        버튼테스트
      </Button>
    </div>
  );
};

export default Main;
