import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom';
import Product from '../../assets/image/product.png'
import User from '../../assets/image/user.png'
import './Detail.css'
import likeIcon from '../../assets/icons/wish.png'
import { useHistory } from 'react-router';
import { Button } from "semantic-ui-react";
import axios from 'axios';


export const Detail = () => {
	const { pNo } = useParams();
	const [detail, setDetail] = useState({})
	const [loading, setLoading] = useState(true);
	useEffect(() => {

		const token = JSON.parse(window.localStorage.getItem('token'))
		axios
			.get(`http://localhost:8080/api/item/detail/${pNo}`, {
				headers: {
					Authentication:
						"Bearer " + token,
				}
			}
			)
			.then((response) => {
				setDetail(response.data);
				console.log(response);
				setLoading(false);
			})
			.catch((error) => {
				console.log(error);
			})

	}, [])
	const history = useHistory();
	const onSelectProduct = () => {
		history.push('/rent');
	}

	return (
		<div>
			{loading ? <>loading...</> :
				<>
					<img src={Product} alt="product" className="detail-product" />
					<div className="detail-profile">
						<img src={User} alt="product" className="detail-user-icon" />
						<div className="detail-user-info">
							<div className="detail-user-name">{detail.owner.username}</div>
							<div className="detail-user-address">{detail.position}</div>
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

						<div className="detail-product-name">{detail.itemname}</div>
						<div className="detail-product-category-time">{detail.category} | 6분</div>
						<br />
						<div>{detail.description}

						</div>
					</div>
					<div className="detail-inquire-buy">
						<div className="detail-oneday-price">
							<div className="detail-price">{detail.price} 원</div>
							<div className="detail-day">1일 기준</div>
						</div>
						<div className="detail-button">
							<Button style={{ backgroundColor: "#F5F5F5", color: "black" }}>
								문의하기
							</Button>
							<Button style={{ backgroundColor: "#497C5F", color: "white" }} className="detail-mayment" onClick={onSelectProduct}>
								대여하기
							</Button>
						</div>
					</div>
				</>
			}
		</div>
	)
}
export default Detail;