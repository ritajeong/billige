import React, { useState } from "react";
import { Input } from "semantic-ui-react";
import { useHistory } from "react-router";
const SearchInput = () => {
  const history = useHistory();
  const [inputText, setInputText] = useState("");
  const onChange = (e) => {
    setInputText(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    history.push(`/searchitem?text=${inputText}`);
  }; 
  return <>
    <form className="inputForm" onSubmit={handleSubmit}>
      <Input
        className="main-search"
        icon="search"
        iconPosition="left"
        onChange={onChange}
        value={inputText}
      />
      <br />
    </form>
  </>;
};

export default SearchInput;
