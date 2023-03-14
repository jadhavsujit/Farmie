import React, { useState } from "react";
import { Form, Button, InputGroup } from "react-bootstrap";

function Address() {
  // state variables for country and state
  const [country, setCountry] = useState("");
  const [state, setState] = useState("");
  const [cities, setCities] = useState([]);

  // function to handle country change
  const handleCountryChange = (event) => {
    const selectedCountry = event.target.value;
    setCountry(selectedCountry);
    const statesForCountry = getStatesForCountry(selectedCountry);
    setState(statesForCountry[0]);
    setCities(getCitiesForState(statesForCountry[0]));
  };

  // function to handle state change
  const handleStateChange = (event) => {
    const selectedState = event.target.value;
    setCities(getCitiesForState(selectedState));
    setState(selectedState);
  };

  // function to get the cities for a given state
  const getCitiesForState = (state) => {
    // state-city mapping
    const citiesByState = {
      Maharashtra: [
        "Mumbai",
        "Pune",
        "Dhule",
        "Latur",
        "Sambhajinagar",
        // add more cities as needed
      ],
      // add more states and their respective cities as needed
    };
    return citiesByState[state] || [];
  };

  // function to get the states for a given country
  const getStatesForCountry = (country) => {
    // country-state mapping
    const statesByCountry = {
      India: [
        "Andhra Pradesh",
        "Bihar",
        "Jharkhand",
        "Karnataka",
        "Madhya Pradesh",
        "Maharashtra",
      ],
      USA: ["Alaska", "Arizona", "California"],
      // add more countries and their respective states as needed
    };
    return statesByCountry[country] || [];
  };

  return (
    <div style={{display: "flex", justifyContent: "center"}}>
  <div style={{width: "50%" , margin:""}}>
    <Form.Group className="mb-3" controlId="formBasicAddress">
      <Form.Label>Address</Form.Label>
      <Form.Control type="text" placeholder="Enter your address" required />
      <Form.Control.Feedback type="invalid">
        Please enter your address.
      </Form.Control.Feedback>
    </Form.Group>
    <Form.Group className="mb-3" controlId="formBasicAddress">
      <Form.Label>Landmark</Form.Label>
      <Form.Control type="text" placeholder="Enter your landmark" required />
      <Form.Control.Feedback type="invalid">
        Please enter your landmark.
      </Form.Control.Feedback>
    </Form.Group>
    <Form.Group className="mb-3" controlId="formBasicCountry">
      <Form.Label>Country</Form.Label>
      <Form.Control
        as="select"
        value={country}
        onChange={handleCountryChange}
      >
        <option>Select Country</option>
        <option>India</option>
        <option>USA</option>
      </Form.Control>
    </Form.Group>

    <Form.Group className="mb-3" controlId="formBasicState">
      <Form.Label>State</Form.Label>
      <Form.Control as="select" value={state} onChange={handleStateChange}>
        {getStatesForCountry(country).map((state) => (
          <option key={state} value={state}>
            {state}
          </option>
        ))}
      </Form.Control>
    </Form.Group>
    <Form.Group className="mb-3" controlId="formBasicCity">
      <Form.Label>City</Form.Label>
      <Form.Control
        as="select"
        value={cities}
        onChange={(event) => setCities([event.target.value])}
      >
        {cities.map((city) => (
          <option key={city} value={city}>
            {city}
          </option>
        ))}
      </Form.Control>
    </Form.Group>
    <Form.Group className="mb-3" controlId="formBasicPincode">
      <Form.Label>Pincode</Form.Label>
      <Form.Control type="text" placeholder="Enter pincode" />
    </Form.Group>
    <Button variant="primary" type="submit">
      Submit
    </Button>
  </div>
</div>

  );
}

export default Address;
