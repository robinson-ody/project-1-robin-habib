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

    View(name){
        if (this.state.view === 'inventory')
            return <Inventory name={name}/>;
        else if (this.state.view === 'employee')
            return <Employee name={name}/>;
        else if (this.state.view === 'transaction')
            return <Transaction name={name}/>;
        else if (this.state.view === 'returnItem')
            return <ReturnItem name={name}/>
    };

    render(){
        return(
		    <div className='home'>
                <Sidebar isAuth={(e)=> this.props.isAuth(e)} pageView={(view)=> this.setState({view: view})} />
                <div className='content'>
                    {this.View(this.props.name)}
                </div>
			</div>
		)
	}
}