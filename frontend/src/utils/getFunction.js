import React, { Component } from "react";
import Web3 from "web3";
import getWeb3 from "./getWeb3";
import ShopContract from "../config/Billige.json"
import { bliTokenAddress } from "../config/index";

// 계좌 연동(계좌가 없으면 이때 new account해줌. 아맞다 그리고 이때 지갑생성 버튼을 눌러야 메타마스크 연동하는 게 좋을 듯. 이전것은 서비스 들어가자마자 연동), 
// 빌리 충전, 가입했을 때 빌리 제공, 
// 물건 구매. 환불.

export class getFunction {

  static shopInstance;
  static myAccount;
  static web3;
  static myApples;

  constructor() {

    // this.state = {
    //   shopInstance: null, // shopInstance 추가
    //   myAccount: null,    // myAccount 추가
    //   myApples: 0,  // myApples 추가
    //   web3: null
    // };
  }
	
  // static web3 = new Web3(gethHost);
  // static contract = new this.web3.eth.Contract(abi, contractAddress);

  // 모든 회원 계좌 연동. 메타마스크 설치를 전제로 한다.
	static async connectMetamask() {
    const results = await getWeb3;
    this.web3 = results;
    await this.instantiateContract();
    return this.myAccount;
	}

  static async instantiateContract() {
    const contract = require("truffle-contract");
    const shop = contract(ShopContract);
    shop.setProvider(this.web3.currentProvider); 
    try{
      this.myAccount = await this.web3.eth.getAccounts();
      this.shopInstance = await shop.deployed();
      // await this.web3.eth.getAccounts((error, accounts) => {
      //   console.log(7);
        // shop.deployed().then(instance => {
        //   console.log(77);
        //   this.shopInstance = instance;
        //   this.myAccount = accounts[0];
        // })
      // })
    } catch {
      console.log("err")
    }

    // await this.web3.eth.getAccounts((error, accounts) => {
    //   console.log(7)
    //   console.log(accounts)
    //   if (!error) {
    //     shop.deployed().then(instance => {
    //       // console.log(instance);
    //       console.log(77);
    //       // console.log(instance.buyApple);
    //       this.shopInstance = instance;
    //       this.myAccount = accounts[0];

    //       // const contract = new Web3Client.eth.Contract(minABI, tokenAddress);
    //       // this.setState({ shopInstance: instance, myAccount: accounts[0] });
    // //       console.log(this.state.myAccount);
    //       // this.updateMyApples();
    //     });
    //   }
    // });
  }

  static async getBliCoin(){
    const minABI = [
      // balanceOf
      {
        constant: true,
        inputs: [{ name: "_owner", type: "address" }],
        name: "balanceOf",
        outputs: [{ name: "balance", type: "uint256" }],
        type: "function",
      },
    ];
    var web3 = window.web3
    const Web3Client = new Web3(web3.currentProvider)
    const tokenAddress = bliTokenAddress;
    const walletAddress = this.myAccount;
    const coinContract = new Web3Client.eth.Contract(minABI, tokenAddress);

    async function getBalance() {
      const bli_result = await coinContract.methods.balanceOf(walletAddress[0]).call();
      const format = Web3Client.utils.fromWei(bli_result);
      console.log(format);
      return format;
    }
    return getBalance();
  }

  // 회원가입 시 실행
  // static async createAccount() {
  //   const web3 = this.web3;

  //   console.log("account creating start");
  //   const account = await web3.eth.personal.newAccount("ethereum"); // temp password: ethereum
  //   console.log("account: ", account);
  //   console.log("created!");

  //   localStorage.myData = JSON.stringify({
  //     address: account,
  //   });
  //   await this.unlockAccount();
  //   return account;
  // }

  // static async unlockAccount() {
  //   const web3 = this.web3;
  //   await web3.eth.personal.unlockAccount(JSON.parse(localStorage.myData).address, "ethereum", 0); // unlock inf time
  // }

  // static async getBalance() {
  //   const web3 = this.web3;
  //   return await web3.eth.getBalance(JSON.parse(localStorage.myData).address);
  // }

  // static async receiveBalance() {
  //   const web3 = this.web3;
  //   const ether = web3.utils.toWei("1");
  //   const currentBalance = await this.web3.eth.getBalance(JSON.parse(localStorage.myData).address);

  //   if (parseInt(currentBalance) < parseInt(ether)) {
  //     const rs = await web3.eth.sendTransaction({
  //       from: coinbase, // main miner. unlocked.
  //       to: JSON.parse(localStorage.myData).address,
  //       value: ether,
  //     });
  //     return true;
  //   }
  //   return false;
  // }

  // static async call(method, args) {
  //   const rs = await method(...args).call({
  //     from: JSON.parse(localStorage.myData).address,
  //   });
  //   return rs;
  // }

  // // 노드에서 계정을 관리할 경우
  // static async send(method, args) {
  //   // ether가 모자랄 경우 수급
  //   console.log("get balance: ", await this.receiveBalance());

  //   const account = JSON.parse(localStorage.myData).address;

  //   try {
  //     const rs = await method(...args).send({
  //       from: account,
  //     });
  //     return rs;
  //   } catch (err) {
  //     console.log("send error: ", err);
  //   }
  // }

  // // 로컬에서 계정을 관리할 경우 서명 후 전송
  // static async signAndSend(method, args, callback) {
  //   const web3 = this.web3;
  //   const privateKey = JSON.parse(localStorage.myData).privateKey;
  //   const account = JSON.parse(localStorage.myData).address;

  //   // 트랜잭션 데이터
  //   const txBuilder = method(...args);
  //   const encodedTx = txBuilder.encodeABI();

  //   // 기타 데이터
  //   const nonce = await web3.eth.getTransactionCount(account);
  //   const block = await web3.eth.getBlock("latest");
  //   const gasLimit = block.gasLimit;
  //   const gasPrice = (await web3.eth.getGasPrice()) * 1.5;

  //   const txObject = {
  //     nonce: web3.utils.toHex(nonce),
  //     gasLimit,
  //     gasPrice,
  //     data: encodedTx,
  //     from: JSON.parse(localStorage.myData).address,
  //     to: contractAddress,
  //   };

  //   // sign
  //   const rs = await web3.eth.accounts.signTransaction(txObject, privateKey, async (error, signedTx) => {
  //     if (error) {
  //       console.log("error!", error);
  //     } else {
  //       // send
  //       try {
  //         const _rs = await web3.eth.sendSignedTransaction(signedTx.rawTransaction);
  //         callback(_rs);
  //       } catch (err) {
  //         console.log("run error!", err);
  //       }
  //     }
  //   });
  //   console.log("rs!!", rs);
  // }
}
