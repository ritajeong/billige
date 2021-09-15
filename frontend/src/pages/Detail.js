import React from 'react'
import Product from '../assets/image/product.png'
import './Detail.css'

export const Detail = () => {
	return (
		<div>
			<img src={Product} alt="product" className="detail-product" />
			<div className="detail-profile">

					<img src={Product} alt="product" className="detail-user-icon" />
					<div className="detail-user-info">
						<p>옥흐 부인</p>
						<p className="detail-user-address">서울특별시 양천구</p>
					</div>
				<div>
					<p>관심등록</p>
				</div>
			</div>
			<div>제품 상세</div>
		</div>
	)
}
export default Detail;