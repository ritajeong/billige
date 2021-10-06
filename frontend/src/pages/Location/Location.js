import React, { useEffect, useState } from "react";
import axios from "axios";
const { kakao } = window;

const Location = ({ searchPlace }) => {
  // 검색결과 배열에 담아줌
  const [Places, setPlaces] = useState([]);
  const [address, setAddress] = useState("");
  const [locationObj, setLocationObj] = useState();
  const handleClick = (address) => {
    setAddress(address)
    
      axios
        .get(`https://dapi.kakao.com/v2/local/search/address.json?query=${address}`, {
          headers: { Authorization: 'KakaoAK 3c8ffe0fda9423ae3d4595085463213e' },
        })
        .then((res) => {
          console.log(address)
          const location = res.data.documents[0];
          console.log(location?.address?.b_code);
        });
    
  }
  
  useEffect(() => {
    const ps = new kakao.maps.services.Places();

    ps.keywordSearch(searchPlace, placesSearchCB);

    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 페이지 목록 보여주는 displayPagination() 추가
        displayPagination(pagination);
        setPlaces(data);
      }
    }

    // 검색결과 목록 하단에 페이지 번호 표시
    function displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지 번호 삭제
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i + " ";

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    }
  }, [searchPlace]);

  return (
    <div>
      <p>{address}</p>
      <p> {locationObj}</p>
      <div id="result-list" className="result-list">
        {Places.map((item, i) => (
          <div key={i} style={{ marginTop: "20px" }}>
            {/* <span>{i + 1}</span> */}
            <div>
              <h5>{item.place_name}</h5>
              {item.road_address_name ?  (
                <div>
                  <span onClick={() => handleClick(item.road_address_name)}>{item.road_address_name}</span><br/>
                  <span onClick={() => handleClick(item.address_name)}>{item.address_name}</span>
                </div>
              ) : (
                  <span onClick={() => handleClick(item.address_name)}>{item.address_name}</span>
              )}
              {/* <span>{item.phone}</span> */}
            </div>
            <br></br>
            <hr></hr>
          </div>
        ))}
        <br />
        <div id="pagination" className="pagination"></div>
        <br />
      </div>
    </div>
  );
};

export default Location;

