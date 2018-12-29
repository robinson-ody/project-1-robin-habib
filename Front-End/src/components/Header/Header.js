import React from 'react';
import './Header.css'

export default class Header extends React.Component{
    render() {
        return(
            <div className='header'>
                <h1>{this.props.pageName}</h1>
                <div className='welcome'>Welcome, {this.props.name}</div>
            </div>
        )
    }
}