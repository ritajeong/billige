import React from "react";
import "./Category.css";
import data from "./Category.json";
import art from "../../assets/icons/category/art.png";
import beach from "../../assets/icons/category/beach.png";
// import camera from "../../assets/icons/category/camera.png";
// import clothes from "../../assets/icons/category/clothes.png";
// import computer from "../../assets/icons/category/computer.png";
// import cutlery from "../../assets/icons/category/cutlery.png";
// import diamond from "../../assets/icons/category/diamond.png";
// import dog from "../../assets/icons/category/dog.png";
// import drill from "../../assets/icons/category/drill.png";
// import etc from "../../assets/icons/category/etc.png";
// import gamepad from "../../assets/icons/category/gamepad.png";
// import hair from "../../assets/icons/category/hair.png";
// import study from "../../assets/icons/category/study.png";


const Category = () => {
  const imgData = data.category.map((item, index) => {
    return (
      <div key={index}>
      <img src={item.url} alt="icon" className="category-icon" />
      <p className="category-name">{item.name}</p>
    </div>);
  });
  //배열 변경되지 않기 때문에 index를 key로 설정함
  return (
    <>
      <div className="category-table">
        <div className="category-row">
          {imgData}
        </div>
        <div className="category-row">
          <div>
            <img src={art} alt="art" className="category-icon" />
            <p className="category-name">음악</p>
          </div>
          <div>
            <img src={art} alt="art" className="category-icon" />
            <p className="category-name">음악</p>
          </div>
          <img src={art} alt="art" className="category-icon" />
          <img src={art} alt="art" className="category-icon" />
          <img src={art} alt="art" className="category-icon" />
        </div>
        <div className="category-row">
          <img src={art} alt="art" className="category-icon" />
          <img src={art} alt="art" className="category-icon" />
          <img src={art} alt="art" className="category-icon" />
          <img src={art} alt="art" className="category-icon" />
          <img src={art} alt="art" className="category-icon" />
        </div>
      </div>
    </>
  );
};

export default Category;
