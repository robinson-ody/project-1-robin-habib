import React, { Component } from 'react';
import './Login.css';

class Login extends Component {
  render() {
    return (
        <div>
            <div className="App">
                <div id="container">
                    <div id="container-left"/>

                    <div id="container-right">
                        <p>Please enter your username and password to login</p>

                        <form action="/login" method="POST">
                            <input type="text" name="username" placeholder="Username..." />
                            <input type="password" name="password" placeholder="Password..." />
                            <button type="submit" className="btn-login">Log In</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
  }
}

export default Login;
