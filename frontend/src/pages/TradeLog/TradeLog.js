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
    axios
      .get(`http://localhost:8080/api/item/list?page=1`, {
        headers: {
          Authentication:

            "Bearer " + `eyJyZWdEYXRlIjoxNjMyNzMwMDkzMzU2LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjgsInVzZXJTaWd1bmd1Q29kZSI6MTExMTAsInVzZXJOaWNrbmFtZSI6Inp6IiwidXNlckVtYWlsIjoienpAenouY20iLCJleHAiOjE2MzUzMjIwOTN9.26q-erHmV9dLFLoQAxlHzFC_J9ZJFeU44lmsrSBEPb4`,
        }
      })
      .then((response) => {
        console.log(response)
      })
      .catch((error) => {
        console.log(error);
      })
  }

  const test3 = () => {
    axios
      .post(`http://localhost:8080/api/user/login`, {
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
    const result = mypage()
      .then((response) => {
        console.log(response)
      })
    console.log(result)
  }
  const test5 = () => {
    axios
      .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/user/mypage`, {
        headers: {
          Authentication:
            "Bearer " + `eyJyZWdEYXRlIjoxNjMyNzMwMDkzMzU2LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjgsInVzZXJTaWd1bmd1Q29kZSI6MTExMTAsInVzZXJOaWNrbmFtZSI6Inp6IiwidXNlckVtYWlsIjoienpAenouY20iLCJleHAiOjE2MzUzMjIwOTN9.26q-erHmV9dLFLoQAxlHzFC_J9ZJFeU44lmsrSBEPb4`,
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
    <button onClick={test5} >테스트5</button>
  </div>;
};

export default TradeLog;
