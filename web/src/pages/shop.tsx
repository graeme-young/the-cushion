import React from "react";
import { Navbar } from "../components/Navbar";

interface shopProps {}

const shop: React.FC<shopProps> = ({}) => {
  return (
    <>
      <Navbar />
      <div>Shop</div>
    </>
  );
};

export default shop;
