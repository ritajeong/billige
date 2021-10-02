import React from 'react'
import Web3 from 'web3'
import getWeb3 from './getWeb3'
// import abi from '../../../blockchain/build/contracts/'
// export const abi = require("../../build/contracts/VoteManager.json").abi;

const getFunction = () => {
	function componentWillMount() {
    getWeb3
      .then(results => {
        this.setState({
          web3: results.web3
        });

        this.instantiateContract();
      })
      .catch(() => {
        console.log("Error finding web3.");
      });
  }

	function createContract(){
			let url_infura = 'https://ropsten.infura.io/v3/58319f91b4914ca08bacfb5bdc3b363e';
			let web3 = new Web3(url_infura);

			const abi = [{"inputs":[{"internalType":"uint256","name":"num","type":"uint256"}],"name":"store","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"retrieve","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"}];   
			const address = '0xFd9aCd010DAfbd1e3601c81e0c779033dDFf2443';

			const contract = new web3.eth.Contract(abi, address);

			console.log(contract);
	}
}

export default getFunction
