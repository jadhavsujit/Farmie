import Location from "./components/Location";
import Forgotpass from "./components/Forgotpass";
import Home from "./components/Home";
import {Routes, Route} from "react-router-dom";
import Logins from "./components/Logins";
import Registers from "./components/Registers";
import Navbars from "./components/Navbars";
import MyProfile from "./components/MyProfile";
import Footer from "./components/Footers";
import Cart from "./components/Cart";
import Deletable from "./components/Deletable";
import Locations from "./Del/Location";
import CustomerDashboard from "./components/CustomerDashboard";
import LoginCustomer from "./components/LoginCustomers";
import LoginSupplier from "./components/LoginSuppliers";
import SupplierDashboard from "./components/SupplierDashboard";
import NewHomepage from "./components/NewHomepage"
import Navbar from "./components/Navbar";
import Product from "./components/Product";
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
    return (
        <div>
            <Navbars/>

            <Routes>
                <Route path="/"
                    element={
                        <div><Location/><br/><Home/></div>
                }></Route>
                <Route path="/register"
                    element={
                        <Registers></Registers>
                }></Route>
                <Route path="/forgotpassword"
                    element={<Forgotpass/>}></Route>
                <Route path="/navbars"
                    element={<Navbars/>}></Route>
                <Route path="/myprofile"
                    element={
                        <MyProfile></MyProfile>
                }></Route>
                <Route path="/cart"
                    element={<Cart/>}></Route>
                <Route path="/locations"
                    element={<Locations/>}></Route>
                <Route path="/logincustomer"
                    element={<LoginCustomer/>}></Route>
                <Route path="/loginsupplier"
                    element={<LoginSupplier/>}></Route>
                <Route path="/newhome"
                    element={
                        <NewHomepage></NewHomepage>
                }></Route>
                <Route path="/customerdashboard"
                    element={<CustomerDashboard/>}></Route>
                <Route path="/supplierdashboard"
                    element={<SupplierDashboard/>}></Route>
                <Route path="/delete"
                    element={
                        <Navbar></Navbar>
                }></Route>
                <Route path="/product/:productId"
                    element={<Product/>}/>
            </Routes>
            <ToastContainer position="top-center" />
            <br/>
                <Footer></Footer>
            </div>
    );
}

export default App;
