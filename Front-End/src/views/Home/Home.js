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

        return(
		    <div className='home'>
                <Sidebar pageView={(view)=> this.setState({view: view})} />
                <div className='content'>
                    <View/>
                </div>
			</div>
		)
	}
}