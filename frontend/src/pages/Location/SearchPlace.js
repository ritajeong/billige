// SearchPlace.js

import React, { useState } from "react";
import Location from "./Location";
import "./styles.css"
import { Button } from "semantic-ui-react";

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
      { /* 첫 진입시(아무것도 입력하지 않았을 때) 안내 멘트 */ }
      {place === '' ? (
        <h2 className="title">지번, 도로명, 건물명을 입력하세요 </h2>
      ) : (
        <br></br>
      )}
      
      { /* 검색바 */ }
      <form className="inputForm" onSubmit={handleSubmit}>
        <input
          className="searchBar"
          placeholder="Search Place..."
          onChange={onChange}
          value={inputText}
        /> 
        <br />
      </form>

      { /* 첫 진입시 최근 주소가 뜨고, 그 외엔 검색 결과를 출력*/ }
      {place === '' ? (
        <div>
          <button className="btn">
          현 위치로 주소 설정
          </button>
          <br/>
          <hr></hr>
          <h2 className="title"> 최근 주소</h2>
        </div>
      ) : (
        <Location searchPlace={place} />
      )} 
    </>
  );
};

export default SearchPlace;