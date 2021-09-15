import React from "react";
import "./Layout.css";
import homeIcon from "../../assets/icons/icons/home.png";
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
      <div className="nav-bottom-items">
        <img src={homeIcon} alt="homeIcon" className="nav-bottom-like" />
        <div>찜</div>
      </div>
      <div className="nav-bottom-items">
        <img src={homeIcon} alt="homeIcon" className="nav-bottom-writing" />
        <div>글쓰기</div>
      </div>
      <div className="nav-bottom-items">
        <img src={homeIcon} alt="homeIcon" className="nav-bottom-chatting" />
        <div>채팅</div>
      </div>
      <div className="nav-bottom-items">
        <img src={homeIcon} alt="homeIcon" className="nav-bottom-mypage" />
        <div>마이페이지</div>
      </div>
    </div>
  );
};

export default NavBottom;
