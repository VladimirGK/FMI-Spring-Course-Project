import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AllModels extends Component {

    state = {
        items: [],
        token: ''
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({
                token: user.token
            });
        }
        
        axios.get(`http://localhost:8080/api/model`)
            .then(res => {
                const items = res.data;
                this.setState({ items });
                console.log(res);
                console.log(res.data);
            })
    }

    deleteRow(id, e) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }
        console.log(headers)
        axios.delete(`http://localhost:8080/api/model/${id}`, { headers })
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
                        <strong>Няма намерени модели</strong>
                    </div>
            )
        } else {
            return (
                    <div class="container">
                        {
                            this.state.items.map((item) => (
                                <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                    <div class="col-sm"><img src={item.logoUrl} width="120px" height="120px"></img></div>
                                    <div class="col-sm">{item.brandName}</div>
                                    <div class="col-sm">{item.name}</div>
                                    <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteRow(item.id, e)}>Delete</button></div>
                                    <hr style={{ marginTop: "10px" }}></hr>
                                </div>
                            ))
                        }
                    </div>
            )
        }
    }
}
