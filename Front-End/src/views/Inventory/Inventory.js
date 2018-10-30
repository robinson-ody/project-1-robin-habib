import React from 'react';
import Header from '../../components/Header/Header'
import TableSingle from "../../components/TableSingle/TableSingle";

export default class Inventory extends React.Component{
	render(){
		return(
			<div>
				<Header pageName="Inventory" />
                <TableSingle title='Inventory List' subTitle='item' />
			</div>
		)
	}
}