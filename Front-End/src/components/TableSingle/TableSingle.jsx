import React from 'react';
import './TableSingle.css';
import image from '../../elements/black.png';
import trashIcon from '../../elements/trash-copy-8.png'
import addNewBtn from '../../elements/add-new-btn.png'
import SearchBar from '../SearchBar/SearchBar';
import PrintIcon from '../../elements/print-icon.png';

export default class TableSingle extends React.Component {
    componentDidMount() {
        if(this.props.version !== 'inventory'){
            document.getElementById('assignInventory').style.display = "none";
            document.getElementById('printIcon').style.display = 'none';
        }
    }

    render() {
        const productData = [
            {id : 123456789001, nama : 'Item Example Number 1', stock : 100, available : 10, price: '1.000.000'},
            {id : 123456789002, nama : 'Item Example Number 2', stock : 200, available : 20, price: '2.000.000'},
            {id : 123456789003, nama : 'Item Example Number 3', stock : 300, available : 30, price: '3.000.000'},
            {id : 123456789004, nama : 'Item Example Number 4', stock : 400, available : 40, price: '4.000.000'},
            {id : 123456789005, nama : 'Item Example Number 5', stock : 500, available : 50, price: '5.000.000'},
            {id : 123456789006, nama : 'Item Example Number 6', stock : 600, available : 60, price: '6.000.000'},
            {id : 123456789007, nama : 'Item Example Number 7', stock : 700, available : 70, price: '7.000.000'},
            {id : 123456789008, nama : 'Item Example Number 8', stock : 800, available : 80, price: '8.000.000'},
            {id : 123456789009, nama : 'Item Example Number 9', stock : 900, available : 90, price: '9.000.000'},
            {id : 123456789010, nama : 'Item Example Number 10', stock : 1000, available : 100, price: '10.000.000'},
            {id : 123456789011, nama : 'Item Example Number 11', stock : 1100, available : 110, price: '11.000.000'},
            {id : 123456789012, nama : 'Item Example Number 12', stock : 1200, available : 120, price: '12.000.000'}
        ];

        return(
            <div className='tableSingle'>
                <div className='tableHeader'>
                    <div className='tableTitle'>{this.props.title}</div>
                    <img src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Product' />
                    <SearchBar placeholder={this.props.placeholder} />
                    <div id='assignInventory' className='assignInventory'><span className='tulisanAssignInventory'>Assign Inventory</span></div>
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
                                <th className='seeUsers'></th>
                                <th className='deleteIcon'></th>
                            </tr>
                        </thead>

                        <tbody>
                            {productData.map((item, index)=>(
                                <tr key={index}>
                                    <td className='chkbox'><input type='checkbox' /></td>
                                    <td className='inventory'>{item.id}</td>
                                    <td className='detail'>{item.nama}</td>
                                    <td className='stock'>{item.stock}</td>
                                    <td className='available'>{item.available}</td>
                                    <td className='price'>Rp <span className='moneyValue'>{item.price}</span></td>
                                    <td className='productImage'><img src={image} width='20px' alt='Product'/></td>
                                    <td className='seeUsers'>See Users</td>
                                    <td className='deleteIcon'><img src={trashIcon} width='15px' alt='Trash icon' /></td>
                                </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}