import React from 'react';
import okBtn from "../../elements/ok-btn.jpg";
import cancelBtn from "../../elements/cancel-btn.jpg";

export default class TableRequest extends React.Component{
    render(){
        return(
            <div ref={this.tableRequest} className='tableRequest'>
                <div className='tableHeader'>
                    <div className='tableTitle'>Requested Item</div>
                    <img src={okBtn} alt='Ok Button' className='confirmationBtn' onClick={()=> this.submitRequest()} />
                    <img src={cancelBtn} alt='Cancel Button' className='confirmationBtn' onClick={()=> this.requestWindowOff()} />
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
                        {this.props.data.map((item, index)=>(
                            <tr key={index}>
                                <td className='detail'>{item.detail}</td>
                                <td className='stock'>{item.stock}</td>
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