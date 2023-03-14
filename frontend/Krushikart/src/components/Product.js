import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

function Product() {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8080/product/${productId}`).then((response) => {
      setProduct(response.data);
    });
  }, [productId]);

  const addToCart = () => {
    // Implement logic to add product to cart
    console.log("Product added to cart");
  };

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6">
          <img className="img-fluid" src={`http://localhost:8080/product/${productId}/image`} alt={product.name} />
        </div>
        <div className="col-md-6">
          <h1>{product.name}</h1>
          <p className="lead">₹{product.price}</p>
          <p>{product.description}</p>
          <p>{product.expiryDate}</p>
          <button className="btn btn-primary btn-lg" onClick={addToCart}>Add to Cart</button>
        </div>
      </div>
    </div>
  );
}

export default Product;
    