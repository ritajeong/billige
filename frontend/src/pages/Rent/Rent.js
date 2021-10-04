import React, { useState, useEffect } from "react";
import 'react-date-range/dist/styles.css'; // main style file
import 'react-date-range/dist/theme/default.css'; // theme css file
import { DateRange } from 'react-date-range';
import "./styles.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowCircleRight } from '@fortawesome/free-solid-svg-icons'
import Web3 from 'web3';
// import getWeb3 from '../../utils/getWeb3'

import { getFunction } from "../../utils/getFunction";
import truffleContract from "truffle-contract";
import BliCoin from '../../config/BliToken.json';

const Rent = () => {

	// const user = {
	// 	shopInstance: null,
	// 	myAccount: null,
	// 	web3: null,
	// };

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

const pricePerDay = 10 // 나중에 상품 상세 페에지에서 props로 받을 데이터.
const today = new Date().toString().split(' ');
const [state, setState] = useState([
	{
		startDate: new Date(),
		endDate: new Date(),
		key: 'selection',
		color: '#C5D3CC',
		preview: {
			color: '#C5D3CC',
		},
	}
]);
const [rentalStartYear, setrentalStartYear] = useState(today[3]);
const [rentalStartMonth, setrentalStartMonth] = useState(stringToNum[today[1]]);
const [rentalStartDay, setrentalStartDay] = useState(today[2]);
const [rentalEndYear, setrentalEndYear] = useState(today[3]);
const [rentalEndMonth, setrentalEndMonth] = useState(stringToNum[today[1]]);
const [rentalEndDay, setrentalEndDay] = useState(today[2]);
const [price, setPrice] = useState('0');

// const [smartContract, setSmartContract] = useState({
// 	web3: null,
// 	accounts: null,
// 	contract: null,
// 	address: "",
// 	TesBalance: null,
// 	EthBalance: null,
// 	sendAmount: "",
// 	sendAddress: "",
// 	txList: [],
// });

const [web3, setWeb3] = useState('')
const [accounts, setAccounts] = useState('')
const [contract, setContract] = useState('')
const [bliContract, setBliContract] = useState('')
const [address, setAddress] = useState('')


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

const buyProduct = () => {
	let minABI = [
		// transfer
		{
				"constant": false,
				"inputs": [
						{
								"name": "_to",
								"type": "address"
						},
						{
								"name": "_value",
								"type": "uint256"
						}
				],
				"name": "transfer",
				"outputs": [
						{
								"name": "success",
								"type": "bool"
						}
				],
				"payable": false,
				"stateMutability": "nonpayable",
				"type": "function"
		}
		];
		let contractAddress="0x14CDEab2be4b34364BB866320d5BF129B1727C4A";
		let contract = new web3.eth.Contract(minABI, contractAddress);
		let value = web3.utils.toWei("10", "ether");
		contract.methods.transfer("0xF683ffC5A39a92827F3A6a69b9f11F12B9abFc7e", value).send({from: accounts[0]});
}


useEffect(() => {
	setWeb3(new Web3(Web3.givenProvider))
}, [])

useEffect(() => {
	if (web3 !== ''){
		getFunction.connectMetamask()
    .then(result =>{
			setAccounts(result)
		})

	// setBliContract(truffleContract(BliCoin));

	// await instance.balanceOf(accounts[0]).then((balance) => {
	// 		this.setState({TesBalance: balance.c[0] * 0.01});
	// })
	// await web3.eth.getBalance(accounts[0]).then((balance) => {
	// 		this.setState({EthBalance: balance * 0.000000000000000001});
	// });
	}
}, [web3])

useEffect(() => {
	if (bliContract !== '') {
		console.log(2323)
		console.log(bliContract)
		// bliContract.setProvider(web3.currentProvider);
		// bliContract.deployed()
		// .then(result => {
		// 	setContract(result)
		// })
	}
}, [bliContract])

useEffect(() => {
	if (contract !== '') {
		contract.balanceOf(accounts)
		.then(result => {
			console.log(1111)
			console.log(result)
		})
	}
}, [contract])

// componentDidMount = async () => {
// 	try{
// 			const web3 = await getWeb3();
// 			const accounts = await web3.eth.getAccounts();
// 			const Contract = truffleContract(TesCoin);
// 			Contract.setProvider(web3.currentProvider);
// 			const instance = await Contract.deployed();
// 			this.setState({web3, accounts, contract: instance, address: String(accounts[0])});
// 			await instance.balanceOf(accounts[0]).then((balance) => {
// 					this.setState({TesBalance: balance.c[0] * 0.01});
// 			})
// 			await web3.eth.getBalance(accounts[0]).then((balance) => {
// 					this.setState({EthBalance: balance * 0.000000000000000001});
// 			});
// 			// await instance.Transfer().watch((error, result) => this.watchTransfer(error, result));
// 			// await instance.Approval().watch((error, result) => this.watchApproval(error, result));
// 			// let {address, privateKey} = await web3.eth.accounts.create();
// 			// console.log(address, privateKey);
// 			console.log(instance.address);
// 	} catch (error) {
// 			alert("Failed to load web3, accounts, or contract. Check console for details.");
// 			console.log(error);
// 	}
// }

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
						<FontAwesomeIcon icon={faArrowCircleRight} size="2x" onClick={buyProduct}/>
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
