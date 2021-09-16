import React from "react";
import "./Layout.css";
import homeIcon from "../../assets/icons/home.png";

import star from "../../assets/icons/star.png";
import plus from "../../assets/icons/plus.png";
import chat from "../../assets/icons/chat.png";
import myPage from "../../assets/icons/my.png";
import { Link } from "react-router-dom";
const NavBottom = () => {
  return (
    <div className="nav-bottom">
      <Link to="/">
        <div className="nav-bottom-items">
          <img src={homeIcon} alt="homeIcon" className="nav-bottom-home" />
          <div>홈</div>
        </div>
      </Link>
      <Link to="/wish">
        <div className="nav-bottom-items">
          <img src={star} alt="starIcon" className="nav-bottom-like" />
          <div>찜</div>
        </div>
      </Link>
      <Link to="/write">
        <div className="nav-bottom-items">
          <img src={plus} alt="homeIcon" className="nav-bottom-writing" />
          <div>글쓰기</div>
        </div>
      </Link>
      <Link to="/chat">
        <div className="nav-bottom-items">
          <img src={chat} alt="chatIcon" className="nav-bottom-chatting" />
          <div>채팅</div>
        </div>
      </Link>
      <Link to="/mypage">
        <div className="nav-bottom-items">
          <img src={myPage} alt="myPageIcon" className="nav-bottom-mypage" />
          <div>마이페이지</div>
        </div>
      </Link>
    </div>
  );
};

export default NavBottom;
