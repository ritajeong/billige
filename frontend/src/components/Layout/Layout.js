import React from "react";
import Footer from "./Footer";
import Header from "./Header";
import NavBottom from "./NavBottom";
import Side from "./Side";

import "./Layout.css";

const Layout = ({ children }) => {
  return (
    <div className="layout-wrap">
      <div className="layout">
        <Header />
        <section>
          <main>{children}</main>
          <Footer />
        </section>
        <NavBottom />
      </div>
      <Side />
    </div>
  );
};

export default Layout;
