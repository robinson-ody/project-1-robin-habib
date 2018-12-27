import React from 'react';
import logo from "../../elements/blibli-logo.png";
import './Sidebar.css';

export default class Sidebar extends React.Component {
    constructor() {
        super();
        this.state = {
            activeView : 'inventory'
        }
    }

    render() {
        const menuClick = (view)=> {
            document.getElementById(this.state.activeView).className = 'menu';

            this.props.pageView(view);
            this.setState({activeView : view});

            document.getElementById(view).className = 'menu active';
        };

        return(
            <div className='sidebar'>
                <img src={logo} alt='Logo Blibli' className='logo' />

                <hr className='divider' />

                <div className='menu-container'>
                    <div id='inventory' onClick={(ev)=> menuClick(ev.target.valueOf().id)} className='menu active'>Inventory</div>
                    <div id='employee' onClick={(ev)=> menuClick(ev.target.valueOf().id)} className='menu'>Employee</div>
                    <div id='transaction' onClick={(ev)=> menuClick(ev.target.valueOf().id)} className='menu'>Transaction</div>
                    <div id='returnItem' onClick={(ev)=> menuClick(ev.target.valueOf().id)} className='menu'>Return Item</div>
                </div>

                <div onClick={()=> this.props.isAuth('false')} className='menu-container logoutContainer'>
                    <div className='menu logoutBtn'>
                        <div className='tulisanLogout'>Log Out</div>
                    </div>
                </div>
            </div>
        )
    }
}