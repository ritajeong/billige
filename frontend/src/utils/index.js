import Web3 from "web3";
import { contractAddress, abi, gethHost, coinbase } from "../config";

// 계좌 연동(계좌가 없으면 이때 new account해줌. 아맞다 그리고 이때 지갑생성 버튼을 눌러야 메타마스크 연동하는 게 좋을 듯. 이전것은 서비스 들어가자마자 연동), 
// 빌리 충전, 가입했을 때 빌리 제공, 
// 물건 구매. 환불.

export class Utils {
	
  // static web3 = new Web3(gethHost);
  // static contract = new this.web3.eth.Contract(abi, contractAddress);

  // 이미 회원가입한 유저의 계좌 연동
	static async connectMetamaskForOrigin() {
		let getWeb3 = new Promise((resolve, reject) => {

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
				const provider = new Web3.providers.HttpProvider(
					"http://127.0.0.1:8545"
				);
				const web3 = new Web3(provider);
				console.log("No web3 instance injected, using Local web3.");
				resolve(web3);
			}
	});

	getWeb3
	.then(results => {
			// this.setState({
			// 	web3: results
			// });
			// console.log(this.state)
			// this.instantiateContract();
			// 이곳은 나중에 회원가입 구현완료되면 리듀서로 연결 지을 부분
		})
		.catch(() => {
			console.log("Error finding web3.");
		});
	}

  // 회원가입 시 실행
  static async createAccount() {
    const web3 = this.web3;

    console.log("account creating start");
    const account = await web3.eth.personal.newAccount("ethereum"); // temp password: ethereum
    console.log("account: ", account);
    console.log("created!");

    localStorage.myData = JSON.stringify({
      address: account,
    });
    await this.unlockAccount();
    return account;
  }

  static async unlockAccount() {
    const web3 = this.web3;
    await web3.eth.personal.unlockAccount(JSON.parse(localStorage.myData).address, "ethereum", 0); // unlock inf time
  }

  static async getBalance() {
    const web3 = this.web3;
    return await web3.eth.getBalance(JSON.parse(localStorage.myData).address);
  }

  static async receiveBalance() {
    const web3 = this.web3;
    const ether = web3.utils.toWei("1");
    const currentBalance = await this.web3.eth.getBalance(JSON.parse(localStorage.myData).address);

    if (parseInt(currentBalance) < parseInt(ether)) {
      const rs = await web3.eth.sendTransaction({
        from: coinbase, // main miner. unlocked.
        to: JSON.parse(localStorage.myData).address,
        value: ether,
      });
      return true;
    }
    return false;
  }

  static async call(method, args) {
    const rs = await method(...args).call({
      from: JSON.parse(localStorage.myData).address,
    });
    return rs;
  }

  // 노드에서 계정을 관리할 경우
  static async send(method, args) {
    // ether가 모자랄 경우 수급
    console.log("get balance: ", await this.receiveBalance());

    const account = JSON.parse(localStorage.myData).address;

    try {
      const rs = await method(...args).send({
        from: account,
      });
      return rs;
    } catch (err) {
      console.log("send error: ", err);
    }
  }

  // 로컬에서 계정을 관리할 경우 서명 후 전송
  static async signAndSend(method, args, callback) {
    const web3 = this.web3;
    const privateKey = JSON.parse(localStorage.myData).privateKey;
    const account = JSON.parse(localStorage.myData).address;

    // 트랜잭션 데이터
    const txBuilder = method(...args);
    const encodedTx = txBuilder.encodeABI();

    // 기타 데이터
    const nonce = await web3.eth.getTransactionCount(account);
    const block = await web3.eth.getBlock("latest");
    const gasLimit = block.gasLimit;
    const gasPrice = (await web3.eth.getGasPrice()) * 1.5;

    const txObject = {
      nonce: web3.utils.toHex(nonce),
      gasLimit,
      gasPrice,
      data: encodedTx,
      from: JSON.parse(localStorage.myData).address,
      to: contractAddress,
    };

    // sign
    const rs = await web3.eth.accounts.signTransaction(txObject, privateKey, async (error, signedTx) => {
      if (error) {
        console.log("error!", error);
      } else {
        // send
        try {
          const _rs = await web3.eth.sendSignedTransaction(signedTx.rawTransaction);
          callback(_rs);
        } catch (err) {
          console.log("run error!", err);
        }
      }
    });
    console.log("rs!!", rs);
  }
}
