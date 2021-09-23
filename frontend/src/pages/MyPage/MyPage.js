import React from "react";
import { Link } from "react-router-dom";
import { Button } from "semantic-ui-react";

const MyPage = () => {
  return (
    <div>
      마이페이지
      <Link to="/signin">
        <Button>로그인 페이지로</Button>
      </Link>
    </div>
  );
};

export default MyPage;
