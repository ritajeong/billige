import React, {useState} from "react";
import 'react-date-range/dist/styles.css'; // main style file
import 'react-date-range/dist/theme/default.css'; // theme css file
import { DateRange } from 'react-date-range';
import "./styles.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowCircleRight } from '@fortawesome/free-solid-svg-icons'

const Rent = () => {

const stringToNum = {
	Jan: 1,
	Feb: 2,
	Mar: 3,
	Apr: 4,
	May: 5,
	Jun: 6,
	Jul: 7,
	Aug: 8,
	Sep: 9,
	Oct: 10,
	Nov: 11,
	Dec: 12
}
const pricePerDay = 200000 // 나중에 상품 상세 페에지에서 props로 받을 데이터.

const today = new Date().toString().split(' ');
const [state, setState] = useState([
	{
		startDate: new Date(),
		endDate: new Date(),
		key: 'selection',
		color: '#C5D3CC',
		preview: {
			color: '#C5D3CC',
		}
	}
]);
const [rentalStartYear, setrentalStartYear] = useState(today[3]);
const [rentalStartMonth, setrentalStartMonth] = useState(stringToNum[today[1]]);
const [rentalStartDay, setrentalStartDay] = useState(today[2]);
const [rentalEndYear, setrentalEndYear] = useState(today[3]);
const [rentalEndMonth, setrentalEndMonth] = useState(stringToNum[today[1]]);
const [rentalEndDay, setrentalEndDay] = useState(today[2]);
const [price, setPrice] = useState('0');

const onChangeDate = (item) => {
	setState([item.selection]);
	const y1 = Number(item.selection['startDate'].toString().split(' ')[3]);
	const m1 = Number(stringToNum[item.selection['startDate'].toString().split(' ')[1]]);
	const d1 = Number(item.selection['startDate'].toString().split(' ')[2]);
	const y2 = Number(item.selection['endDate'].toString().split(' ')[3]);
	const m2 = Number(stringToNum[item.selection['endDate'].toString().split(' ')[1]]);
	const d2 = Number(item.selection['endDate'].toString().split(' ')[2]);
	setrentalStartYear(y1);
	setrentalStartMonth(m1);
	setrentalStartDay(d1);
	setrentalEndYear(y2);
	setrentalEndMonth(m2);
	setrentalEndDay(d2);

	const date1 = new Date(y1, m1, d1);
	const date2 = new Date(y2, m2, d2);

	const elapsedMSec = date2.getTime() - date1.getTime();
	const elapsedDay = elapsedMSec / 1000 / 60 / 60 / 24;
	setPrice((elapsedDay*pricePerDay).toLocaleString());
}

  return (
		<div>
			<div className="calendar">
			<DateRange
			editableDateInputs={true}
			onChange={onChangeDate}
			// onChange={item => setState([item.selection])}
			moveRangeOnFirstSelection={false}
			ranges={state}
	/>
			</div>
			<br />
			<div className="rent-date">
				<div className="rent-raental-box">
					<div>대여일</div>
					<br />
					<div className="rent-raental-date">{rentalStartYear}년 {rentalStartMonth}월 {rentalStartDay}일</div>
				</div>
				<div className="rent-return-box">
					<div>반납일</div>
					<br />
					<div className="rent-return-date">{rentalEndYear}년 {rentalEndMonth}월 {rentalEndDay}일</div>
				</div>
		</div>
		<div className="rent-estimated-price-box">
				<div className="rent-estimated-comment">
				예상주문금액
				</div>
				<div className="rent-nbutton-price">
					<div className="rent-next-button">
						<FontAwesomeIcon icon={faArrowCircleRight} size="2x"/>
					</div>
					<div className="rent-price-krw">
						<div className="rent-estimated-price">
							{price}&nbsp;
						</div>
						<div className="rent-estimated-krw">
							원
						</div>
					</div>
				</div>
		</div>
		</div>
	)
};

export default Rent;
