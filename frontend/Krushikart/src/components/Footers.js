import React from "react";
import "../css/style.css";


function Footer() {
  return (
    <div>

      <footer className="footer-06">
        <div className="container">
          <div className="row align-items-center align-items-stretch mb-5">
            <div className="col-md-12 py-4 py-md-5 aside-stretch d-flex align-items-center">
              <div className="w-100">
                <span className="subheading">Subscribe to our</span>
                <h3 className="heading-section">Newsletter</h3>
              </div>
              <form action="#" className="subscribe-form w-100">
                <div className="form-group d-flex">
                  <input
                    type="text"
                    className="form-control rounded-left"
                    placeholder="Enter email address"
                  />
                  <button type="submit" className="form-control submit">
                    <span>Submit</span>
                  </button>
                </div>
              </form>
            </div>
            {/* <div className="col-md-8 py-4 py-md-5 d-flex align-items-center pl-md-5 aside-stretch-right">
              <form action="#" className="subscribe-form w-100">
                <div className="form-group d-flex">
                  <input
                    type="text"
                    className="form-control rounded-left"
                    placeholder="Enter email address"
                  />
                  <button type="submit" className="form-control submit">
                    <span>Submit</span>
                  </button>
                </div>
              </form>
            </div> */}
          </div>
          <div className="row pt-4">
            <div className="col-md-3 col-lg-6 order-md-last">
              <div className="row justify-content-end">
                <div className="col-md-12 col-lg-9 text-md-right mb-md-0 mb-4">
                  <h2 className="footer-heading">
                    <a href="#" className="logo">
                      KrishiMart
                    </a>
                  </h2>
                  <p className="text-white">
                    ©{new Date().getFullYear()} All rights reserved | This
                    template is made with{" "}
                    <i className="ion-ios-heart" aria-hidden="true"></i> by{" "}
                    <a
                      href="https://colorlib.com"
                      target="_blank"
                      rel="noopener noreferrer"
                    >
                      KrishiMart.com
                    </a>
                  </p>
                </div>
              </div>
            </div>
            <div className="col-md-9 col-lg-6">
              <div className="row">
                <div className="col-md-4 mb-md-0 mb-4">
                  <h2 className="footer-heading">Information</h2>
                  <ul className="list-unstyled">
                    <li>
                      <a href="#" className="py-1 d-block">
                        <span className="ion-ios-checkmark-circle-outline mr-2"></span>
                        Our Company
                      </a>
                    </li>
                    <li>
                      <a href="#" className="py-1 d-block">
                        <span className="ion-ios-checkmark-circle-outline mr-2"></span>
                        Data
                      </a>
                    </li>
                    <li>
                      <a href="#" className="py-1 d-block">
                        <span className="ion-ios-checkmark-circle-outline mr-2"></span>
                        Pricing
                      </a>
                    </li>
                    <li>
                      <a href="#" className="py-1 d-block">
                        <span className="ion-ios-checkmark-circle-outline mr-2"></span>
                        Contact Us
                      </a>
                    </li>
                    <li>
                      <a href="#" className="py-1 d-block">
                        <span className="ion-ios-checkmark-circle-outline mr-2"></span>
                        Support
                      </a>
                    </li>
                  </ul>
                </div>
                <div className="col-md-4 mb-md-0 mb-4">
                  <h2 className="footer-heading">Application</h2>
                  <ul class="list-unstyled">
                    <li>
                      <a href="#" class="py-1 d-block">
                        <span class="ion-ios-checkmark-circle-outline mr-2"></span>
                        Download
                      </a>
                    </li>
                    <li>
                      <a href="#" class="py-1 d-block">
                        <span class="ion-ios-checkmark-circle-outline mr-2"></span>
                        Transport Provider
                      </a>
                    </li>
                    <li>
                      <a href="#" class="py-1 d-block">
                        <span class="ion-ios-checkmark-circle-outline mr-2"></span>
                        How to Used
                      </a>
                    </li>
                  </ul>
                </div>
                <div class="col-md-4 mb-md-0 mb-4">
                  <h2 class="footer-heading">API</h2>
                  <ul class="list-unstyled">
                    <li>
                      <a href="#" class="py-1 d-block">
                        <span class="ion-ios-checkmark-circle-outline mr-2"></span>
                        Documentation
                      </a>
                    </li>
                    <li>
                      <a href="#" class="py-1 d-block">
                        <span class="ion-ios-checkmark-circle-outline mr-2"></span>
                        Credential
                      </a>
                    </li>
                    <li>
                      <a href="#" class="py-1 d-block">
                        <span class="ion-ios-checkmark-circle-outline mr-2"></span>
                        Developer info
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default Footer;
