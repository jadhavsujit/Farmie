import { useState } from "react";
import { Dropdown } from "react-bootstrap";
import { FaBars } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

function Menu() {
  const [showMenu, setShowMenu] = useState(false);
  const navigate = useNavigate();
  const handleToggleMenu = () => {
    setShowMenu(!showMenu);
  };

  const handleLogout = () => {
    // Clear all items in session storage
    sessionStorage.clear();

    navigate("/");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <button
          className="navbar-toggler"
          type="button"
          onClick={handleToggleMenu}
        >
          <FaBars />
        </button>
        <div className={`collapse navbar-collapse ${showMenu && "show"}`}>
          <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
            <Dropdown>
              <Dropdown.Toggle variant="light" id="dropdown-menu">
                Menu
              </Dropdown.Toggle>
              <Dropdown.Menu>
                <Dropdown.Item href="/register">Register Me</Dropdown.Item>
                <Dropdown.Item href='/logincustomer'>Login As Customer</Dropdown.Item>
                <Dropdown.Item href='/loginsupplier'>login As Supplier</Dropdown.Item>
                <Dropdown.Item href='/aboutus'>About Us</Dropdown.Item>
                <hr></hr>
                <Dropdown.Item href='/myprofile'>My Profile</Dropdown.Item>
                <Dropdown.Item onClick={handleLogout}><i class="fa-solid fa-right-to-bracket"></i> Logout</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Menu;
