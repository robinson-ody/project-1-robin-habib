import React from 'react';

export default class Print extends React.Component {
    render(){
        return (
            <div>
                Print Content {this.props.printItem}
            </div>
        )
    }
}