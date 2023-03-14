import React from "react";
// import '../css/forgotpass.css'

function Forgotpass() {
  return (
    <div class="maindiv">
      <div class="row">
        <h1>Forgot Password</h1>
        <h6 class="information-text">
          Enter your registered email to reset your password.
        </h6>
        <div class="form-group">
          <p>
            <input type="email" name="user_email" id="user_email" />
          </p>
          <button>Reset Password</button>
        </div>
        <div class="footer">
          <h>
            Already have an account? <a href="/login">Sign In.</a>
          </h>
        </div>
      </div>
    </div>
  );
}

export default Forgotpass;
