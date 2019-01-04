import React from 'react';
import Header from "../../components/Header/Header";
import CheckActive from "../../elements/check-active.png";
import DeleteActive from "../../elements/delete-active.png";
import SearchIcon from "../../elements/icon-search.png";
import axios from "axios";

export default class ReturnItem extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            transactionData: [],
            transactionDetail: [],
            activeTransaction: '',
            shownData: []
        };
        this.actionButton = React.createRef();
    }

    componentDidMount(){
        axios.get('http://localhost:8080/api/transaction')
            .then(res => {this.setState({transactionData: res.data})})
            .then(()=> {
                let dataTmp = [];
                for(let i = 0 ; i < this.state.transactionData.length ; i++){
                    if(this.state.transactionData[i].status.toLowerCase() === 'approved'){
                        dataTmp.push(this.state.transactionData[i]);
                    }
                }
                this.setState({shownData: dataTmp})
            })
    }

    returnHandler(){
        {this.state.transactionDetail.map((item, index)=>{
            // console.log({email: this.props.email, id: this.state.activeTransaction, inventoryId: item.inventoryId, status: 'REJECTED'})
            axios.put(
                'http://localhost:8080/api/transaction/assignment',
                {email: this.props.email, id: this.state.activeTransaction, inventoryId: item.inventoryId, status: 'REJECTED'}
            )
                .then(()=> {this.componentDidMount()})
                .then(()=> {this.setState({activeTransaction: ''})})
                .then(()=> {this.setState({transactionDetail: []})})
                .then(()=> {this.actionButton.current.style.display = 'none';})
        })}
    }

    filterData(ev){
        let dataTemp = [];

        for(let i = 0 ; i < this.state.transactionData.length ; i++){
            if(this.state.transactionData[i].status.toLowerCase() === 'approved' && this.state.transactionData[i].id.toLowerCase().includes(ev.target.value.toLowerCase())){
                dataTemp.push(this.state.transactionData[i])
            } else if(this.state.transactionData[i].status.toLowerCase() === 'approved' && this.state.transactionData[i].email.toString().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.transactionData[i])
            } else if(this.state.transactionData[i].status.toLowerCase() === 'approved' && this.state.transactionData[i].status.toLowerCase().includes(ev.target.value.toLowerCase())) {
                dataTemp.push(this.state.transactionData[i])
            }
        }

        this.setState({shownData: dataTemp});
    };

    detailHandler(e){
        for(let i = 0 ; i < this.state.transactionData.length ; i++){
            if(this.state.transactionData[i].id === e.target.id){
                this.setState({transactionDetail: this.state.transactionData[i].transcData});
                this.setState({activeTransaction: e.target.id});
                this.actionButton.current.style.display = 'block';
            }
        }
        if(this.props.role === 'ADMIN'){
            this.actionButton.current.style.display = 'block';
        }
    };

    getDate(date){
        return new Date(date).getDate();
    };

    getMonth(date){
        const curr_month = new Date(date).getMonth();
        if(curr_month === 0) return 'January';
        else if(curr_month === 1) return 'February';
        else if(curr_month === 2) return 'March';
        else if(curr_month === 3) return 'April';
        else if(curr_month === 4) return 'May';
        else if(curr_month === 5) return 'June';
        else if(curr_month === 6) return 'July';
        else if(curr_month === 7) return 'August';
        else if(curr_month === 8) return 'September';
        else if(curr_month === 9) return 'October';
        else if(curr_month === 10) return 'November';
        else if(curr_month === 11) return 'December'
    };

    getYear(date){
        return new Date(date).getFullYear();
    };

    render(){
        document.title = "Return Item | Blibli Inventory System";

        return(
            <div>
                <Header pageName="Return Item" name={this.props.name}/>

                <div className='tableContainer'>
                    <div className='tableLeft'>
                        <div className='tableHeaderDouble'>
                            <div className='tableTitleDouble'>Accepted Request</div>
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
                                    <th className='assignment'>Transaction ID</th>
                                    <th className='requestDate'>Request Date</th>
                                    <th className='staff'>Requested By</th>
                                </tr>
                                </thead>

                                <tbody>
                                {this.state.shownData.map((item, index)=>(
                                    <tr key={index} id={item.id} onClick={this.detailHandler.bind(this)}>
                                        <td id={item.id} className='assignment'>{item.id}</td>
                                        <td id={item.id} className='requestDate'>{this.getDate(item.createdAt)} {this.getMonth(item.createdAt)} {this.getYear(item.createdAt)}</td>
                                        <td id={item.id} className='staff'>{item.email}</td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div className='tableRight'>
                        <div className='tableHeaderDouble' style={{padding:'33px 16px'}}>
                            <div style={{fontSize:'1.2em'}} className='tableTitleDouble'>{this.state.activeTransaction}</div>
                        </div>

                        <div className='tableBodyDouble'>
                            <table>
                                <thead>
                                <tr>
                                    <th className='itemName'>Item</th>
                                    <th className='itemQty'>Qty</th>
                                </tr>
                                </thead>

                                <tbody>
                                {this.state.transactionDetail.map((item, index)=>(
                                    <tr key={index}>
                                        <td className='itemName'>{item.inventoryId}</td>
                                        <td className='itemQty'>{item.qty}</td>
                                    </tr>
                                ))}
                                <tr ref={this.actionButton} style={{display:'none'}}>
                                    <td onClick={this.returnHandler.bind(this)} style={{width:'100%', cursor:'pointer'}}><img className='checkActive' alt='Check Icon' src={CheckActive} /> Return All Item</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
