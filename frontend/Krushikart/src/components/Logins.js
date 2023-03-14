import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import { useState } from "react";
import { BsEyeSlash, BsEye } from "react-icons/bs";
import "../css/registers.css";
import { Link } from "react-router-dom";
import LoginCustomer from "./LoginCustomer";
import LoginSupplier from "./LoginSupplier";
import VaibhavLogin from "./LoginCustomers";
import axios from "axios";

function Logins() {
  const [loginType, setLoginType] = useState("customer");

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post(`http://localhost:8080/auth/${loginType}/login`, {
        email,
        password,
      })
      .then((response) => {
        console.log(response.data);
        setError(null);
      })
      .catch((error) => {
        console.error(error);
        setError("Invalid email or password");
      });
  };

  return (
    <div class="maindiv">
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Login as:</Form.Label>
          <div>
            <Form.Check
              inline
              label="Customer"
              type="radio"
              name="loginType"
              value="customer"
              checked={loginType === "customer"}
              onChange={(event) => setLoginType(event.target.value)}
            />
            <Form.Check
              inline
              label="Farmer"
              type="radio"
              name="loginType"
              value="farmer"
              checked={loginType === "farmer"}
              onChange={(event) => setLoginType(event.target.value)}
            />
            <Form.Check
              inline
              label="Admin"
              type="radio"
              name="loginType"
              value="admin"
              checked={loginType === "admin"}
              onChange={(event) => setLoginType(event.target.value)}
              disabled
            />
          </div>
        </Form.Group>
        {loginType === "customer" ? (
          <div>
            <form onSubmit={handleSubmit}>
              <label>
                Email:
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </label>
              <label>
                Password:
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </label>
              {error && <p>{error}</p>}
              <button type="submit">Submit</button>
            </form>
          </div>
        ) : null}
        {loginType === "farmer" ? <LoginSupplier /> : null}
      </Form>
    </div>
  );
}

export default Logins;
