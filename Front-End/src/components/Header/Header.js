import React from 'react';
import './Header.css'

function Header(props){
    return(
        <div className='header'>
            <h1>{props.pageName}</h1>
            <div className='welcome'>Welcome, User</div>
        </div>
    )
}

export default Header