import React, { useEffect, useState } from "react";
import noImage from "../../assets/image/no-image.jpg";
import { Link } from "react-router-dom";
import "../Wish/Wish.css";
import "./MyProduct.css";
import axios from "axios";

const MyProduct = (props) => {
  const [product, setProduct] = useState([]);
  const [checked, setChecked] = useState(false);

  const token = JSON.parse(window.localStorage.getItem("token"));

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/item`, {
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

  const isActive = (idx) => {
    if (product[idx].status == "Y") product[idx].status = "N";
    else product[idx].status = "Y";
    itemOnOff(idx);
    console.log(product);
  };

  const itemOnOff = (idx) => {
    axios
      .put(
        `${process.env.REACT_APP_SERVER_BASE_URL}/api/item/active/${product[idx].itemId}`,
        {},
        {
          headers: {
            Authentication: "Bearer " + token,
          },
        }
      )
      .then((response) => {
        console.log("success");
      })
      .catch((err) => {
        console.log("fail");
      });
  };

  return product.map((item, idx) => {
    return (
      <div>
        <div className="wish-item-list">
          <img src={item.image == null ? noImage : item.image} className="wish-item-icon" alt="profile"></img>
          <div className="wish-item-vertical">
            <div className="wish-item-title">
              <Link to={`/rentuser/${item.itemId}`}>{item.itemname}</Link>
            </div>
            <span>{item.position}</span>
            <div className="wish-item-price">{item.price} 원</div>
          </div>
          <div>
            <label className="switch">
              {item.status == "Y" ? (
                <input type="checkbox" onClick={() => isActive(idx)} />
              ) : (
                <input type="checkbox" checked onClick={() => isActive(idx)} />
              )}
              {/* <input type="checkbox" onClick={() => isActive(idx)} checked={item.status == "Y"} /> */}
              <span className="slider round"></span>
            </label>
          </div>
        </div>
      </div>
    );
  });
};

export default MyProduct;
