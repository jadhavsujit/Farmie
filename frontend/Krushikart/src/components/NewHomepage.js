import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import "../css/navbars.css";
import Cart from "./Cart";
import "@fortawesome/fontawesome-free/css/all.min.css";
import Menu from "./Menu";
import logo from "../images/logo.jpg";
import Location from "./Location";
import Footer from "./Footers";
import Home from "./Home"
function Navbars() {
  var customerobj = JSON.parse(sessionStorage.getItem("customer"));
  console.log(customerobj);

  return (
    <div>
      <Navbar style={{ backgroundColor: "#d8e2dc" }} expand="lg">
        <Container>
          <Navbar.Brand href="/">
            <img src={logo} style={{ width: "200px", height: "auto" }} />
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <NavDropdown title="Vegetables" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.3">
                  <i class="fa-solid fa-carrot"></i>
                  Carrots <br />
                  <sub>गाजर</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.1">
                  <i class="fa-solid fa-onion"></i>
                  Onion <br />
                  <sub>प्याज </sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">
                  <i class="fa-solid fa-potato"></i>
                  Potatoes
                  <br />
                  <sub>आलू</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  <i class="fa-solid fa-tomato"></i>
                  Tomatoes
                  <br />
                  <sub>टमाटर</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  <i class="fa-solid fa-leafy-green"></i>
                  Spinach, Kale & Greens
                  <br />
                  <sub>पालक,गोभी & हरी सब्ज़ियां</sub>
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">
                  Organic Vegetables
                </NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Fruits" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">
                  <i class="fa-solid fa-apple-whole"></i>
                  Apples
                  <br />
                  <sub>सेब</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">
                  Bananas
                  <br />
                  <sub>केला</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Grapes
                  <br />
                  <sub>अंगूर</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  <i class="fa-sharp fa-solid fa-melon"></i>
                  Melons
                  <br />
                  <sub>ख़रबूज़े</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Dried Fruit & Nuts
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">
                  Organic Fruits
                </NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Sereals" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">
                  Rice
                  <br />
                  <sub>चावल</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">
                  Wheat
                  <br />
                  <sub>गेहूँ</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Sorghum
                  <br />
                  <sub>ज्वार</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Millet
                  <br />
                  <sub>बाजरा</sub>
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Soyabeans
                  <br />
                  <sub>सोयाबीन</sub>
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">
                  Organic Sereals
                </NavDropdown.Item>
              </NavDropdown>
              <NavDropdown title="Dairy Products" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">Cow-Milk</NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">
                  Buffalow-milk
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">Ghee</NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">
                  Curd & tak
                </NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">
                  <i class="fa-solid fa-bolt"></i>
                  Order Instantly
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>
            <Nav className="me-auto">
              <Form className="d-flex">
                <Form.Control
                  type="search"
                  placeholder="Search"
                  className="me-2"
                  aria-label="Search"
                />
                <Button variant="primary">
                  <FontAwesomeIcon icon={faSearch} /> Search
                </Button>
              </Form>
            </Nav>

            <Nav className="me-auto">
              <i class="fa-solid fa-user"></i>
              <div>Signed as: {customerobj.firstName}</div>
            </Nav>
            <Nav className="me-auto">
              <i class="fa-solid fa-cart-shopping"></i>
            </Nav>
            <Nav className="me-auto">
              <Menu></Menu>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <div>
        <Location />
        <br />
        <Home />
        <Footer></Footer>
      </div>
    </div>
  );
}

export default Navbars;
