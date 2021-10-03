import Web3 from 'web3'

let getWeb3 = new Promise((resolve, reject) => {
	// Modern dapp browsers...

	if (window.ethereum) {
		const web3 = new Web3(Web3.givenProvider || 'https://ropsten.infura.io/v3/26261cf4c7af4304b492cefe8505e390');
		try {
			// Request account access if needed
			window.ethereum.enable();
			// Accounts now exposed
			resolve(web3);
		} catch (error) {
			reject(error);
		}
	}
	// Legacy dapp browsers...
	else if (window.web3) {
		// Use Mist/MetaMask's provider.
		const web3 = window.web3;
		console.log("Injected web3 detected.");
		resolve(web3);
	}
	// Fallback to localhost; use dev console port by default...
	else {
		alert("메타마스크를 설치하셔야 이용 가능합니다.");
	}
});

export default getWeb3