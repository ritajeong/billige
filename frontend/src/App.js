// api components layout redux pages assets font utils
import Layout from "./components/Layout/Layout";
import Main from "./components/Layout/Main";
import Location from "./pages/Location";
import Lent from "./pages/Lent";
import Detail from "./pages/Detail";
import { BrowserRouter as Router, Switch } from "react-router-dom";
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

            <PrivateRouter path="/lent" component={Lent} exact />
          </Layout>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
