import Layout from "./components/Layout/Layout";
import Main from "./components/Layout/Main";
import Location from "./components/Location";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./App.css";
function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Layout>
            <Route path="/location" component={Location} exact />
            <Route path="/" component={Main} exact />
          </Layout>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
