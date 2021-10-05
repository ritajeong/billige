import React from "react";
import profile from "../../assets/image/defaultuser.png";
import { Link } from "react-router-dom";
import "./RentUserList.css";

const RentUserList = ({history}) => {
  const rentUser = [
    {
      uid: 1,
      contractId: 1,
      userName: "지노진호",
      userAddress: "서울특별시 강남구",
      startDate: "2021-09-03",
      endDate: "2021-09-09",
    },
    {
      uid: 2,
      contractId: 2,
      userName: "민재민잼",
      userAddress: "서울특별시 강남구",
      startDate: "2021-08-17",
      endDate: "2021-08-18",
    },
    {
      uid: 3,
      contractId: 3,
      userName: "동욱우기",
      userAddress: "서울특별시 강남구",
      startDate: "2021-08-11",
      endDate: "2021-08-12",
    },
  ];

  const goToTradeDetail = (contractId) => {
    history.push(`/tradedetail/${contractId}`);
  }

  return rentUser.map((user) => {
    return (
      <div className="rent-user">
        <div className="rent-user-list rent-user-box">
          <img src={profile} className="rent-user-image" alt="profile"></img>
          <div className="rent-user-vertical">
            <div className="rent-user-name">{user.userName}</div>
            <span>{user.userAddress}</span>
            <br />
            <span className="rent-user-period">
              {user.startDate.replaceAll("-", ".")} ~ {user.endDate.replaceAll("-", ".")}
            </span>
          </div>
        </div>
        <div className="rent-user-box">
          <button onClick={() => goToTradeDetail(user.contractId)}>대여상세</button>
          <button>채팅하기</button>
        </div>
      </div>
    );
  });
};

export default RentUserList;
