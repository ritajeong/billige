import React, { useState } from "react";
import { Input, Button, Form, TextArea, Grid } from "semantic-ui-react";
import "./Write.css";
import Category from "../Category/Category";

const Write = (props) => {
  const [category, setCategory] = useState("");
  const getCategory = (category) => {
    setCategory(category);
  };
  const [form, setForm] = useState({
    itemname: "",
    price: "",
    description: "",
  });
  const { itemname, price, description } = form;
  const onChange = (e) => {
    const nextForm = {
      ...form,
      [e.target.name]: e.target.value,
    };
    setForm(nextForm);
  };
  const onClick = () => {
    alert(itemname + ": " + price);
    setForm({
      itemname: "",
      price: "",
      description: "",
    });
  };
  // const showCategory = () => {
  //   setVisible(!visible);
  // };
  return (
    <div className="write">
      <h4 className="item-name">상품명</h4>
      <Input
        className="item-name"
        placeholder="상품명을 입력해주세요"
        name="itemname"
        value={itemname}
        onChange={onChange}
      />
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
          <Grid.Column className="price">
            <div className="price">
              <Input
                className="price"
                placeholder="가격"
                name="price"
                value={price}
                onChange={onChange}
              />
              <h4 className="price">원</h4>
            </div>
          </Grid.Column>
        </Grid.Row>
      </Grid>

      <Form className="item-description">
        <h4>
          <label>상품 설명</label>
        </h4>
        <TextArea
          className="item-description"
          name="description"
          value={description}
          onChange={onChange}
        />
      </Form>
      <div className="done">
        <Button className="done-button" onClick={onClick}>
          등록
        </Button>
      </div>
    </div>
  );
};

export default Write;
