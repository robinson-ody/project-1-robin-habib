import React from 'react';
import Login from "./views/Login/Login";
import Home from "./views/Home/Home";
import Cookies from 'universal-cookie';

export default class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            isAuth: false,
            name: '',
            email: '',
            role: ''
        };
    }

    render() {
        const IsAuth = ()=> {
            if(this.state.isAuth === false) {
                return <Login isAuth={(e)=> this.setState({isAuth : e})} name={(e)=> this.setState({name : e})} email={(e)=> this.setState({email: e})} role={(e)=> this.setState({role: e})}/>
            } else {
                return <Home isAuth={(e)=> this.setState({isAuth : e})} name={this.state.name} email={this.state.email} role={this.state.role}/>
            }
        };

        return(
            <IsAuth/>
        )
    }
}