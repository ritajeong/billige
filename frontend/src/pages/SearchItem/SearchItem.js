import React, { useState } from "react";
import SearchResult from "./SearchResult";
import "./SearchItem.css";
import close from "../../assets/icons/close.png";
import { Input } from "semantic-ui-react";
const SearchItem = () => {
  const recentSearchList = [
    "방탈출",
    "노트10",
    "강아지 사료",
    "우산",
    "보조배터리",
    "노트북",
    "헤드셋",
  ];
  const [inputText, setInputText] = useState("");
  const [word, setWord] = useState("");
  const onChange = (e) => {
    setInputText(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    setWord(inputText);
    setInputText("");
  };
  return (
    <>
      <form className="inputForm" onSubmit={handleSubmit}>
        <Input
          className="main-search"
          icon="search"
          iconPosition="left"
          onChange={onChange}
          value={inputText}
        />
        <br />
      </form>

      {word === "" ? (
        <>
          <div className="recent-search-top">
            <h3>최근 검색어</h3>
            <p>모두 지우기</p>
          </div>
          <div className="recent-search-list">
            {recentSearchList.map((data, index) => (
              <div className="recent-search-item" key={index}>
                <div className="recent-search-word">
                  <p>{data}</p>
                  <img src={close} width="10px" height="10px" alt="close" />
                </div>
                <hr></hr>
              </div>
            ))}
          </div>
        </>
      ) : (
        <SearchResult searcWord={word} />
      )}
    </>
  );
};

export default SearchItem;
