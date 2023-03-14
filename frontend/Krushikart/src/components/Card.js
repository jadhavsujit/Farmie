import React from "react";
import { Link } from "react-router-dom";

const Card = ({ product }) => {
  const { id, name, price } = product;

  return (
    <div className="card">
      <img className="card-img-top" src={`http://localhost:8080/product/${id}/image`} alt={name} style={{ width: "100%", height: "200px", objectFit: "cover" }} />
      <div className="card-body">
        <h5 className="card-title">{name}</h5>
        <p className="card-text">Price: ₹{price}</p>
        <Link to={`/product/${id}`} className="btn btn-primary">
          View Details
        </Link>
      </div>
    </div>
  );
};

export default Card;
