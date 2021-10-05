
import React, { useState } from "react";
import axios from 'axios';
import { useHistory } from 'react-router';
import { Input, Button, Form, TextArea, Grid } from "semantic-ui-react";
import "./Write.css";
import Category from "../Category/Category";

const Write = () => {
  const history = useHistory();
  const [itemname, setItemName] = useState()
  const [itembli, setItemBli] = useState()
  const [description, setDescription] = useState()
  const [category, setCategory] = useState('test');
  const getCategory = (category) => {
    setCategory(category);
  };
  const writeProduct = () => {
    const token = JSON.parse(window.localStorage.getItem('token'))
    axios
      .post(`${process.env.REACT_APP_SERVER_BASE_URL}/api/item`, {
        category,
        description,
        itemSigunguCode: 11110,
        itemname,
        position: "서울특별시 강남구",
        price: itembli,
      }, {
        headers: {
          Authentication:
            "Bearer " + token
        },

      })
      .then((response) => {
        console.log(response);
        history.push('/');
      })
      .catch((error) => {
        console.log(error)
      })
  }

  const onChangeItemName = (e) => {
    setItemName(e.target.value);
  }

  const onChangeItemBli = (e) => {
    setItemBli(e.target.value);
  }

  const onChangeDesc = (e) => {
    setDescription(e.target.value)
  }
  return (
    <div className="write">
      <h4 className="item-name">상품명</h4>

      <Input placeholder="상품명을 입력해주세요" className="item-name" onChange={onChangeItemName} />

      <h4 className="item-heading-upload">이미지 업로드</h4>
      <div className="upload">
        <Button className="upload-button">
          <h2>+</h2>
        </Button>
      </div>
      {/* <p>카테고리 : {category}</p> */}
      <Category category={category} getCategory={getCategory} />
      <Grid divided="vertically">
        <Grid.Row columns={2}>
          <Grid.Column>
            <h4 className="item-heading">대여 가격(1일)</h4>
          </Grid.Column>
          <Grid.Column>
            <div className="item-bli">
          <Input placeholder="가격을 입력해주세요" className="write-bli" onChange={onChangeItemBli} />
            BLI
            </div>
          </Grid.Column>
        </Grid.Row>
      </Grid>

      <Form className="item-description">
        <h4>
          <label>상품 설명</label>
        </h4>
        <TextArea onChange={onChangeDesc} />
      </Form>
      <div className="done">
        <Button className="done-button" onClick={writeProduct}>등록</Button>
      </div>
    </div>
  );
};

export default Write;
