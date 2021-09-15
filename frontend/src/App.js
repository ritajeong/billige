// api components layout redux pages assets font utils
import Layout from "./components/Layout/Layout";
import Main from "./components/Layout/Main";
import Location from "./components/Location";
import Lent from "./components/Lent";
import Detail from "./components/Detail";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./App.css";
function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Layout>
            <Route path="/" component={Main} exact />
            <Route path="/location" component={Location} exact />
            <Route path="/lent" component={Lent} exact />
            <Route path="/detail" component={Detail} exact />
          </Layout>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
