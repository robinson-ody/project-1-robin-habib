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
        return(
            <div className='tableSingle'>
                <div className='tableHeader'>
                    <div className='tableTitle'>{this.props.title}</div>

                    <img src={addNewBtn} alt='Add New Button' className='addNewBtn' title='Add New Product' />

                    <SearchBar placeholder={this.props.placeholder} />

                    <div id='assignInventory' className='assignInventory'>
                        <span className='tulisanAssignInventory'>
                            Assign Inventory
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
                            {this.props.data.map((item, index)=>(
                                <tr key={index}>
                                    <td className='chkbox'><input type='checkbox' /></td>
                                    <td className='inventory'>{item.inventoryId}</td>
                                    <td className='detail'>{item.detail}</td>
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