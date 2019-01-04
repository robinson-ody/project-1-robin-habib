import React from 'react';
import Header from '../../components/Header/Header'
import './Inventory.css';
import axios from 'axios';
import image from "../../elements/black.png";
import trashIcon from "../../elements/trash-copy-8.png";
import addNewBtn from "../../elements/add-new-btn.png";
import PrintIcon from "../../elements/print-icon.png";
import cancelBtn from '../../elements/cancel-btn.jpg';
import okBtn from '../../elements/ok-btn.jpg';
import addNewPlaceholder from '../../elements/placeholder-image.svg';
import SearchIcon from "../../elements/icon-search.png";
import multer from 'multer';
import PrintTemplate from 'react-print';

export default class Inventory extends React.Component{
	constructor(props){
        super(props);
		this.state = {
            addNew: 'false',
            data: [],
            selected: [],
            inventoryId: '',
            detail: '',
            price: null,
            stock: null,
            shownData: [],
            activeInventory: '',
            userList: [],
            image: null
		};

		this.tableRequest = React.createRef();
        this.blackBg = React.createRef();
        this.tableAdd = React.createRef();
        this.seeUsers = React.createRef();
        this.warningWindow = React.createRef();
        this.addButton = React.createRef();
	}

	componentDidMount() {
        this.getData();
        if(this.props.role !== 'ADMIN'){
            this.addButton.current.style.display = 'none';
        }
    }

	getData(){
        axios.get('http://localhost:8080/api/inventory')
            .then(res => {
                this.setState({data : res.data});
            })
            .then(()=> {this.setState({shownData: this.state.data})})
            .catch(function(error) {
                console.log(error);
            });
    }

    deleteHandler(ev){
        axios.delete('http://localhost:8080/api/inventory/' + ev.target.id)
            .then(()=> this.getData())
    }

    requestWindowOn(id){
        this.tableRequest.current.style.display = 'block';
        this.blackBg.current.style.display = 'block';
    }

    requestWindowOff(){
        this.tableRequest.current.style.display = 'none';
        this.blackBg.current.style.display = 'none';
    }

    submitRequest(){
	    let submitData = [];
	    for(let i = 0 ; i < this.state.selected.length ; i ++){
	        submitData.push({inventoryId: this.state.selected[i].inventoryId, qty: this.state.selected[i].qty});
        }
        console.log({email: this.props.email, transcData: submitData});
        axios.post('http://localhost:8080/api/transaction/List', {email: this.props.email, transcData: submitData})
            .then(()=> {this.requestWindowOff()})
            .then(()=> {this.setState({selected: []})})
            .then(()=> {this.getData()})
            .then(()=> {
                const els = document.getElementsByClassName('checkboxes');

                Array.prototype.forEach.call(els, function(el) {
                    console.log(el.tagName);
                    if(el.checked === true){
                        el.checked = false
                    }
                });
            })
    }

    addRequest(ev){
	    let dataTemp = this.state.selected;

	    if(ev.target.checked === true){
            axios.get('http://localhost:8080/api/inventory/' + ev.target.id)
                .then(res => {dataTemp.push(res.data)})
                .then(()=> {this.setState({selected: dataTemp})})
        } else {
	        let dataTemp = [];
	        for(let i = 0 ; i < this.state.selected.length ; i++){
	            if(this.state.selected[i].id !== ev.target.id){
	                dataTemp.push(this.state.selected[i])
                }
            }
            this.setState({selected: dataTemp});
        }
    }

    addWindowOn(){
        this.tableAdd.current.style.display = 'block';
        this.blackBg.current.style.display = 'block';
    }

    addWindowOff(){
        this.tableAdd.current.style.display = 'none';
        this.blackBg.current.style.display = 'none';
    }

    valueSetter(e){
        this.setState({[e.target.name]: e.target.value});
    };

    addNewInventory(){
        axios.post('http://localhost:8080/api/inventory/create', {detail: this.state.detail, invenUsers: [], inventoryId: this.state.inventoryId, price: this.state.price, stock: this.state.stock})
            .then(()=> {this.addWindowOff()})
            .then(()=> {this.getData()})
            .then(()=> {this.setState({inventoryId: ''})})
            .then(()=> {this.setState({detail: ''})})
            .then(()=> {this.setState({price: ''})})
            .then(()=> {this.setState({stock: ''})})
    };

    static thousandSeparator(e){
        let result = '';
        let counter = 0;
        for (let i = e.toString().length ; i >= 0 ; i--){
            if(counter % 3 === 0 && counter !== 0 && counter !== e.toString().length){
                result = '.' + e.toString().substr(i, 1) + result;
            } else {
                result = e.toString().substr(i, 1) + result;
            }
            counter += 1;
        }
        return result
    };

