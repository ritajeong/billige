pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract BliToken is ERC20{
  uint public INITIAL_SUPPLY = 120000*10**18;

  constructor() public ERC20("Bli Token","BLI"){
    _mint(msg.sender, INITIAL_SUPPLY);
  }
}
