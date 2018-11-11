import React, { Component } from 'react';
import './Login.css';

class Login extends Component {
  render() {
    return (
        <div>
            <div className="App">
                <div id="container">
                    <div id="container-left">
                    </div>

                    <div id="container-right">
                        <p>Please enter your username and password to login</p>

                        <form th:action="@{loocalhost:8080/login}" method="POST">
                            <input type="text" name="username" placeholder="Username..." />
                            <input type="password" name="password" placeholder="Password..." />
                            <button onClick={()=> this.props.isAuth('true')} type="submit" className="btn-login">Log In</button>
                        </form>

                        {/*<div onClick={()=> this.props.isAuth('true')} className='btn-login'>Log In</div>*/}
                    </div>
                </div>
            </div>
        </div>
    );
  }
}

export default Login;
