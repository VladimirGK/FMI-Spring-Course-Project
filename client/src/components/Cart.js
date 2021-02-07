import React from 'react';
import axios from 'axios';
import { Component } from 'react';

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
        autoparts: [],
        batteries: [],
        oils: [],
        supplements: [],
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
                    const autoparts = res.data;
                    this.setState({ autoparts });
                })
            axios.get(`http://localhost:8080/api/cart/battery`, { headers })
                .then(res => {
                    const batteries = res.data;
                    this.setState({ batteries});
                })
            axios.get(`http://localhost:8080/api/cart/oil`, { headers })
                .then(res => {
                    const oils = res.data;
                    this.setState({ oils });
                })
            axios.get(`http://localhost:8080/api/cart/supplement`, { headers })
                .then(res => {
                    const supplements= res.data;
                    this.setState({ supplements });
                    console.log(res);
                    console.log(res.data);
                })
        } else {
            this.setState({ isLogged: false });
        }

    }

    deleteAutoPartRow(id, e) {
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

                    const autoparts = this.state.autoparts.filter(item => item.id !== id);
                    this.setState({ autoparts });
                })
        }
    }

    deleteBatteryRow(id, e) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true });
            const headers = {
                'Authorization': 'Bearer ' + user.token,
                'Content-Type': 'application/json'
            }
            axios.delete(`http://localhost:8080/api/cart/battery/${id}`, { headers })
                .then(res => {
                    console.log(res);
                    console.log(res.data);

                    const batteries = this.state.batteries.filter(item => item.id !== id);
                    this.setState({ batteries });
                })
        }
    }

    deleteOilRow(id, e) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true });
            const headers = {
                'Authorization': 'Bearer ' + user.token,
                'Content-Type': 'application/json'
            }
            axios.delete(`http://localhost:8080/api/cart/oil/${id}`, { headers })
                .then(res => {
                    console.log(res);
                    console.log(res.data);

                    const oils = this.state.oils.filter(item => item.id !== id);
                    this.setState({ oils });
                })
        }
    }

    deleteSupplementRow(id, e) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true });
            const headers = {
                'Authorization': 'Bearer ' + user.token,
                'Content-Type': 'application/json'
            }
            axios.delete(`http://localhost:8080/api/cart/supplement/${id}`, { headers })
                .then(res => {
                    console.log(res);
                    console.log(res.data);

                    const supplements = this.state.supplements.filter(item => item.id !== id);
                    this.setState({ supplements });
                })
        }
    }

    render() {
        const isEmpty = this.state.autoparts.length == 0;
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
                        this.state.autoparts.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.partPhoto} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteAutoPartRow(item.id, e)}>Delete</button></div>
                            </div>
                        ))
                    }

                    {
                        this.state.batteries.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteBatteryRow(item.id, e)}>Delete</button></div>
                            </div>
                        ))
                    }

                    {
                        this.state.oils.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteOilRow(item.id, e)}>Delete</button></div>
                            </div>
                        ))
                    }

                    {
                        this.state.supplements.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteSupplementRow(item.id, e)}>Delete</button></div>
                            </div>
                        ))
                    }
                </div>
            )
        }
    }
}
