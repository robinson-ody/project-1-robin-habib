import React, { Component } from 'react';
import './Login.css';

class Login extends Component {
  render() {
    document.title = "Login Page | Blibli Inventory System"

    return (
      <div className="App">

        <div id="container">
          <div id="container-left">
          </div>

          <div id="container-right">
            <p>Please enter your username and password to login</p>

            <form action="http://localhost:8080/auth/login" method="POST">
              <input type="text" name="username" placeholder="Username..." />
              <input type="password" name="password" placeholder="Password..." />
              <button type="submit" className="btn-login">Log In</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;
