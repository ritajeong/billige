// api components layout redux pages assets font utils
import Layout from "./components/Layout/Layout";
import Main from "./components/Layout/Main";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Rent from "./pages/Rent";
import Detail from "./pages/Detail";
import Location from "./pages/Location";
import PrivateRouter from "./router/PrivateRouter";
import PublicRouter from "./router/PublicRouter";

import "./App.css";
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
          </Layout>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
