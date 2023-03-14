import React, { useState, useEffect } from "react";
import axios from "axios";
import Card from "./Card";
import "../css/card.css";

const Home = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    let source = axios.CancelToken.source();
  
    axios
      .get("http://localhost:8080/product", { cancelToken: source.token })
      .then((response) => setProducts(response.data))
      .catch((error) => console.log(error));
  
    return () => {
      source.cancel();
    };
  }, []);

  return (
    <div className="container-fluid">
      <div className="row">
        {products.map((product) => (
          <div key={product.id} className="col-md-3 mb-4">
            <Card product={product} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
