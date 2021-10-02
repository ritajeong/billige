import React from "react";
import profile from "../../assets/image/user.png";
import "./UserEdit.css";

const UserEdit = () => {
  const user = {
    image: profile,
    userName: "SSAFY",
  };
  return (
    <div className="user-edit">
      <img src={user.image} alt="profile"></img>
      <div>
        <span>프로필 이미지 변경</span>
      </div>
      <div>
        <input type="text" placeholder={user.userName} className="user-edit-input"></input>
        <button className="user-edit-button">완료</button>
      </div>
    </div>
  );
};

export default UserEdit;
