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
            data : [],
            dummy : [
                {employeeId : '20181208', name: 'Muhammad Habib', birthday: '7 November 1996', gender: 'Male', division: 'Technology', superior: 'Anthonius'}
            ]
        }
    }

    render(){
        return(
            <div>
                <Header pageName="Employee" />
                <TableSingle title='Employee' version='employee' placeholder='Search Employees...'
                             data={this.state.dummy}/>
            </div>
        )
    }
}
