import React, { useEffect } from "react";
const { kakao } = window;

const Location = () => {

  useEffect(() => {
    const container = document.getElementById("myMap");
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);
  }, []);

  return (
    <>
      <input type="text"></input>
      <button>검색</button>
      <br/>
      <div
        id="myMap"
        style={{
          width: "500px",
          height: "500px",
        }}
        ></div>
    </>
  );
};

export default Location;
