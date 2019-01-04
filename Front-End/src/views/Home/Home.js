import React from 'react';
import '../Login/Login.css';
import Inventory from '../Inventory/Inventory';
import Employee from '../Employee/Employee';
import Transaction from '../Transaction/Transaction';
import ReturnItem from '../ReturnItem/ReturnItem';
import Sidebar from '../../components/Sidebar/Sidebar';
import './Home.css';

export default class Home extends React.Component{
    constructor(){
        super();
        this.state = {
            view : 'inventory'
        }
    }

    View(name, email){
        if (this.state.view === 'inventory')
            return <Inventory name={name} email={email} role={this.props.role} printItem={(e)=> this.props.printItem(e)}/>;
        else if (this.state.view === 'employee')
            return <Employee name={name}/>;
        else if (this.state.view === 'transaction')
            return <Transaction name={name} email={email} role={this.props.role}/>;
        else if (this.state.view === 'returnItem')
            return <ReturnItem name={name} email={email} role={this.props.role}/>
    };

    render(){
        return(
		    <div className='home'>
                <Sidebar isAuth={(e)=> this.props.isAuth(e)} role={this.props.role} pageView={(view)=> this.setState({view: view})} />
                <div className='content'>
                    {this.View(this.props.name, this.props.email)}
                </div>
			</div>
		)
	}
}