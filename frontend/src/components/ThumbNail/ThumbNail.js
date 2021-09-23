import React from "react";
import { Link } from "react-router-dom";

import "./ThumbNail.css";
const ThumbNail = (props) => {
  return (
    <div>
      <Link to={`/detail/${props.product.pNo}`}>
        <div className="thumbnail">
          {props.product.isRent === "true" ? (
            <span className="ribbon-angle">
              <small className="card-ribbon">{"대여중"}</small>
            </span>
          ) : (
            ""
          )}
          썸네일
        </div>
        <p>
          <span>{props.product.pName}</span>
          <br />
          {props.product.pPrice}
        </p>
      </Link>
    </div>
  );
};

export default ThumbNail;
