import Form from "react-bootstrap/Form";
import "../css/registers.css";
import { useState } from "react";
import RegisterCustomer from "./RegisterCustomer";
import RegisterSupplier from "./RegisterSupplier";

function Registers() {
  const [isCustomer, setIsCustomer] = useState(true);

  const handleChange = (event) => {
    setIsCustomer(event.target.id === "customer");
  };

  return (
    <div class="maindiv">
      <Form>
        <Form.Group className="mb-3">
          <Form.Label>Register as:</Form.Label>
          <div key="inline-radio" className="mb-3">
            <Form.Check
              inline
              label="Customer"
              type="radio"
              id="customer"
              name="registerAs"
              defaultChecked
              onChange={handleChange}
              checked={isCustomer}
            />
            <Form.Check
              inline
              label="Farmer"
              type="radio"
              id="Supplier"
              name="registerAs"
              onChange={handleChange}
              checked={!isCustomer}
            />
          </div>
        </Form.Group>
        <div style={{ display: isCustomer ? "none" : "block" }}>
          <RegisterSupplier></RegisterSupplier>
        </div>
        <div style={{ display: isCustomer ? "block" : "none" }}>
          <RegisterCustomer></RegisterCustomer>
        </div>
      </Form>
    </div>
  );
}

export default Registers;
