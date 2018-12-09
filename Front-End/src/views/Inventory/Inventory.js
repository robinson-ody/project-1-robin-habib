import React from 'react';
import Header from '../../components/Header/Header'
import './Inventory.css';
import axios from 'axios';
import image from "../../elements/black.png";
import trashIcon from "../../elements/trash-copy-8.png";
import addNewBtn from "../../elements/add-new-btn.png";
import SearchBar from "../../components/SearchBar/SearchBar";
import PrintIcon from "../../elements/print-icon.png";

export default class Inventory extends React.Component{
	constructor(props){
        super(props);
		this.state = {
			data : []
		}
	}

	componentDidMount() {
	  this.getData()
    };

	getData(){
        axios.get('http://localhost:8080/api/inventory')
            .then(res => {
                this.setState({data : res.data})
                console.log(res.data)
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    deleteHandler(id){
        axios.delete('http://localhost:8080/api/inventory/' + id)
            .then(()=> this.getData())
    }

    render(){
		return(
			<div>
				<Header pageName="Inventory" />

                <div className='tableSingle'>
                    <div className='tableHeader'>
                        <div className='tableTitle'>Inventory List</div>

                        <img src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Product' />

                        <SearchBar placeholder='Seach Item...' />

                        <div id='assignInventory' className='assignInventory'>
                        <span className='tulisanAssignInventory'>
                            Request Item
                        </span>
                        </div>

                        <img src={PrintIcon} id='printIcon' className='printIcon' alt='Print Icon' />
                    </div>

                    <div className='tableBody'>
                        <table>
                            <thead>
                            <tr>
                                <th className='chkbox'><input type='checkbox' /></th>
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
                            {this.state.data.map((item, index)=>(
                                <tr key={index}>
                                    <td className='chkbox'><input type='checkbox' /></td>
                                    <td className='inventory'>{item.inventoryId}</td>
                                    <td className='detail'>{item.detail}</td>
                                    <td className='stock'>{item.stock}</td>
                                    <td className='available'>{item.available}</td>
                                    <td className='price'>Rp <span className='moneyValue'>{item.price.toLocaleString()}</span></td>
                                    <td className='productImage'><img src={image} width='20px' alt='Product'/></td>
                                    <td className='seeUsers'>See Users</td>
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