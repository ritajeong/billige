import React, { useState, useRef } from "react";
import { Link } from "react-router-dom";
import { Button, Modal, Input } from "semantic-ui-react"
import profile from "../../assets/image/user.png";
import star from "../../assets/icons/star.png";
import arrow from "../../assets/icons/arrow-right.png";
import product from "../../assets/icons/product.png";
import productlist from "../../assets/icons/productlist.png";
import fingerprint from "../../assets/icons/fingerprint.png"
import "./MyPage.css";
const MyPage = () => {
  const [wallet, setWallet] = useState(true);
  const [open, setOpen] = React.useState(false)
  const btn = useRef();
  const user = {
    name: "SSAFY",
    email: "ssafy@ssafy.com",
    profile: profile,
    money: "4,000",
    wallet: '',
    tradelog: [
      {
        userName: "거래한사람",
        date: "2021-09-24 16:00:00",
        pName: "a",
        pPrice: "2,000",
      },
      {
        userName: "거래한사람2",
        date: "2021-09-25 16:00:00",
        pName: "b",
        pPrice: "3,000",
      },
    ],
    wishlist: [
      {
        pName: "a",
        pPrice: "2,000",
      },
      {
        pName: "b",
        pPrice: "3,000",
      },
    ],
  };

  const createWallet = () => {
    alert("지갑주소는 쏼라쏼라")
    setWallet(!wallet)
  }
  return (
    <div className="mypage">
      <div className="mypage-profile">
        <img src={profile} alt="product" className="mypage-user-icon" />
        <div className="mypage-profile-desc">
          <h4>{user.name} 님 안녕하세요!</h4>
          <span>{user.email}</span>
        </div>
        {/* <Link to="/useredit">
          <img src={arrow} width="20px" alt="arrow" />
        </Link> */}
      </div>
      <Link to="/useredit">
        <button className="mypage-useredit">프로필 수정</button>
      </Link>
      <div className="mypage-wallet">

        {wallet ?
          <div className="mypage-wallet-create" onClick={createWallet}>
            <img src={fingerprint} alt="fingerprint" width="60px" />
            지갑 생성하기
          </div>
          : <div className="mypage-wallet-info">
            <div>지갑 잔액
              <Modal
                open={open}
                onClose={() => setOpen(false)}
                onOpen={() => setOpen(true)}
                trigger={<button ref={btn}>충전</button>}
              >
                <Modal.Header>충전</Modal.Header>
                <Modal.Content image>
                  <Modal.Description>
                    <div className="charge-modal-input">
                      <span>지갑주소</span>
                      <Input />
                    </div>
                    <div className="charge-modal-input">
                      <span>보유eth</span>
                      <Input />
                      ETH
                    </div>
                  </Modal.Description>
                </Modal.Content>
                <Modal.Actions>
                  <Button primary onClick={() => setOpen(false)}>
                    충전하기
                  </Button>
                </Modal.Actions>
              </Modal>
            </div>
            <div>17,000원</div>
            <div> 잔액이 부족하면 대여서비스를 이용할 수 없습니다!</div>

          </div>
        }

      </div>
      <div className="mypage-user-info">
        <Link to="/myproduct">
          <div className="user-info">
            <img src={product} width="30px" alt="eth" />
            <p>
              등록한 제품
            </p>
          </div>
        </Link>

        <Link to="/tradelog">
          <div className="user-info">
            <img src={productlist} width="30px" alt="eth" />
            <p>
              대여내역
            </p>
          </div>
        </Link>

        <Link to="/wish">
          <div className="user-info">
            <img src={star} width="30px" alt="eth" />
            <p>
              찜
            </p>
          </div>
        </Link>
      </div>

      <div className="division-bar"></div>

      <div className="mypage-options">
        <Link to="/signin">
          <div className="mypage-option">
            <p>로그인 (임시)</p> <img src={arrow} width="20px" alt="arrow" />
          </div>
        </Link>

        <Link to="/tradelog">
          <div className="mypage-option">
            <p>거래내역</p>
            <img src={arrow} width="20px" alt="arrow" />
          </div>
        </Link>

        <Link to="/">
          <div className="mypage-option">
            <p>로그아웃</p> <img src={arrow} width="20px" alt="arrow" />
          </div>
        </Link>
      </div>


    </div >
  );
};

export default MyPage;
