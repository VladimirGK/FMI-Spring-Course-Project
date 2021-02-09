import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

export default class AllOils extends Component {

    state = {
        items: [],
        isLogged: false,
        showAdmin: "",
        token: "",
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            const roles = [];
            user.user.authorities.forEach(authority => {
                roles.push(authority.authority);
            });

            this.setState({
                showAdmin: roles.includes("ROLE_ADMIN"),
                isLogged: true,
                token: user.token
            });
        }
        axios.get(`http://localhost:8080/api/oil`)
            .then(res => {
                const items = res.data;
                this.setState({ items });
                console.log(res);
                console.log(res.data);
            })
    }

    addToCart(item, e) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token
        }
        console.log(item);
        axios.post(`http://localhost:8080/api/cart/oil`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    deleteRow(id, e) {
        const headers = {
            'Authorization': 'Bearer ' + this.state.token,
            'Content-Type': 'application/json'
        }
        axios.delete(`http://localhost:8080/api/oil/${id}`, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);

                const items = this.state.items.filter(item => item.id !== id);
                this.setState({ items });
            })
    }

    render() {
        const isEmpty = this.state.items.length == 0;
        if (isEmpty) {
            return (
                    <div class="alert alert-success alert-dismissible fade show">
                        <strong>Няма намерени добавки</strong>
                    </div>
            )
        } else {
            return (
                    <div class="container">
                        {
                            this.state.items.map((item) => (
                                <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                    <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                    <div class="col-sm">{item.name}</div>
                                    <div class="col-sm">{item.price}лв.</div>
                                    {this.state.isLogged && <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.addToCart(item, e)}>Добави в количка</button></div>}
                                    {this.state.showAdmin && <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteRow(item.id, e)}>Delete</button></div>}
                                    {this.state.showAdmin && <div class="col-sm"><Link to="/admin" className="btn btn-secondary">Edit</Link></div>}
                                </div>
                            ))
                        }
                    </div>
            )
        }
    }
}
