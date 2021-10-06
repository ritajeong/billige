import React, { useState } from "react"; 
import "./SearchItem.css";
import "./SearchResult.css";
import { Input } from "semantic-ui-react";
import queryString from 'query-string'
import Filter from "./Filter";
import product from "../../assets/image/product.png";

const SearchItem = ({location, match}) => {
  const query = queryString.parse(location.search);
  console.log(query);
  const text = query.text;
  
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

  const [visible, setVisible] = useState(false);
  const onClickFilter = () => {
    setVisible(!visible);
  };
  console.log(text);
  return (
    <>
      <form className="inputForm" onSubmit={handleSubmit}>
        <p>검색어 : {text}</p>
        <Input
          className="main-search"
          icon="search"
          iconPosition="left"
          onChange={onChange}
          value={inputText}
        />
        <br />
      </form>
      {!visible && (
        <div className="search-result">
          <h3>최근 검색어</h3>
          <hr></hr>
          <h4 onClick={onClickFilter}>검색필터</h4>
          <hr></hr>
          <div className="search-result-item">
            <div>
              <img src={product} alt="product" className="mypage-user-icon" />
            </div>
            <div>
              <h3>유레카 노트북</h3>
              <p>대치동</p>
              <h4>15,000원</h4>
            </div>
          </div>
          <hr></hr>
          <div className="search-result-item">
            <div>
              <img src={product} alt="product" className="mypage-user-icon" />
            </div>
            <div>
              <h3>유레카 노트북</h3>
              <p>대치동</p>
              <h4>15,000원</h4>
            </div>
          </div>
          <hr></hr>
          <div className="search-result-item">
            <div>
              <img src={product} alt="product" className="mypage-user-icon" />
            </div>
            <div>
              <h3>유레카 노트북</h3>
              <p>대치동</p>
              <h4>15,000원</h4>
            </div>
          </div>
          <hr></hr>
        </div>
      )}
      {visible && <Filter />}
      {/* {text === "" ? (
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
        <SearchResult searcWord={text} />
      )} */}
    </>
  );
};

export default SearchItem;
