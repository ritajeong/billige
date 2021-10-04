import React from 'react';
import './TradeDetail.css';
import product from "../../assets/image/product.png";

const TradeDetail = () => {
    const contract = {
        item: {
            itemname: "오디세이",
            price: 3000,
            address: "서울특별시 강남구"
        },
        user: {
            userName: "옥흐부인"
        },
        startDate: '2021-09-21',
        endDate: '2021-09-23',
        totalPrice: 30000
    };
    return (
        <div className="trade-detail-body">
            <div className="trade-detail-product">
                <img src={product} alt="product"></img>
                <div>
                    <div className="trade-item-name">{contract.item.itemname}</div>
                    <div>주소</div>
                </div>
            </div>
            <div>
                <button className="trade-cancel-button">대여취소</button>
            </div>
            <div className="trade-detail-list">
                <div className="trade-detail-title">
                    <div className="trade-detail-info">대여자</div>
                    <div className="trade-detail-info">대여일</div>
                    <div className="trade-detail-info">반납일</div>
                    <div className="trade-detail-info">가격</div>
                </div>
                <div>
                    <div className="trade-detail-info">{contract.user.userName}</div>
                    <div className="trade-detail-info">{contract.startDate.replaceAll("-", ".")}</div>
                    <div className="trade-detail-info">{contract.endDate.replaceAll("-", ".")}</div>
                    <div className="trade-detail-info">{contract.item.price.toLocaleString('ko-KR')} 원</div>
                </div>
            </div>
            <hr></hr>
            <div className="trade-detail-list">
                <div className="total">Total</div>
                <div className="price">{contract.totalPrice.toLocaleString('ko-KR')} 원</div>
            </div>
        </div>
    );
}

export default TradeDetail