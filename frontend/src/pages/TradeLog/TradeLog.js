import React from "react";
import axiosInstance from '../../api/axios';
import axios from "axios"
import { mypage, signIn } from '../../api/user';
const TradeLog = () => {

  const test = () => {
    const result = signIn(`test@test.com`, `test`)
      .then((response) => {
        console.log(response)
      })
    console.log(result)
  }

  const test2 = () => {
    const token = JSON.parse(window.localStorage.getItem('token'))
    axios
      .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/item/list`,
      // headers: {
      //   Authentication:
      //     "Bearer " + `eyJyZWdEYXRlIjoxNjMyNzMwMDkzMzU2LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjgsInVzZXJTaWd1bmd1Q29kZSI6MTExMTAsInVzZXJOaWNrbmFtZSI6Inp6IiwidXNlckVtYWlsIjoienpAenouY20iLCJleHAiOjE2MzUzMjIwOTN9.26q-erHmV9dLFLoQAxlHzFC_J9ZJFeU44lmsrSBEPb4`,
      // }
    )
      .then((response) => {
        console.log(response)
      })
      .catch((error) => {
        console.log(error);
      })
  }

  const test3 = () => {

    axios
      .post(`${process.env.REACT_APP_SERVER_BASE_URL}/api/user/login`, {
        userEmail: 'test@test.com',
        userPassword: 'test'
      })
      .then((response) => {
        console.log(response)
      })
      .catch((error) => {
        console.log(error)
      })
  }

  const test4 = () => {
    const token = JSON.parse(window.localStorage.getItem('token'))
    axios
      .post(`${process.env.REACT_APP_SERVER_BASE_URL}/api/item/`, {

        category: "test",
        description: "test",
        itemSigunguCode: '11110',
        itemname: "test",
        position: "서울특별시 강남구",
        price: 0,
        uid: 0

      }, {
        headers: {
          Authentication:
            "Bearer " + token
        },

      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error)
      })
  }
  const test5 = () => {
    const token = JSON.parse(window.localStorage.getItem('token'))
    axios
      .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/item`, {
        headers: {
          Authentication:
            "Bearer " + token
        },

      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error)
      })
  }


  return <div >거래내역
    <button onClick={test} >테스트</button>
    <button onClick={test2} >테스트2</button>
    <button onClick={test3} >테스트3</button>
    <button onClick={test4} >테스트4</button>
    <button onClick={test5} >아이템 리스트 불러오기</button>
  </div>;
};

export default TradeLog;
