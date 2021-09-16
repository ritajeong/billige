import React, {useState} from "react";
import 'react-date-range/dist/styles.css'; // main style file
import 'react-date-range/dist/theme/default.css'; // theme css file
import { DateRange } from 'react-date-range';
import "./styles.css"


const Rent = () => {
	
const [state, setState] = useState([
	{
		startDate: new Date(),
		endDate: null,
		key: 'selection',
		color: '#C5D3CC',
		preview: {
			color: '#E96F02',
		}
	}
]);

  return (
		<div>
		<div className="calendar">
		<DateRange
		editableDateInputs={true}
		onChange={item => setState([item.selection])}
		moveRangeOnFirstSelection={false}
		ranges={state}
/>
		</div>
		<div className="rent-date">

		<div className="rent-raental-date"></div>
		<div className="rent-raental-date"></div>
		</div>
		</div>
	)
};

export default Rent;
