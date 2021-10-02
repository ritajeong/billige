import Web3 from "web3";

let getWeb3 = new Promise((resolve, reject) => {
    // Wait for loading completion to avoid race conditions with web3 injection timing.
    window.addEventListener("load", async () => {

      if (window.ethereum) {
        // const web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/v3/26261cf4c7af4304b492cefe8505e390'));
        const web3 = new Web3(Web3.givenProvider || 'https://ropsten.infura.io/v3/26261cf4c7af4304b492cefe8505e390');
        // const web3 = new Web3(window.ethereum);
        try {
          // Request account access if needed
          await window.ethereum.enable();
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
        console.log(3)
        const provider = new Web3.providers.HttpProvider(
          "http://127.0.0.1:8545"
        );
        const web3 = new Web3(provider);
        console.log("No web3 instance injected, using Local web3.");
        resolve(web3);
      }
    });
  });

export default getWeb3;
