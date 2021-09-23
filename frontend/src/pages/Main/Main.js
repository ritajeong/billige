import React from "react";
import Slider from "react-slick";
import { Link } from "react-router-dom";
import { Input } from "semantic-ui-react";
import arrow from "../../assets/icons/next.png";
import "./Main.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import ThumbNail from "../../components/ThumbNail/ThumbNail";

const Main = () => {
  const NextArrow = (props) => {
    const { className, style, onClick } = props;
    return (
      <img
        src={arrow}
        alt="arrow"
        className={className}
        style={{ ...style, display: "block" }}
        onClick={onClick}
      />
    );
  };
  const settings = {
    dots: true,
    infinite: true,
    speed: 1000,
    autoplay: true,
    slidesToShow: 1,
    slidesToScroll: 1,
  };

  const responsiveSettings = {
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    initialSlide: 0,
    nextArrow: <NextArrow />,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 3,
          infinite: true,
        },
      },
    ],
  };

  const nearProduct = [
    {
      pThumbNail: "",
      pName: "강아지 먹이",
      pPrice: "10,000원",
      pNo: "1",
      isRent: "false",
    },
    {
      pThumbNail: "",
      pName: "고양이 먹이",
      pPrice: "11,000원",
      pNo: "2",
      isRent: "true",
    },
    {
      pThumbNail: "",
      pName: "거북이 먹이",
      pPrice: "12,000원",
      pNo: "3",
      isRent: "false",
    },
    {
      pThumbNail: "",
      pName: "옆집 강아지 먹이",
      pPrice: "13,000원",
      pNo: "4",
      isRent: "true",
    },
    {
      pThumbNail: "",
      pName: "옆집 고양이 먹이",
      pPrice: "14,000원",
      pNo: "5",
      isRent: "false",
    },
    {
      pThumbNail: "",
      pName: "옆집 거북이 먹이",
      pPrice: "15,000원",
      pNo: "6",
      isRent: "false",
    },
  ];
  const productCarousel = () => {
    return nearProduct.map((product, idx) => {
      return (
        <div key={idx}>
          <ThumbNail product={product} />
        </div>
      );
    });
  };
  return (
    <div className="main">
      <Input className="main-search" icon="search" iconPosition="left" />

      <Slider {...settings}>
        <div className="carousel-page">
          <h3>여기에</h3>
        </div>
        <div className="carousel-page">
          <h3>무엇을</h3>
        </div>
        <div className="carousel-page">
          <h3>쓰면</h3>
        </div>
        <div className="carousel-page">
          <h3>좋을까요</h3>
        </div>
        <div className="carousel-page">
          <h3>?</h3>
        </div>
      </Slider>

      <div className="main-near-product">
        <h4>가까운 위치에 있는 물건 소개</h4>

        <Slider {...responsiveSettings}>{productCarousel()}</Slider>
      </div>

      <div className="main-current-rent">
        <div className="main-current-rent-header">
          <h4>최근에 대여했어요 ✌🏻</h4>
          <Link to="/mypage" className="rent-header-link">
            {"대여내역 보기 >"}
          </Link>
        </div>

        <Slider {...responsiveSettings}>{productCarousel()}</Slider>
      </div>
    </div>
  );
};

export default Main;
