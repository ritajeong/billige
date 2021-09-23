import React from "react";
import logo from "../../assets/image/billige.PNG";
import { Button, Input } from "semantic-ui-react";
import { Link } from "react-router-dom";
import kakao from "../../assets/icons/kakao-talk.png";
import "./SignIn.css";
const SignIn = () => {
  const SignInKakao = () => {
    alert("카카오로 로그인");
  };
  return (
    <div className="signin">
      <div className="signin-logo-wrap">
        <img src={logo} alt="logo" width="60%" />
      </div>

      <form className="signin-form">
        <Input placeholder="이메일 입력" />
        <Input type="password" placeholder="비밀번호 입력" />
        <Button className="signin-button">로그인</Button>
      </form>
      <div className="signin-option">
        <Link to="/findpwd">비밀번호 찾기</Link>
        <span>|</span>
        <Link to="/signup">{"회원가입  "}</Link>
      </div>
      <hr />
      <div className="signin-kakao" onClick={SignInKakao}>
        <img src={kakao} alt="kakao" className="signin-kakao-logo" />
        카카오로 간편 로그인
      </div>
    </div>
  );
};

export default SignIn;
