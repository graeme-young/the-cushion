import React from "react";
import { Navbar } from "../components/Navbar";

interface cartProps {}

const cart: React.FC<cartProps> = ({}) => {
  return (
    <>
      <Navbar />
      <div>cart</div>
    </>
  );
};

export default cart;
