import React from 'react';
import './TableDouble.css';
import CheckActive from '../../elements/check-active.png';
import DeleteActive from '../../elements/delete-active.png';
import SearchBar from "../SearchBar/SearchBar";

export default class TableDouble extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            activeHistory : '',

            historyData : [
                {id : 20180901001, requestDate : '1 September 2018', staff : 'Habib'},
                {id : 20180902001, requestDate : '2 September 2018', staff : 'Robin'},
                {id : 20180902002, requestDate : '2 September 2018', staff : 'Habib'},
                {id : 20180903001, requestDate : '3 September 2018', staff : 'Robin'},
            ]        
        }
    }

    componentDidMount(){
        this.setState({activeHistory: this.state.historyData[0].id})
    };

    historyChanger(activeHistory){
        this.setState({activeHistory: activeHistory})
    };

    render() {
        const dataDummy = [
            {itemName: 'Meja', qty : 1, statusItem: 'Pending'},
            {itemName: 'Kursi', qty : 1, statusItem: 'Approved'},
            {itemName: 'Printer', qty : 2, statusItem: 'Pending'},
        ];

        return(
            <div className='tableContainer'>
                <div className='tableLeft'>
                    <div className='tableHeaderDouble'>
                        <div className='tableTitleDouble'>{this.props.title}</div>
                        <div className='stylingSearch'><SearchBar placeholder={this.props.placeholder} /></div>
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
    }
}