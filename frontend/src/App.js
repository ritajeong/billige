// api components layout redux pages assets font utils
import Layout from "./components/Layout/Layout";
import Main from "./components/Layout/Main";
import { BrowserRouter as Router, Switch } from "react-router-dom";
import Rent from "./pages/Rent/Rent";
import Detail from "./pages/Detail/Detail";
import Location from "./pages/Location/Location";
import PrivateRouter from "./router/PrivateRouter";
import PublicRouter from "./router/PublicRouter";

import "./App.css";
import MyPage from "./pages/MyPage/MyPage";
import Wish from "./pages/Wish/Wish";
import Write from "./pages/Write/Write";
import Chat from "./pages/Chat/Chat";
function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Layout>
            <PublicRouter path="/" component={Main} exact />
            <PublicRouter path="/location" component={Location} exact />
            <PublicRouter path="/detail" component={Detail} exact />

            <PrivateRouter path="/rent" component={Rent} exact />
            <PrivateRouter path="/mypage" component={MyPage} exact />
            <PrivateRouter path="/wish" component={Wish} exact />
            <PrivateRouter path="/write" component={Write} exact />
            <PrivateRouter path="/chat" component={Chat} exact />
          </Layout>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
