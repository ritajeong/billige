import React, { useState, useEffect } from "react";

const CurrentPage = (props) => {
  const [pageName, setPageName] = useState();

  useEffect(() => {
    const page = {
      "/": "메인",

      "/searchplace": "대여할 장소",

      "/rent": "대여하기",
      "/detail": "제품 상세",
      "/write": "대여 게시글 등록",
      "/mypage": "마이페이지",
      "/wish": "관심목록",
      "/chat": "채팅",
    };
    setPageName(page[props.url]);
  }, [props.url]);

  return <div className="header-location">{pageName}</div>;
};

export default CurrentPage;
