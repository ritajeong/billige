import React from "react";
import profile from "../../assets/image/user.png";
// import wishOn from "../../assets/icons/wish-on.png";
import "./Wish.css";
const Wish = () => {
  const wishProduct = [
    {
      itemname: "비모 피규어",
      price: "4000",
      address: "서울특별시 강남구",
      image: "",
      book: true,
    },
    {
      itemname: "버즈 플러스",
      price: "4000",
      address: "서울특별시 강남구",
      image: "",
      book: true,
    },
  ];
  return wishProduct.map((product, idx) => {
    return (
      <div key={idx}>
        <div className="wish-item-list">
          <img src={profile} className="wish-item-icon" alt="profile"></img>
          <div className="wish-item-vertical">
            <div className="wish-item-title">{product.itemname}</div>
            <span>{product.address}</span>
            <div className="wish-item-price">{product.price} 원</div>
          </div>
          <div>
            <button className="wish-item-borrow">대여하기</button>
          </div>
        </div>
      </div>
    );
  });
};

export default Wish;
