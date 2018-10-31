import React, {Component} from 'react';
import SearchIcon from '../../elements/icon-search.png';
import './SearchBar.css';

export default class SearchBar extends Component {
    render() {
        return (
            <div className='searchBar'>
                <input type='text' placeholder={this.props.placeholder} className='inputSearch' />
                <img src={SearchIcon} alt='Search Icon' className='searchIcon'/>
            </div>
        )
    }
}