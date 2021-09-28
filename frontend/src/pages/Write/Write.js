import React from "react";
import { Input, Button, Form, TextArea, Grid } from "semantic-ui-react";
import "./Write.css";

const Write = () => {
  return (
    <div className="write">
      <h4 className="item-name">상품명</h4>
      <Input placeholder="상품명을 입력해주세요" className="item-name" />
      <h4 className="item-heading-upload">이미지 업로드</h4>
      <div className="upload">
        <Button className="upload-button"><h2>+</h2></Button>
      </div>
      <Grid divided="vertically">
        <Grid.Row columns={2}>
          <Grid.Column>
            <h4 className="item-heading">카테고리 설정</h4>
          </Grid.Column>
          <Grid.Column>
            <div className="category-choice">
              <p>음악</p>
              <Button className="choice-button">선택</Button>
            </div>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <Grid divided="vertically">
        <Grid.Row columns={2}>
          <Grid.Column>
            <h4 className="item-heading">대여 가격(1일)</h4>
          </Grid.Column>
          <Grid.Column>
            <h4 className="price">200,000원</h4>
          </Grid.Column>
        </Grid.Row>
      </Grid>

      <Form className="item-description">
        <h4>
          <label>상품 설명</label>
        </h4>
        <TextArea />
      </Form>
      <div className="done">
        <Button className="done-button">등록</Button>
      </div>
    </div>
  );
};

export default Write;
