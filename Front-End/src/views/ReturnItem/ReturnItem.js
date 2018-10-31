import React from 'react';
import Header from "../../components/Header/Header";
import TableDouble from "../../components/TableDouble/TableDouble";

export default class ReturnItem extends React.Component{
    render(){
        return(
            <div>
                <Header pageName="Return Item" />
                <TableDouble title='Approved' placeholder='Search Assignment ID...'/>
            </div>
        )
    }
}
