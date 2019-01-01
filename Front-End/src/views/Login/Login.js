import React, { Component } from 'react';
import './Login.css';
import axios from 'axios';

class Login extends Component {
    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    valueController(e){
        this.setState({[e.target.name] : e.target.value});
    };

    submitController(){
        axios.post('http://localhost:8080/api/auth/login', this.state)
            .then(res => {
                this.props.isAuth(res.data.success);
                this.props.name(res.data.name);
            })
    };

    render() {
        document.title = "Login | Blibli Inventory System";

        return (
            <div>
                <div className="App">
                    <div id="container">
                        <div id="container-left"/>

                        <div id="container-right">
                            <p>Please enter your email and password to login</p>

                            <div className='login'>
                                <input onChangeCapture={this.valueController.bind(this)} type="email" name="email" placeholder="Email..." />
                                <input onChangeCapture={this.valueController.bind(this)} type="password" name="password" placeholder="Password..." />
                                <button className="btn-login" onClick={this.submitController.bind(this)}>Log In</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;