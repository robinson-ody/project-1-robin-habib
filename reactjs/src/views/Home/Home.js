import React from 'react';
import logo from '../../elements/blibli-logo.png';
import '../../App.css';
import Inventory from '../Inventory/Inventory'
import Employee from '../Employee/Employee'
import Transaction from '../Transaction/Transaction'
import ReturnItem from '../ReturnItem/ReturnItem'

export default class Home extends React.Component{
    constructor(){
        super();
        this.state = {
            view : 'inventory'
        }
    }

	render(){
        document.title = "Home | Blibli Inventory System"

        const View = ()=> {
            if (this.state.view === 'inventory')
                return <Inventory/>;
            else if (this.state.view === 'employee')
                return <Employee/>;
            else if (this.state.view === 'transaction')
                return <Transaction/>;
            else if (this.state.view === 'returnItem')
                return <ReturnItem/>
        };

        let inventoryClass = "";
        let employeeClass = "";
        let transactionClass = "";
        let returnClass = "";


        if(this.state.view === 'inventory'){
            inventoryClass = "menu active"
        }else
            inventoryClass = "menu";

        if(this.state.view === 'employee'){
            employeeClass = "menu active"
        }else
            employeeClass = "menu";

        if(this.state.view === 'transaction'){
            transactionClass = "menu active"
        }else
            transactionClass = "menu";

        if(this.state.view === 'returnItem'){
            returnClass = "menu active"
        }else
            returnClass = "menu";

        return(
		    <div>
				<div className='sidebar'>
                    <img src={logo} alt='Logo Blibli' className='logo' />

                    <hr className='divider' />

                    <div className='menu-container'>
                        <div onClick={()=> this.setState({view: 'inventory'})} className={inventoryClass}>Inventory</div>
                        <div onClick={()=> this.setState({view: 'employee'})} className={employeeClass}>Employee</div>
                        <div onClick={()=> this.setState({view: 'transaction'})} className={transactionClass}>Transaction</div>
                        <div onClick={()=> this.setState({view: 'returnItem'})} className={returnClass}>Return Item</div>
                    </div>
				</div>

                <div className='content'><View/></div>
			</div>
		)
	}
}