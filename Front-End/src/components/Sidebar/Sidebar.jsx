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
        const menuClick = (pageView, activeView)=> {
            this.props.pageView(pageView);
            this.setState({activeView})
        };

        let inventoryClass = "";
        let employeeClass = "";
        let transactionClass = "";
        let returnClass = "";

        if(this.state.activeView === 'inventory'){
            inventoryClass = "menu active"
        }else
            inventoryClass = "menu";

        if(this.state.activeView === 'employee'){
            employeeClass = "menu active"
        }else
            employeeClass = "menu";

        if(this.state.activeView === 'transaction'){
            transactionClass = "menu active"
        }else
            transactionClass = "menu";

        if(this.state.activeView === 'returnItem'){
            returnClass = "menu active"
        }else
            returnClass = "menu";

        return(
            <div className='sidebar'>
                <img src={logo} alt='Logo Blibli' className='logo' />

                <hr className='divider' />

                <div className='menu-container'>
                    <div onClick={()=> menuClick('inventory', 'inventory')} className={inventoryClass}>Inventory</div>
                    <div onClick={()=> menuClick('employee', 'employee')} className={employeeClass}>Employee</div>
                    <div onClick={()=> menuClick('transaction', 'transaction')} className={transactionClass}>Transaction</div>
                    <div onClick={()=> menuClick('returnItem', 'returnItem')} className={returnClass}>Return Item</div>
                </div>
            </div>
        )
    }
}