    filterData(ev){
        let dataTemp = [];

        for(let i = 0 ; i < this.state.data.length ; i++){
            if(this.state.data[i].detail.toLowerCase().includes(ev.target.value.toLowerCase())){
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].inventoryId.toLowerCase().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].stock.toString().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            } else if(this.state.data[i].price.toString().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.data[i])
            }
        }

        this.setState({shownData: dataTemp});
    };

    qtyHandler(e){
        for(let i = 0 ; i < this.state.selected.length ; i++){
            if(e.target.id === this.state.selected[i].id){
                Object.assign(this.state.selected[i], {qty: e.target.value});
            }
        }
    }

    seeUsersOn(id, inventoryId){
        this.seeUsers.current.style.display = 'block';
        this.blackBg.current.style.display = 'block';
        this.setState({activeInventory: inventoryId});
        axios.get('http://localhost:8080/api/inventory/' + id)
        .then(res => {this.setState({userList: res.data.invenUsers})})
    }

    seeUsersOff(){
        this.seeUsers.current.style.display = 'none';
        this.blackBg.current.style.display = 'none';
    }

    warningWindowOn(){
        this.seeUsers.current.style.display = 'block';
        this.blackBg.current.style.display = 'block';
    }

    warningWindowOff(){
        this.seeUsers.current.style.display = 'none';
        this.blackBg.current.style.display = 'none';
    }

    deleteIconChecker(){
        if(this.props.role === 'ADMIN'){
            return (
                this.state.shownData.map((item, index)=> (
                    <tr key={index}>
                        <td className='chkbox'>
                            <input type='checkbox' name='inventoryId' onChange={this.addRequest.bind(this)}
                                   id={item.id} className='checkboxes'/>
                        </td>
                        <td className='inventory'>{item.inventoryId}</td>
                        <td className='detail'>{item.detail}</td>
                        <td className='stock'>{Inventory.thousandSeparator(item.stock)}</td>
                        <td className='available'>{item.available}</td>
                        <td className='price'>Rp
                            <span className='moneyValue'>
                                        {Inventory.thousandSeparator(item.price)}
                                    </span>
                        </td>
                        <td className='productImage'><img src={image} width='20px' alt='Product'/></td>
                        <td className='seeUsers' onClick={()=> {this.seeUsersOn(item.id, item.inventoryId)}}>
                            See Users
                        </td>
                        <td><img onClick={()=> {this.printHandler(item.id)}} src={PrintIcon} id='printIcon' className='printIcon' alt='Print Icon'/></td>
                        <td className='deleteIcon' onClick={this.deleteHandler.bind(this)} id={item.id}>
                            <img id={item.id} src={trashIcon} width='15px' alt='Trash icon' />
                        </td>
                    </tr>
                ))
            )
        } else {
            return (
                this.state.shownData.map((item, index)=> (
                    <tr key={index}>
                        <td className='chkbox'>
                            <input type='checkbox' name='inventoryId' onChange={this.addRequest.bind(this)}
                                   id={item.id} className='checkboxes'/>
                        </td>
                        <td className='inventory'>{item.inventoryId}</td>
                        <td className='detail'>{item.detail}</td>
                        <td className='stock'>{Inventory.thousandSeparator(item.stock)}</td>
                        <td className='available'>{item.available}</td>
                        <td className='price'>Rp
                            <span className='moneyValue'>
                                {Inventory.thousandSeparator(item.price)}
                            </span>
                        </td>
                        <td className='productImage'><img src={image} width='20px' alt='Product'/></td>
                        <td className='seeUsers' onClick={()=> {this.seeUsersOn(item.id, item.inventoryId)}}>
                            See Users
                        </td>
                        <td><img onClick={()=> {this.printHandler()}} src={PrintIcon} id='printIcon' className='printIcon' alt='Print Icon'/></td>
                    </tr>
                ))
            )
        }
    }

    printHandler(){
        this.props.printItem('TEST');
        window.open('/print');
    }

    uploadFile(ev){
        this.setState({image: ev.target.files[0]});
        // console.log(this.state.image)
        let data = new FormData();
        // data.append('fileImage', ev.target.files[0]);
        for (var i = 0; i < ev.target.files.length; i++)
        {
            let file = ev.target.files.item(i);
            data.append('images[' + i + ']', file, file.name);
        }

        const config = {
            headers: {
                'content-type': 'multipart/form-data'
                // 'Accept': 'application/octet-stream'
            }
        };
        axios.post('http://localhost:8080/api/files', data, config)
            .then(response => response.json())
            .then(res => {
                console.log(res);
                alert('File berhasil diupload')
            })
            .catch(error => {
                console.log(error);
                alert('File gagal diupload')
            })
    }

    render(){
        document.title = "Inventory | Blibli Inventory System";

        return <div>
            <div ref={this.blackBg} className='blackBg'/>

            {/*WINDOW UNTUK REQUEST ITEM*/}
            <div ref={this.tableRequest} className='tableRequest'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Requested Item</div>
                    <img src={okBtn} alt='Ok Button' className='confirmationBtn' onClick={() => this.submitRequest()}/>
                    <img src={cancelBtn} alt='Cancel Button' className='confirmationBtn'
                         onClick={() => this.requestWindowOff()}/>
                </div>

                <div className='tableBody'>
                    <table>
                        <thead>
                        <tr>
                            <th className='detail'>Detail</th>
                            <th className='stock'>Available</th>
                            <th className='request'>Request</th>
                        </tr>
                        </thead>

                        <tbody>
                            {this.state.selected.map((item, index) => (
                                <tr key={index}>
                                    <td className='detail'>{item.detail}</td>
                                    <td className='stock'>{Inventory.thousandSeparator(item.available)}</td>
                                    <td className='request'><input id={item.id} onChange={this.qtyHandler.bind(this)} type='number'/></td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
            {/*END OF WINDOW UNTUK REQUEST ITEM*/}

            {/*WINDOW UNTUK ADD ITEM*/}
            <div ref={this.tableAdd} className='tableAdd'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Add New Item</div>
                    <img src={okBtn} alt='Ok Button' className='confirmationBtn'
                         onClick={() => this.addNewInventory()}/>
                    <img src={cancelBtn} alt='Cancel Button' className='confirmationBtn'
                         onClick={() => this.addWindowOff()}/>
                </div>

                <div className='tableBody'>
                    <input type='file' name='file' id='file' className='inputFile' onChangeCapture={this.uploadFile.bind(this)} accept='image/*'/>
                    <label htmlFor="file" id='previewImage'><img src={addNewPlaceholder} className="placeholder-image" alt="Add New"/></label>
                    <input value={this.state.inventoryId} onChangeCapture={this.valueSetter.bind(this)} name='inventoryId'
                           type='text' placeholder='Inventory ID'/>
                    <input value={this.state.detail} onChangeCapture={this.valueSetter.bind(this)} name='detail' type='text'
                           placeholder='Detail'/>
                    <input value={this.state.price} onChangeCapture={this.valueSetter.bind(this)} name='price' type='text'
                           placeholder='Price'/>
                    <input value={this.state.stock} onChangeCapture={this.valueSetter.bind(this)} name='stock' type='text'
                           placeholder='Total Items' style={{width: '80px'}}/> <span className='pcsWord'>Pcs</span>
                </div>
            </div>
            {/*END OF WINDOW UNTUK ADD ITEM*/}

            {/*WINDOW UNTUK SEE USERS*/}
            <div ref={this.seeUsers} className='tableSeeUsers'>
                <div className='tableHeader'>
                    <div className='tableTitle'>{this.state.activeInventory}</div>
                    <img src={okBtn} alt='Ok Button' className='confirmationBtn' onClick={() => this.seeUsersOff()}/>
                </div>

                <div className='tableBody'>
                    <table>
                        <thead>
                        <tr>
                            <th className='emailUsers'>Users</th>
                            <th style={{textAlign:'center'}} className='qty'>Qty</th>
                        </tr>
                        </thead>

                        <tbody>
                        {this.state.userList.map((item, index) => (
                            <tr key={index}>
                                <td className='emailUsers'>{item.email}</td>
                                <td className='qty'>{item.qty}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
            {/*END OF WINDOW UNTUK SEE USERS*/}

            {/*WARNING WINDOW*/}
            <div ref={this.warningWindow} className='tableSeeUsers'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Warning!</div>
                    <img src={okBtn} alt='Ok Button' className='confirmationBtn' onClick={() => this.warningWindowOff()}/>
                </div>

                <div className='tableBody'>
                    Please return all the items before deleting inventory data.
                </div>
            </div>
            {/*END OF WARNING WINDOW*/}

            <Header pageName="Inventory" name={this.props.name}/>

            <div className='tableSingle'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Inventory List</div>

                    <img ref={this.addButton} src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Product'
                         onClick={() => this.addWindowOn()}/>

                    <div className='searchBar'>
                        <input
                            onChange={this.filterData.bind(this)}
                            type='text'
                            placeholder='Search Items...'
                            className='inputSearch'
                        />
                        <img src={SearchIcon} alt='Search Icon' className='searchIcon'/>
                    </div>

                    <div id='assignInventory' className='assignInventory' onClick={() => this.requestWindowOn()}>
                        <span className='tulisanAssignInventory'>
                            Request Item
                        </span>
                    </div>

                    {/*<img onClick={()=> {this.printHandler()}} src={PrintIcon} id='printIcon' className='printIcon' alt='Print Icon'/>*/}
                </div>

                <div className='tableBody'>
                    <table>
                        <thead>
                        <tr>
                            <th className='chkbox' style={{width:'19px'}} />
                            <th className='inventory'>Inventory ID</th>
                            <th className='detail'>Detail</th>
                            <th className='stock'>Stock</th>
                            <th className='available'>Available</th>
                            <th className='price'>Price</th>
                            <th className='productImage'>Image</th>
                            <th className='seeUsers'/>
                            <th ref={this.deleteButton} className='deleteIcon'/>
                            <th className='printIcon'/>
                        </tr>
                        </thead>

                        <tbody>
                            {this.deleteIconChecker()}
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    }
}