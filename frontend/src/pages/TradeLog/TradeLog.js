import React, { useEffect, useState } from "react";
import axios from "axios";
import noImage from "../../assets/image/no-image.jpg";
import { Link } from "react-router-dom";
import "./TradeLog.css";

const TradeLog = () => {
  const [product, setProduct] = useState([]);

  useEffect(() => {
    const token = JSON.parse(window.localStorage.getItem("token"));
    axios
      .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/contract/rent`, {
        headers: {
          Authentication: "Bearer " + token,
        },
      })
      .then((response) => {
        setProduct(response.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return product.map((item) => {
    return (
      <div>
        <div className="wish-item-list">
          <img src={item.image == null ? noImage : item.image} className="wish-item-icon" alt="item-image"></img>
          <div className="wish-item-vertical">
            <div className="wish-item-title">
              <Link to={`/detail/${item.itemId}`}>{item.itemname}</Link>
            </div>
            <span>{item.address}</span>
            <div className="wish-item-price">{item.price} 원</div>
          </div>
          <div>
            <button className="trade-log-button">상세보기</button>
          </div>
        </div>
      </div>
    );
  });
};

export default TradeLog;
