import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import profile from "../../assets/image/user.png";
import star from "../../assets/icons/star.png";
import eth from "../../assets/icons/eth.png";
import arrow from "../../assets/icons/arrow-right.png";
import trade from "../../assets/icons/trade.png";
import "./MyPage.css";
const MyPage = () => {
  const user = {
    name: "SSAFY",
    email: "ssafy@ssafy.com",
    profile: profile,
    money: "4,000",
    tradelog: [
      {
        userName: "거래한사람",
        date: "2021-09-24 16:00:00",
        pName: "a",
        pPrice: "2,000",
      },
      {
        userName: "거래한사람2",
        date: "2021-09-25 16:00:00",
        pName: "b",
        pPrice: "3,000",
      },
    ],
    wishlist: [
      {
        pName: "a",
        pPrice: "2,000",
      },
      {
        pName: "b",
        pPrice: "3,000",
      },
    ],
  };

  return (
    <div className="mypage">
      <div className="mypage-profile">
        <img src={profile} alt="product" className="mypage-user-icon" />
        <div className="mypage-profile-desc">
          <h4>{user.name} 님 안녕하세요!</h4>
          <span>{user.email}</span>
        </div>
        <Link to="/useredit">
          <img src={arrow} width="20px" alt="arrow" />
        </Link>
      </div>

      <div className="mypage-user-info">
        <Link to="/tradelog">
          <div className="user-info">
            <img src={trade} width="30px" alt="eth" />
            <p>
              거래횟수 <span>{user.tradelog.length}</span>
            </p>
          </div>
        </Link>

        <Link to="/charge">
          <div className="user-info">
            <img src={eth} width="30px" alt="eth" />
            <p>
              보유금 <span>{user.money}</span>
            </p>
          </div>
        </Link>

        <Link to="/wish">
          <div className="user-info">
            <img src={star} width="30px" alt="eth" />
            <p>
              찜 <span>{user.wishlist.length}</span>
            </p>
          </div>
        </Link>
      </div>

      <div className="division-bar"></div>

      <div className="mypage-options">
        <Link to="/signin">
          <div className="mypage-option">
            <p>로그인 (임시)</p> <img src={arrow} width="20px" alt="arrow" />
          </div>
        </Link>

        <Link to="/tradelog">
          <div className="mypage-option">
            <p>거래내역</p>
            <img src={arrow} width="20px" alt="arrow" />
          </div>
        </Link>

        <Link to="/">
          <div className="mypage-option">
            <p>로그아웃</p> <img src={arrow} width="20px" alt="arrow" />
          </div>
        </Link>
      </div>
    </div>
  );
};

export default MyPage;
