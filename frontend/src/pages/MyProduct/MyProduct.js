import React from "react";
import profile from "../../assets/image/user.png";
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

  return product.map((item, idx) => {
    return (
      <div key={item.itemId}>
        <div className="wish-item-list">
          <img src={profile} className="wish-item-icon" alt="profile"></img>
          <div className="wish-item-vertical">
            <div className="wish-item-title">{item.itemname}</div>
            <span>{item.address}</span>
            <div className="wish-item-price">{item.price} 원</div>
          </div>
          <div>
            <label class="switch">
              <input type="checkbox" />
              <span class="slider round"></span>
            </label>
          </div>
        </div>
      </div>
    );
  });
};

export default MyProduct;
