import React, { useState, useCallback } from "react";
import Category from "../Category/Category";
import { Form, Radio, Input } from "semantic-ui-react";
import "./Filter.css";
const Filter = () => {
  const [inputStatus, setInputStatus] = useState("");
  const handleClickRadioButton = useCallback(
    (radioBtnName) => {
      setInputStatus(radioBtnName);
    },
    [inputStatus]
  );
  const handleSubmit = (e) => {
    e.preventDefault();
    alert("확인");
    setInputText("");
  };

  const [inputText, setInputText] = useState("");
  const onChange = (e) => {
    setInputText(e.target.value);
  };

  return (
    <>
      <div className="filter">
        <div className="filter-category">
          <Category />
        </div>
        <hr />
        <h4>정렬</h4>
        <Form onSubmit={handleSubmit} className="radio-group">
          {/* <Form.Field>
          Selected value: <b>{inputStatus}</b>
        </Form.Field>
         */}
          <Form.Field>
            <Radio
              id="radio1"
              label="대여순"
              name="radioGroup"
              checked={inputStatus === "대여순"}
              onClick={() => handleClickRadioButton("대여순")}
            />
          </Form.Field>
          <Form.Field>
            <Radio
              id="radio2"
              label="북마크순"
              name="radioGroup"
              checked={inputStatus === "북마크순"}
              onClick={() => handleClickRadioButton("북마크순")}
            />
          </Form.Field>
          <Form.Field>
            <Radio
              id="radio3"
              label="최신순"
              name="radioGroup"
              checked={inputStatus === "최신순"}
              onClick={() => handleClickRadioButton("최신순")}
            />
          </Form.Field>
          <hr />
          {/* <h4>가격 범위</h4>
        <Form.Field>
          <Input
            className="price-range"
            icon="search"
            iconPosition="left"
            onChange={onChange}
            value={inputText}
          />
        </Form.Field>
        <Form.Field>
          <Input
            className="price-range"
            icon="search"
            iconPosition="left"
            onChange={onChange}
            value={inputText}
          />
        </Form.Field> */}
        </Form>
        <hr></hr>
        <form className="inputForm" onSubmit={handleSubmit}>
          <h4>가격범위</h4>
          <Input
            className="input-price"
            icon="search"
            iconPosition="left"
            placeholder="0"
            onChange={onChange}
            value={inputText}
          />
          ~
          <Input
            className="input-price"
            icon="search"
            iconPosition="left"
            placeholder="제한없음"
            onChange={onChange}
            value={inputText}
          />
          <br />
        </form>
        <hr />
      </div>
    </>
  );
};

export default Filter;
