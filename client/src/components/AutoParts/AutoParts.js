import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useParams
} from "react-router-dom";
import { partsUrl } from '../Home';

export default class AutoParts extends Component {

    state = {
        items: [],
        isLogged: false,
        token: "",
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true, token: user.token })
        }
        this.setState({})
        axios.get(partsUrl)
            .then(res => {
                const items = res.data;
                this.setState({ items: items });
                console.log(res);
                console.log(res.data);
            })
    }

    addToCart(item, e) {
        const headers = {
            'Authorization': 'Bearer ' + this.state.token,
            'Content-Type': 'application/json'
        }
        console.log(item);
        axios.post(`http://localhost:8080/api/cart/autopart`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        const isEmpty = this.state.items.length == 0;
        if (isEmpty) {
            return (
                <div class="alert alert-success alert-dismissible fade show">
                    <strong>Няма намерени авточасти за вашия автомобил</strong>
                </div>
            )
        } else {
            return (
                <div>
                    <div class="alert alert-success alert-dismissible fade show">
                        <strong>Търсените авточасти за вашия автомобил</strong>
                    </div>
                    <div class="container">
                        {
                            this.state.items.map((item) => (
                                <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                    <div class="col-sm"><img src={item.partPhoto} width="120px" height="120px"></img></div>
                                    <div class="col-sm">{item.name}</div>
                                    <div class="col-sm">{item.price}лв.</div>
                                    {this.state.isLogged && <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.addToCart(item, e)}>Добави в количка</button></div>}
                                </div>
                            ))
                        }
                    </div>
                </div>
            )
        }
    }
}
