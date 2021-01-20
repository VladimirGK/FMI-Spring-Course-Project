import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import { partsUrl } from './Home';

export default class AutoParts extends Component {

    state = {
        items: [],
    }

    componentDidMount() {
        console.log("URL IS " + partsUrl);
        axios.get(partsUrl)
            .then(res => {
                const items = res.data;
                this.setState({ items });
                console.log(res);
                console.log(res.data);
            })
    }

    deleteRow(id, e) {
        axios.delete(`http://localhost:8080/api/autopart/${id}`)
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
                                    <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                    <div class="col-sm">{item.name}</div>
                                    <div class="col-sm">{item.price}</div>
                                    <div class="col-sm"><button type="button" class="btn btn-primary" onClick={(e) => this.deleteRow(item.id, e)}>Delete</button></div>
                                    <div class="col-sm"><Link to="/admin" className="btn btn-primary">Edit</Link></div>
                                </div>
                            ))
                        }
                    </div>
                </div>
            )
        }
    }
}
