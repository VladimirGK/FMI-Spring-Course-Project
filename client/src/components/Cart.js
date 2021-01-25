import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import { Redirect } from 'react-router-dom'
import {
    BrowserRouter as Router,
    Link
} from "react-router-dom";

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.user.token) {
        const token = 'Bearer ' + user.token;
        config.headers.Authorization = token;
    }

    return config;
});


export default class Cart extends Component {

    state = {
        items: [],
        isLogged: false,
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true });
            const headers = {
                'Authorization': 'Bearer ' + user.token,
                'Content-Type': 'application/json'
            }
            axios.get(`http://localhost:8080/api/cart/autopart`, { headers })
                .then(res => {
                    const items = res.data;
                    this.setState({ items });
                    console.log(res);
                    console.log(res.data);
                })
        } else {
            this.setState({ isLogged: false });
        }

    }

    deleteRow(id, e) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true });
            const headers = {
                'Authorization': 'Bearer ' + user.token,
                'Content-Type': 'application/json'
            }
            axios.delete(`http://localhost:8080/api/cart/autopart/${id}`, { headers })
                .then(res => {
                    console.log(res);
                    console.log(res.data);

                    const items = this.state.items.filter(item => item.id !== id);
                    this.setState({ items });
                })
        }
    }

    render() {
        const isEmpty = this.state.items.length == 0;
        if (isEmpty) {
            return (
                <div class="alert alert-success alert-dismissible fade show">
                    <strong>Няма намерени продукти в количката</strong>
                </div>
            )
        } else {
            return (
                <div class="container">
                    {
                        this.state.items.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.partPhoto} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteRow(item.id, e)}>Delete</button></div>
                            </div>
                        ))
                    }
                </div>
            )
        }
    }
}
