import React, { useState } from "react";
import axios from "axios";
import "../css/registers.css";
import { BsEyeSlash, BsEye } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import { Form, Button, InputGroup } from "react-bootstrap";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Login() {
  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const customer = { email, password };
    try {
      const response = await axios.post(
        "http://localhost:8080/auth/customer/login",
        customer
      );
      console.log(response.data);
      sessionStorage.setItem("customer", JSON.stringify(response.data));
      setEmail("");
      setPassword("");
      navigate("/customerdashboard");
      toast.success("Logged in Successfully");
    } catch (error) {
      console.log(customer);
      console.error(error);
      toast.error("Invalid email or password");
    }
  };

  return (
    <div class="maindiv">
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email:</Form.Label>
          <Form.Control
            type="email"
            value={email}
            placeholder="Enter Customer Email"
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password:</Form.Label>
          <InputGroup>
            <Form.Control
              type={showPassword ? "text" : "password"}
              value={password}
              placeholder="Enter Password"
              onChange={(e) => setPassword(e.target.value)}
            />
            <Button
              variant="outline-secondary"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? <BsEyeSlash /> : <BsEye />}
            </Button>
          </InputGroup>
        </Form.Group>

        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
}

export default Login;
