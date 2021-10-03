import React, { useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";
import { Button, Modal, Input } from "semantic-ui-react"
import profile from "../../assets/image/user.png";
import star from "../../assets/icons/star.png";
import arrow from "../../assets/icons/arrow-right.png";
import product from "../../assets/icons/product.png";
import productlist from "../../assets/icons/productlist.png";
import fingerprint from "../../assets/icons/fingerprint.png"
import "./MyPage.css";
import axios from 'axios';
const MyPage = () => {
  const [wallet, setWallet] = useState(true);
  const [open, setOpen] = useState(false)
  const [user, setUser] = useState({})
  const btn = useRef();

  useEffect(() => {
    const token = JSON.parse(window.localStorage.getItem('token'));
    console.log("Bearer " + token);
    axios
      .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/user/mypage`, {
        headers: {
          Authentication:
            "Bearer " + token,
        },

      })
      .then((response) => {
        setUser(response.data)
        console.log(response);
      })
      .catch((error) => {
        console.log(error)
      })
  }, [])
  const createWallet = () => {
    alert("지갑주소는 쏼라쏼라")
    setWallet(!wallet)
  }
  return (
    <div className="mypage">
      <div className="mypage-profile">
        <img src={profile} alt="product" className="mypage-user-icon" />
        <div className="mypage-profile-desc">
          <h4>{user.userName} 님 안녕하세요!</h4>
          <span>{user.userEmail}</span>
        </div>
      </div>
      <Link to="/useredit">
        <button className="mypage-useredit">프로필 수정</button>
      </Link>
      <div className="mypage-wallet">

        {!user.existWallet ?
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
                      <span>보유BLI</span>
                      <Input />
                      BLI
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
            <div>{user.userBli} BLI</div>
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
