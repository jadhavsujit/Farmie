import React, { useState } from "react";

function CountryStateDropdown() {
  // state variables for country and state
  const [country, setCountry] = useState("");
  const [states, setStates] = useState([]);

  // function to handle country change
  const handleCountryChange = (event) => {
    const selectedCountry = event.target.value;
    setCountry(selectedCountry);
    setStates(getStatesForCountry(selectedCountry));
  };

  // function to get the states for a given country
  const getStatesForCountry = (country) => {
    // country-state mapping
    const statesByCountry = {
      India: ["Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"],
      USA: ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"]
      // add more countries and their respective states as needed
    };
    return statesByCountry[country] || [];
  };

  // render function
  return (
    <div>
      <label>
        Country:
        <select value={country} onChange={handleCountryChange}>
          <option value="">Select a country</option>
          <option value="India">India</option>
          <option value="USA">USA</option>
          {/* add more countries as needed */}
        </select>
      </label>
      <label>
        State:
        <select value={states} onChange={(event) => setStates(event.target.value)}>
          {states.map((state) => (
            <option key={state} value={state}>
              {state}
            </option>
          ))}
        </select>
      </label>
    </div>
  );
}

export default CountryStateDropdown;
