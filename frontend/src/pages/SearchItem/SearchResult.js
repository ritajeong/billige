import React, { useState } from "react";
import Filter from "./Filter";
import "./SearchResult.css"
const SearchResult = ({ searchPlace }) => {
  const [visible, setVisible] = useState(false);
  const onClickFilter = () => {
    setVisible(!visible);
  };
  return (
    <>
      {!visible &&
        <div className="search-result">
          <h3>최근 검색어</h3>
          <hr></hr>
          <h4 onClick={onClickFilter}>검색필터</h4>
          <hr></hr>
        </div>
      }
      {visible && <Filter />}
    </>
  );
};

export default SearchResult;
