import React from "react";
import profile from "../../assets/image/user.png";
import { Link } from "react-router-dom";
import "../Wish/Wish.css";
import "./MyProduct.css";

const MyProduct = () => {
  const product = [
    {
      itemId: 1,
      itemname: "비모 피규어",
      price: "4000",
      address: "서울특별시 강남구",
      image: "",
      book: true,
    },
    {
      itemId: 3,
      itemname: "버즈 플러스",
      price: "4000",
      address: "서울특별시 강남구",
      image: "",
      book: true,
    },
  ];

  return product.map((item) => {
    return (
      <div>
        <div className="wish-item-list">
          <img src={profile} className="wish-item-icon" alt="profile"></img>
          <div className="wish-item-vertical">
            <div className="wish-item-title">
              <Link to={`/rentuser/${item.itemId}`}>{item.itemname}</Link>
            </div>
            <span>{item.address}</span>
            <div className="wish-item-price">{item.price} 원</div>
          </div>
          <div>
            <label className="switch">
              <input type="checkbox" />
              <span className="slider round"></span>
            </label>
          </div>
        </div>
      </div>
    );
  });
};

export default MyProduct;
