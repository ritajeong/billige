// api components layout redux pages assets font utils
import Layout from "./components/Layout/Layout";
import Main from "./components/Layout/Main";
import Location from "./components/Location";
import Rent from "./pages/Rent";
import Detail from "./pages/Detail";
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
            <Route path="/rent" component={Rent} exact />
            <Route path="/detail" component={Detail} exact />
          </Layout>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
