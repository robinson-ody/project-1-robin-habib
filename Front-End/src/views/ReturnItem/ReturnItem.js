import React from 'react';
import Header from "../../components/Header/Header";
import CheckActive from "../../elements/check-active.png";
import DeleteActive from "../../elements/delete-active.png";
import SearchIcon from "../../elements/icon-search.png";

export default class ReturnItem extends React.Component{
    render(){
        document.title = "Return Item | Blibli Inventory System";

        const dataDummy = [
            {itemName: 'Meja', qty : 1, statusItem: 'Pending'},
            {itemName: 'Kursi', qty : 1, statusItem: 'Approved'},
            {itemName: 'Printer', qty : 2, statusItem: 'Pending'},
        ];

        return(
            <div>
                <Header pageName="Return Item" name={this.props.name}/>

                <div className='tableContainer'>
                    <div className='tableLeft'>
                        <div className='tableHeaderDouble'>
                            <div className='tableTitleDouble'>{this.props.title}</div>
                            <div className='stylingSearch'>
                                <div className='searchBar'>
                                    <input onChange={this.filterData.bind(this)} type='text' placeholder='Search Items...' className='inputSearch' />
                                    <img src={SearchIcon} alt='Search Icon' className='searchIcon'/>
                                </div>
                            </div>
                        </div>

                        <div className='tableBodyDouble'>
                            <table>
                                <thead>
                                <tr>
                                    <th className='assignment'>Assignment ID</th>
                                    <th className='requestDate'>Request Date</th>
                                    <th className='staff'>Staff</th>
                                </tr>
                                </thead>

                                <tbody>
                                {this.state.historyData.map((item, index)=>(
                                    <tr key={index} onClick={()=> this.historyChanger(item.id)}>
                                        <td className='assignment'>{item.id}</td>
                                        <td className='requestDate'>{item.requestDate}</td>
                                        <td className='staff'>{item.staff}</td>
                                    </tr>
                                ))
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div className='tableRight'>
                        <div className='tableHeaderDouble'>
                            <div className='tableTitleDouble'>{this.state.activeHistory}</div>
                        </div>

                        <div className='tableBodyDouble'>
                            <table>
                                <thead>
                                <tr>
                                    <th className='itemName'>Item</th>
                                    <th className='itemQty'>Qty</th>
                                    <th className='itemStatus'>Status</th>
                                </tr>
                                </thead>

                                <tbody>

                                {dataDummy.map((item, index)=>(
                                    <tr key={index}>
                                        <td className='itemName'>{item.itemName}</td>
                                        <td className='itemQty'>{item.qty}</td>
                                        <td className='itemStatus'>{item.statusItem}</td>
                                        <td><img className='checkActive' alt='Check Icon' src={CheckActive} /></td>
                                        <td><img className='deleteActive' alt='Delete Icon' src={DeleteActive} /></td>
                                    </tr>
                                ))

                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                )
            </div>
        )
    }
}
