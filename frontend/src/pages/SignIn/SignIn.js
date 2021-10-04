import React, { useState } from "react";
import logo from "../../assets/image/billige.PNG";
import { Button, Input } from "semantic-ui-react";
import { Link } from "react-router-dom";
import kakao from "../../assets/icons/kakao-talk.png";

import "./SignIn.css";
import axios from 'axios';

const SignIn = ({ history }) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const signin = (e) => {
    e.preventDefault();
    axios
      .post(`http://localhost:8080/api/user/login`, {
        userEmail: email,
        userPassword: password,
      })
      .then((response) => {
        window.localStorage.setItem("token", JSON.stringify(response.headers.authentication.split(" ")[1].replaceAll('"', '')));
        history.push('/mypage')
      })
      .catch(() => {
        alert("아이디 혹은 비밀번호를 확인해주세요")
      })
  }
  const SignInKakao = () => {
    alert("카카오로 로그인");
  };

  const onChangeEmail = (e) => {
    setEmail(e.target.value);
  }

  const onChangePassword = (e) => {
    setPassword(e.target.value);
  }
  return (
    <div className="signin">
      <div className="signin-logo-wrap">
        <img src={logo} alt="logo" width="60%" />
      </div>

      <form className="signin-form" onSubmit={e => { signin(e) }}>
        <Input placeholder="이메일 입력" onChange={onChangeEmail} />
        <Input type="password" placeholder="비밀번호 입력" onChange={onChangePassword} />
        <Button className="signin-button" type='submit'>로그인</Button>
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
