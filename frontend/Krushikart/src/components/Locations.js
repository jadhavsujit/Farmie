import { useContext, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import { LocationContext } from './locationContext'

const Location = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [location, setLocation] = useState("");
  const [url, setUrl] = useState();
  const [isLocation, issetLocation] = useState(false);
  const { locationContext, setLocationContext } = useContext(LocationContext);

  const setlocationurl = () => {
    issetLocation(true);
    setUrl(
      "https://maps.google.com/maps?q=${location}&t=&z=13&ie=UTF8&iwloc=&output=embed"
    );
    setLocationContext(location);
  };

  const toggleDrawer = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div>
      <button
        onClick={toggleDrawer}
        className="location-button"
        style={{
          maxWidth: "120px",
          minWidth: "120px",
          fontSize: "1rem",
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          padding: "0.5rem",
          border: "none",
          background: "transparent",
          cursor: "pointer",
        }}
      >
        <FontAwesomeIcon icon={faMapMarkerAlt} />
        {locationContext === "" ? "Location" : locationContext.slice(0, 12)}
      </button>

      {isOpen && (
        <div className="location-drawer">
          <div className="location-drawer-content">
            <div className="location-drawer-header">
              <h3>Location</h3>
              <button onClick={toggleDrawer}>X</button>
            </div>
            <div className="location-drawer-body">
              <div className="location-map">
                <iframe
                  title="map"
                  width="400px"
                  id="gmap_canvas"
                  src={
                    isLocation
                      ? url
                      : "https://maps.google.com/maps?q=dehli&t=&z=13&ie=UTF8&iwloc=&output=embed"
                  }
                ></iframe>
              </div>

              <div className="location-form">
                <label htmlFor="location-input">Set Location</label>
                <div className="location-input-group">
                  <input
                    id="location-input"
                    type="text"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                    placeholder="Enter location"
                  />
                  <button onClick={() => setlocationurl()}>Set</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Location;
