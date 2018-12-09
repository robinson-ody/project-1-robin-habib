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
        const TableHeader = ()=> {
          if(this.props.title === 'Inventory')
              return (
                  <thead>
                  <tr>
                      <th className='chkbox'><input type='checkbox' /></th>
                      <th className='inventory'>{this.props.title} ID</th>
                      <th className='detail'>Detail</th>
                      <th className='stock'>Stock</th>
                      <th className='available'>Available</th>
                      <th className='price'>Price</th>
                      <th className='productImage'>Image</th>
                      <th className='seeUsers'/>
                      <th className='deleteIcon'/>
                  </tr>
                  </thead>
              );
          else if(this.props.title === 'Employee')
              return (
                  <thead>
                  <tr>
                      <th className='chkbox'><input type='checkbox' /></th>
                      <th className='inventory'>{this.props.title} ID</th>
                      <th className='name'>Name</th>
                      <th className='birthday'>Birthday</th>
                      <th className='gender'>Gender</th>
                      <th className='division'>Division</th>
                      <th className='superior'>Superior</th>
                      <th className='deleteIcon'/>
                  </tr>
                  </thead>
              )
        };

        const TableBody = ()=> {
          if(this.props.title === 'Inventory')
              return (
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
              );
          else if(this.props.title === 'Employee')
              return (
                  <tbody>
                  {this.props.data.map((item, index)=>(
                      <tr key={index}>
                          <td className='chkbox'><input type='checkbox' /></td>
                          <td className='inventory'>{item.employeeId}</td>
                          <td className='name'>{item.name}</td>
                          <td className='birthday'>{item.birthday}</td>
                          <td className='gender'>{item.gender}</td>
                          <td className='division'>{item.division}</td>
                          <td className='superior'>{item.superior}</td>
                          <td className='deleteIcon'><img src={trashIcon} width='15px' alt='Trash icon' /></td>
                      </tr>
                  ))
                  }
                  </tbody>
              )
        };

        return(
            <div className='tableSingle'>
                <div className='tableHeader'>
                    <div className='tableTitle'>{this.props.title} List</div>

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
                        <TableHeader/>

                        <TableBody/>
                    </table>
                </div>
            </div>
        )
    }
}