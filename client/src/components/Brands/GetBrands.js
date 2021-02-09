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
    }

    componentDidMount() {
        axios.get(`http://localhost:8080/api/brand`)
            .then(res => {
                const items = res.data;
                this.setState({ items });
                console.log(res);
                console.log(res.data);
            })
    }

    deleteRow(id, e) {
        axios.delete(`http://localhost:8080/api/brand/${id}`)
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
                        <strong>Няма намерени марки</strong>
                    </div>
            )
        } else {
            return (
                    <div class="container">
                        {
                            this.state.items.map((item) => (
                                <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                    <div class="col-sm"><img src={item.logoUrl} width="120px" height="120px"></img></div>
                                    <div class="col-sm">{item.name}</div>
                                    <div class="col-sm"><button type="button" class="btn btn-primary" onClick={(e) => this.deleteRow(item.id, e)}>Delete</button></div>
                                </div>
                            ))
                        }
                    </div>
            )
        }
    }
}