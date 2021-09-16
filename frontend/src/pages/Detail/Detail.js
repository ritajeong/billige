import React from 'react'
import Product from '../../assets/image/product.png'
import User from '../../assets/image/user.png'
import './Detail.css'
import likeIcon from '../../assets/icons/wish.png'
import { useHistory } from 'react-router';

export const Detail = () => {

	const history = useHistory();
	const onSelectProduct = () => {
		console.log('결제할래')
		history.push('/rent');
	}

	return (
		<div>
			<img src={Product} alt="product" className="detail-product" />
			<div className="detail-profile">
				<img src={User} alt="product" className="detail-user-icon" />
				<div className="detail-user-info">
					<div className="detail-user-name">옥흐 부인</div>
					<div className="detail-user-address">서울특별시 양천구</div>
				</div>
				<div className="detail-like">
					<img src={likeIcon} alt="likeIcon" className="detail-like-icon" />
					<div>관심 등록</div>
				</div>
			</div>
			<br />
			<div className="detail-box">제품 상세</div>
			<br />
			<div className="detail-product-detail">

				<div className="detail-product-name">삼성전자 2020 오디세이 15.6</div>
				<div className="detail-product-category-time">가전제품 | 6분</div>
				<br />
				<div>삼성 게이밍 노트북 오디세이NT850XCJ-Y78A <br />
				본품:R-R-SEC-NT850XCJ, YU10055-20004 <br /> 
				어댑터: SU10462-18002, R-REM-PPQ-PA-1181-96 <br />
				대여율 1위~! 최고의 제품입니다 <br /> <br />
				
				</div>
			</div>
			<div className="detail-inquire-buy">
				<div className="detail-oneday-price">
					<div className="detail-price">200,000원</div>
					<div className="detail-day">1일 기준</div>
				</div>
				<div className="detail-button">
					<button>문의하기</button>
					<button className="detail-mayment" onClick={onSelectProduct}>결제하기</button>
				</div>
			</div>
		</div>
	)
}
export default Detail;