import React from 'react';
import Header from '../../components/Header/Header'
import './Inventory.css';
import axios from 'axios';
import image from "../../elements/black.png";
import trashIcon from "../../elements/trash-copy-8.png";
import addNewBtn from "../../elements/add-new-btn.png";
import SearchBar from "../../components/SearchBar/SearchBar";
import PrintIcon from "../../elements/print-icon.png";
import cancelBtn from '../../elements/cancel-btn.jpg';
import okBtn from '../../elements/ok-btn.jpg';
import addNewPlaceholder from '../../elements/placeholder-image.svg';

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
            stock: null
		};

		this.tableRequest = React.createRef();
        this.blackBg = React.createRef();
        this.tableAdd = React.createRef();
	}

	componentDidMount() {
	  this.getData()
    };

	getData(){
        axios.get('http://localhost:8080/api/inventory')
            .then(res => {
                this.setState({data : res.data});
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    deleteHandler(id){
        axios.delete('http://localhost:8080/api/inventory/' + id)
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
	    console.log('OK')
    }

    addRequest(id){
	    let dataTemp = this.state.selected;

	    axios.get('http://localhost:8080/api/inventory/' + id)
            .then(res => {dataTemp.push(res.data)})
            .then(()=> {this.setState({selected: dataTemp})})
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
        axios.post('http://localhost:8080/api/inventory/create', {inventoryId: this.state.inventoryId, detail: this.state.detail, price: this.state.price, stock: this.state.stock})
            .then(res => {this.addWindowOff()})
            .then(res => {this.getData()})
            .then(this.setState({inventoryId: ''}))
            .then(this.setState({detail: ''}))
            .then(this.setState({price: ''}))
            .then(this.setState({stock: ''}))
            .catch(error => {console.log(error)})
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

    // Selected(){
    // };

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
                            <th className='stock'>Stock</th>
                        </tr>
                        </thead>

                        <tbody>
                            {this.state.selected.map((item, index) => (
                                <tr key={index}>
                                    <td className='detail'>{item.detail}</td>
                                    <td className='stock'>{item.stock}</td>
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
                    <img src={addNewPlaceholder} className="placeholder-image" alt="Add New"/>
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

            <Header pageName="Inventory" name={this.props.name}/>

            <div className='tableSingle'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Inventory List</div>

                    <img src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Product'
                         onClick={() => this.addWindowOn()}/>

                    <SearchBar placeholder='Search Item...'/>

                    <div id='assignInventory' className='assignInventory' onClick={() => this.requestWindowOn()}>
                        <span className='tulisanAssignInventory'>
                            Request Item
                        </span>
                    </div>

                    <img src={PrintIcon} id='printIcon' className='printIcon' alt='Print Icon'/>
                </div>

                <div className='tableBody'>
                    <table>
                        <thead>
                        <tr>
                            <th className='chkbox'><input type='checkbox'/></th>
                            <th className='inventory'>Inventory ID</th>
                            <th className='detail'>Detail</th>
                            <th className='stock'>Stock</th>
                            <th className='available'>Available</th>
                            <th className='price'>Price</th>
                            <th className='productImage'>Image</th>
                            <th className='seeUsers'/>
                            <th className='deleteIcon'/>
                        </tr>
                        </thead>

                        <tbody>
                        {this.state.data.map((item, index) => (
                            <tr key={index}>
                                <td className='chkbox'><input type='checkbox' name='inventoryId' onChange={() => {
                                    this.addRequest(item.id)
                                }}/></td>
                                <td className='inventory'>{item.inventoryId}</td>
                                <td className='detail'>{item.detail}</td>
                                <td className='stock'>{Inventory.thousandSeparator(item.stock)}</td>
                                <td className='available'>{item.available}</td>
                                <td className='price'>Rp <span
                                    className='moneyValue'>{Inventory.thousandSeparator(item.price)}</span></td>
                                <td className='productImage'><img src={image} width='20px' alt='Product'/></td>
                                <td className='seeUsers'>See Users</td>
                                <td className='deleteIcon' onClick={() => this.deleteHandler(item.id)}><img
                                    src={trashIcon}
                                    width='15px'
                                    alt='Trash icon'/>
                                </td>
                            </tr>
                        ))
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    }
}