import React from 'react';
import './TableSingle.css';

export default class TableSingle extends React.Component {
    render() {
        return(
            <div className='tableSingle'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Inventory List</div>
                    <div className='tableSubTitle'>List of all items in the company</div>
                </div>

                <div className='tableBody'>
                    <table>
                        <thead>
                            <tr>
                                <th className='widthChkbox'><input type='checkbox' /></th>
                                <th>Inventory ID</th>
                                <th>Detail</th>
                                <th>Stock</th>
                                <th>Available</th>
                                <th>Price</th>
                                <th>Image</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td className='widthChkbox'><input type='checkbox' /></td>
                                <td>Meja-001</td>
                                <td>Meja Komputer</td>
                                <td>225</td>
                                <td>3</td>
                                <td>Rp 1.250.000</td>
                                <td></td>
                                <td>See Users</td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}