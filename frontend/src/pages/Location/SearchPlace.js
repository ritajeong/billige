// SearchPlace.js

import React, { useState } from "react";
import Location from "./Location";
import "./styles.css"

const SearchPlace = () => {
  const [inputText, setInputText] = useState("");
  const [place, setPlace] = useState("");

  const onChange = (e) => {
    setInputText(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setPlace(inputText);
    setInputText("");
  };

  return (
    <>
      <h2 className="title">지번, 도로명, 건물명을 입력하세요 </h2>
      <form className="inputForm" onSubmit={handleSubmit}>
        <input
          className="searchBar"
          placeholder="Search Place..."
          onChange={onChange}
          value={inputText}
        /> 
        <br />
      </form>
      <br />
      <hr></hr>
      <Location searchPlace={place} />
    </>
  );
};

export default SearchPlace;