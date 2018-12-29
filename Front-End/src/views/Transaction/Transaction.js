import React from 'react';
import Header from "../../components/Header/Header";
import TableDouble from '../../components/TableDouble/TableDouble';

export default class Transaction extends React.Component{
    render(){
        return(
            <div>
                <Header pageName="Transaction" name={this.props.name}/>
                <TableDouble title='History' placeholder='Search Assignment ID...'/>
            </div>
        )
    }
}
