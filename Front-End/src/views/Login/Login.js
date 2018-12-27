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
        axios.post('http://localhost:8080/auth/login', this.state)
            .then(res => {this.props.isAuth(res.data)})
    };

    render() {
    return (
        <div>
            <div className="App">
                <div id="container">
                    <div id="container-left"/>

                    <div id="container-right">
                        <p>Please enter your username and password to login</p>

                        <div className='login'>
                            <input onChangeCapture={this.valueController.bind(this)} type="text" name="username" placeholder="Username..." />
                            <input onChangeCapture={this.valueController.bind(this)} type="password" name="password" placeholder="Password..." />
                            <button className="btn-login" onClick={this.submitController.bind(this)}>Log In</button>
                        </div>

                        <button onClick={()=> this.props.isAuth('true')}/>
                    </div>
                </div>
            </div>
        </div>
    );
  }
}

export default Login;
