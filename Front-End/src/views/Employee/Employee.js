import React from 'react';
import Header from "../../components/Header/Header";
import TableSingle from '../../components/TableSingle/TableSingle';

export default class Employee extends React.Component{
    render(){
        return(
            <div>
                <Header pageName="Employee" />
                <TableSingle title='Employee List' version='employee' placeholder='Search Employees...' />
            </div>
        )
    }
}
