import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import axios from "axios";

function RegisterCustomer() {
  const [firstName, setfname] = useState("");
  const [lastName, setlname] = useState("");
  const [email, setemail] = useState("");
  const [phoneNo, setphoneno] = useState("");
  const [password, setpassword] = useState("");
  const [isRegistered, setIsRegistered] = useState(false); // Add state variable for registration status

  const handleClick = (e) => {
    e.preventDefault();
    const customer = { firstName, lastName, email, phoneNo, password };
    console.log(customer);
    axios
      .post("http://localhost:8080/auth/customer/signup", customer, {
        headers: { "Content-Type": "application/json" },
      })
      .then(() => {
        console.log("New customer added");
        setIsRegistered(true);
      })
      .catch((error) => {
        console.error(error);
      });
  };
  return (
    <div>
      <Form>
        <Form.Group className="mb-3" controlId="formBasicFirstName">
          <Form.Label>First Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter First Name"
            required
            value={firstName}
            onChange={(e) => setfname(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter your first name.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicLastName">
          <Form.Label>Last Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Last Name"
            required
            value={lastName}
            onChange={(e) => setlname(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter your last name.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            required
            value={email}
            onChange={(e) => setemail(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter a valid email address.
          </Form.Control.Feedback>
          <Form.Text className="text-muted">
            We'll never share your email with anyone else.
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicPhoneNo">
          <Form.Label>Phone No</Form.Label>
          <Form.Control
            type="tel"
            placeholder="Enter Phone No"
            required
            value={phoneNo}
            onChange={(e) => setphoneno(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter your phone number.
          </Form.Control.Feedback>
        </Form.Group>
        {/* <Address></Address> */}

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            required
            value={password}
            onChange={(e) => setpassword(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter a password.
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group>
          Already Registered? <Link to="/logincustomer">Sign in</Link>
        </Form.Group>
        <Button variant="primary" type="submit" onClick={handleClick}>
          Submit
        </Button>
      </Form>
      {isRegistered && (
        <p style={{ color: "green" }}>Successfully registered!</p>
      )}{" "}
      {/* Conditionally render success message */}
    </div>
  );
}

export default RegisterCustomer;
