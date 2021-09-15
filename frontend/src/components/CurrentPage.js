import React, { useState, useEffect } from "react";

const CurrentPage = (props) => {
  const [pageName, setPageName] = useState();

  useEffect(() => {
    const page = {
      "/": "메인",

      "/location": "위치 정보",

      "/lent": "대여하기",
      "/detail": "제품 상세",
    };
    setPageName(page[props.url]);
  }, [props.url]);

  return <div className="header-location">{pageName}</div>;
};

export default CurrentPage;
