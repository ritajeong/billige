const loginUser = (user) => {
  console.log('이것이 data ', user)
  window.localStorage.setItem("user", JSON.stringify(user));
  return {
    type: "LOGIN",
    user
  };
};

const logoutUser = () => {
  return {
    type: "LOGOUT",
  };
};

const userActions = { loginUser, logoutUser };
export default userActions;
