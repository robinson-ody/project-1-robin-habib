import React from 'react';
import Header from "../../components/Header/Header";
import axios from 'axios';
import addNewBtn from "../../elements/add-new-btn.png";
import SearchBar from "../../components/SearchBar/SearchBar";
import trashIcon from "../../elements/trash-copy-8.png";
import './Employee.css';

export default class Employee extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            data : []
        }
    }

    componentDidMount(){
      this.getData();
    };

    getData(){
        axios.get('http://localhost:8080/api/employee')
            .then(res => {
                this.setState({data : res.data})
                console.log(res.data)
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    // changeFormat(e){
    //     let hasil = '';
    //   for(let i = 0 ; i <= e.length ; i++){
    //       if (e.substr(i, 1) === '-') {
    //           hasil += '/'
    //       } else {
    //           hasil += e.substr(i, 1)
    //       }
    //   }
    //   return hasil
    // };

    deleteHandler(id){
        axios.delete('http://localhost:8080/api/employee/' + id)
            .then(()=> this.getData())
    }

    render(){
        return(
            <div>
                <Header pageName="Employee" />

                <div className='tableSingle'>
                    <div className='tableHeader'>
                        <div className='tableTitle'>Employee List</div>

                        <img src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Product' />

                        <SearchBar placeholder='Search Employee...' />
                    </div>

                    <div className='tableBody'>
                        <table>
                            <thead>
                                <tr>
                                    <th className='chkbox'><input type='checkbox' /></th>
                                    <th className='inventory'>Employee ID</th>
                                    <th className='name'>Name</th>
                                    <th className='birthday'>Birthday</th>
                                    <th className='gender'>Gender</th>
                                    <th className='division'>Division</th>
                                    <th className='superior'>Superior</th>
                                    <th className='deleteIcon'/>
                                </tr>
                            </thead>

                            <tbody>
                                {this.state.data.map((item, index)=>(
                                    <tr key={index}>
                                        <td className='chkbox'><input type='checkbox' /></td>
                                        <td className='inventory'>{item.id}</td>
                                        <td className='name'>{item.name}</td>
                                        <td className='birthday'>{item.birthday}</td>
                                        <td className='gender'>{item.gender}</td>
                                        <td className='division'>{item.division}</td>
                                        <td className='superior'>{item.superior}</td>
                                        <td className='deleteIcon' onClick={()=> this.deleteHandler(item.id)}><img src={trashIcon} width='15px' alt='Trash icon' /></td>
                                    </tr>
                                ))
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        )
    }
}
