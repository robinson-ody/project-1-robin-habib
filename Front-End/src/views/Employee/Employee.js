import React from 'react';
import Header from "../../components/Header/Header";
import TableSingle from '../../components/TableSingle/TableSingle';
import axios from 'axios';

export default class Employee extends React.Component{
    constructor(props) {
        axios.get('http://localhost:8080/api/employee')
            .then(res => {
                this.setState({data : res.data})
                console.log(res.data)
            })
            .catch(function(error) {
                console.log(error);
            });

        super(props);
        this.state = {
            data : []
        }
    }

    render(){
        return(
            <div>
                <Header pageName="Employee" />
                <TableSingle title='Employee List' version='employee' placeholder='Search Employees...'
                             data={this.state.data}/>
            </div>
        )
    }
}
