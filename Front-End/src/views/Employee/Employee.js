import React from 'react';
import Header from "../../components/Header/Header";
import axios from 'axios';
import addNewBtn from "../../elements/add-new-btn.png";
import trashIcon from "../../elements/trash-copy-8.png";
import './Employee.css';
import okBtn from "../../elements/ok-btn.jpg";
import cancelBtn from "../../elements/cancel-btn.jpg";
import addNewPlaceholder from "../../elements/placeholder-image.svg";
import SearchIcon from "../../elements/icon-search.png";

export default class Employee extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            data : [],
            email: '',
            name: '',
            birthday: '',
            division: '',
            gender: '',
            role: '',
            superior: '',
            password: '',
            shownData: []
        };

        this.tableAdd = React.createRef();
        this.blackBg = React.createRef();
    }

    componentDidMount(){
      this.getData();
    };

    getData(){
        axios.get('http://localhost:8080/api/employee')
            .then(res => {
                this.setState({data : res.data});
            })
            .then(()=> {this.setState({shownData: this.state.data})})
            .catch(function(error) {
                console.log(error);
            });
    }

    deleteHandler(id){
        axios.delete('http://localhost:8080/api/employee/' + id)
            .then(()=> this.getData())
    }

    valueSetter(e){
        console.log('OK');
        this.setState({[e.target.name]: e.target.value});
    };

    addWindowOn(){
        this.tableAdd.current.style.display = 'block';
        this.blackBg.current.style.display = 'block';
    }

    addWindowOff(){
        this.tableAdd.current.style.display = 'none';
        this.blackBg.current.style.display = 'none';
    }

    addNewEmployee(){
        axios.post('http://localhost:8080/api/employee/create', {
            email: this.state.email,
            name: this.state.name,
            birthday: this.state.birthday,
            division: this.state.division,
            gender: this.state.gender,
            role: this.state.role,
            superior: this.state.superior,
            password: this.state.password
        })
            .then(res => {this.addWindowOff()})
            .then(res => {this.getData()})
            .then(this.setState({email: ''}))
            .then(this.setState({name: ''}))
            .then(this.setState({birthday: ''}))
            .then(this.setState({division: ''}))
            .then(this.setState({gender: ''}))
            .then(this.setState({role: ''}))
            .then(this.setState({superior: ''}))
            .then(this.setState({password: ''}))
            .catch(error => {console.log(error)})
    };

    filterData(ev){
        let dataTemp = [];

        for(let i = 0 ; i < this.state.data.length ; i++){
            if(this.state.data[i].email.toLowerCase().includes(ev.target.value.toLowerCase())){
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].name.toLowerCase().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].birthday.toString().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].gender.toLowerCase().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].division.toLowerCase().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].superior.toLowerCase().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            }
        }

        this.setState({shownData: dataTemp});
    };

    // dateFormatChanger(ev){
    //     const d = new Date("2015-03-15T14:01:16.447Z");
    //     alert(d.toLocaleString('id'))
    // };

    render(){
        document.title = "Employee | Blibli Inventory System";

        return(
            <div>
                <div ref={this.blackBg} className='blackBg' />

                <Header pageName="Employee" name={this.props.name}/>

                {/*TABLE UNTUK ADD EMPLOYEE*/}
                <div ref={this.tableAdd} className='tableAdd' style={{top:'130px'}}>
                    <div className='tableHeader'>
                        <div className='tableTitle'>Add New Item</div>
                        <img src={okBtn} alt='Ok Button' className='confirmationBtn' onClick={()=> this.addNewEmployee()} />
                        <img src={cancelBtn} alt='Cancel Button' className='confirmationBtn' onClick={()=> this.addWindowOff()} />
                    </div>

                    <div className='tableBody' style={{height:'350px'}}>
                        <img src={addNewPlaceholder} className="placeholder-image" alt="Add New"/>
                        <input style={{width: '200px'}} value={this.state.email} onChangeCapture={this.valueSetter.bind(this)} name='email' type='text' placeholder='Email'/>
                        <input style={{width: '200px'}} value={this.state.name} onChangeCapture={this.valueSetter.bind(this)} name='name' type='text' placeholder='Name'/>
                        <input onChangeCapture={this.valueSetter.bind(this)} value={this.state.birthday} name='birthday' type='date' placeholder='Birth:&nbsp;' />
                        <input style={{width: '200px'}} value={this.state.division} onChangeCapture={this.valueSetter.bind(this)} name='division' type='text' placeholder='Division' /><br clear='both'/>
                        <span className='inputText' style={{borderBottom:'none'}}>Gender</span>
                        <input type='radio' value='male' onChangeCapture={this.valueSetter.bind(this)} name='gender'/> <span className='inputText' style={{borderBottom:'none', marginLeft:'0'}}>Male</span>
                        <input type='radio' value='female' onChangeCapture={this.valueSetter.bind(this)} name='gender'/> <span className='inputText' style={{borderBottom:'none', marginLeft:'0'}}>Female</span><br/>
                        <select onChangeCapture={this.valueSetter.bind(this)} name='role'>
                            <option style={{display:'none'}}>Role</option>
                            <option>Admin</option>
                            <option>Manager</option>
                            <option>User</option>
                        </select>
                        <input style={{width: '250px'}} value={this.state.superior} onChangeCapture={this.valueSetter.bind(this)} name='superior' type='text' placeholder='Superior' />
                        <input style={{width: '360px'}} value={this.state.password} onChangeCapture={this.valueSetter.bind(this)} name='password' type='password' placeholder='Password' />
                    </div>
                </div>
                {/*END OF TABLE UNTUK ADD EMPLOYEE*/}

                <div className='tableSingle'>
                    <div className='tableHeader'>
                        <div className='tableTitle'>Employee List</div>

                        <img src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Employee' onClick={()=> this.addWindowOn()}/>

                        <div className='searchBar'>
                            <input onChange={this.filterData.bind(this)} type='text' placeholder='Search Employee...' className='inputSearch' />
                            <img src={SearchIcon} alt='Search Icon' className='searchIcon'/>
                        </div>
                    </div>

                    <div className='tableBody'>
                        <table>
                            <thead>
                                <tr>
                                    <th className='employee'>Email</th>
                                    <th className='name'>Name</th>
                                    <th className='birthday'>Birthday</th>
                                    <th className='gender'>Gender</th>
                                    <th className='division'>Division</th>
                                    <th className='superior'>Superior</th>
                                    <th className='deleteIcon'/>
                                </tr>
                            </thead>

                            <tbody>
                                {this.state.shownData.map((item, index)=>(
                                    <tr key={index}>
                                        <td className='employee'>{item.email}</td>
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
