import React from "react";
import "./Category.css";
import art from "../../assets/icons/category/art.png";
// import beach from "../../assets/icons/category/beach.png";
// import camera from "../../assets/icons/category/camera.png";
// import clothes from "../../assets/icons/category/clothea.png";
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
  return (
    <>
      <div className="category-table">
        <div className="category-row">
          <div>
            <img src={art} alt="art" className="category-icon" />
            <p className="category-name">음악</p>
          </div>
          <img src={art} alt="art" className="category-icon" />
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
