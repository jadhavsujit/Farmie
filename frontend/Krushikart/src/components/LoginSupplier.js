import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import { Form, Button } from "react-bootstrap";

function LoginSuppliers() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(""); // state variable for error message
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const supplier = { email, password };
    try {
      const response = await axios.post(
        "http://localhost:8080/auth/supplier/login",
        supplier
      );
      console.log(response.data);
      sessionStorage.setItem("supplier", JSON.stringify(response.data));
      setEmail("");
      setPassword("");
      navigate("/supplierdashboard");
    } catch (error) {
      console.error(error);
      setErrorMessage("Invalid email or password"); // set error message state variable
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        border: "2px solid black",
        height: "100vh",
      }}
    >
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email:</Form.Label>
          <Form.Control
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password:</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Submit
        </Button>

        {errorMessage && (
          <Form.Control.Feedback type="invalid">
            {errorMessage}
          </Form.Control.Feedback>
        )}
      </Form>
    </div>
  );
}

export default LoginSuppliers;
