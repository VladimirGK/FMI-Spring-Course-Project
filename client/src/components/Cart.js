import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import { withRouter } from "react-router-dom";

export default class Cart extends Component {

    state = {
        autoparts: [],
        batteries: [],
        oils: [],
        supplements: [],
        firstName: '',
        lastName: '',
        city: '',
        address: '',
        number: '',
        total: '',
        userId: '',
        token: '',
        isLogged: false,
        checkout: false,
        redirect: false,
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            console.log(user)
            this.setState({ isLogged: true, userId: user.user.id, token: user.token });
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
                    this.setState({ batteries });
                })
            axios.get(`http://localhost:8080/api/cart/oil`, { headers })
                .then(res => {
                    const oils = res.data;
                    this.setState({ oils });
                })
            axios.get(`http://localhost:8080/api/cart/supplement`, { headers })
                .then(res => {
                    const supplements = res.data;
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

    changeFirstName = event => {
        this.setState({ firstName: event.target.value });
    }

    changeLastName = event => {
        this.setState({ lastName: event.target.value });
    }

    changeCity = event => {
        this.setState({ city: event.target.value });
    }

    changeAddress = event => {
        this.setState({ address: event.target.value });
    }

    changeNumber = event => {
        this.setState({ number: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const order = {
            userId: this.state.userId,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            city: this.state.city,
            address: this.state.address,
            number: this.state.number,
            oils: this.state.oils,
            batteries: this.state.batteries,
            supplements: this.state.supplements,
            autoParts: this.state.autoparts,
            total: this.state.total
        }

        const headers = {
            'Authorization': 'Bearer ' + this.state.token,
            'Content-Type': 'application/json'
        }
        console.log(headers)
        axios.post(`http://localhost:8080/api/order`, order, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
                this.setState({redirect: true});
            })
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

    checkout = () => {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({ isLogged: true });
            const headers = {
                'Authorization': 'Bearer ' + user.token,
                'Content-Type': 'application/json'
            }
            axios.get(`http://localhost:8080/api/cart/price`, { headers })
                .then(res => {
                    const total = res.data.toFixed(2);
                    this.setState({ total: total, checkout: true });
                })
        }
    }

    render() {
        if (this.state.redirect) {
            window.location.reload();
            this.props.history.push("/");
        }
        const isEmpty = this.state.autoparts.length == 0 && this.state.batteries.length == 0 && this.state.oils.length == 0 && this.state.supplements.length == 0;
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
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteAutoPartRow(item.id, e)}>Премахни</button></div>
                                <hr style={{ marginTop: "10px" }}></hr>
                            </div>
                        ))
                    }

                    {
                        this.state.batteries.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteBatteryRow(item.id, e)}>Премахни</button></div>
                                <hr style={{ marginTop: "10px" }}></hr>
                            </div>
                        ))
                    }

                    {
                        this.state.oils.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteOilRow(item.id, e)}>Премахни</button></div>
                                <hr style={{ marginTop: "10px" }}></hr>
                            </div>
                        ))
                    }

                    {
                        this.state.supplements.map((item) => (
                            <div class="row" key={item.id} style={{ marginTop: "20px" }}>
                                <div class="col-sm"><img src={item.photoUrl} width="120px" height="120px"></img></div>
                                <div class="col-sm">{item.name}</div>
                                <div class="col-sm">{item.price}лв.</div>
                                <div class="col-sm"><button type="button" class="btn btn-secondary" onClick={(e) => this.deleteSupplementRow(item.id, e)}>Премахни</button></div>
                                <hr style={{ marginTop: "10px" }}></hr>
                            </div>
                        ))
                    }
                    {!this.state.checkout && <button type="button" class="btn btn-secondary" onClick={this.checkout}>Направи поръчка</button>}
                    {this.state.checkout && <div>
                        <h5 style={{ textAlign: "right" }}>Общо: {this.state.total}лв.</h5>
                        <hr style={{ marginTop: "10px" }}></hr>

                    </div>}
                    {this.state.checkout && <div>
                        <form style={{ margin: "50px auto", width: "480px", textAlign: "center" }} class="form-inline" onSubmit={this.handleSubmit}>
                            <div style={{ marginTop: "10px" }} class="form-group">
                                <label for="firstName">Име</label>
                                <input type="text" class="form-control" id="firstName" onChange={this.changeFirstName} required/>
                            </div>
                            <div style={{ marginTop: "10px" }} class="form-group">
                                <label for="lastName">Фамилия</label>
                                <input type="text" class="form-control" id="lastName" onChange={this.changeLastName} required/>
                            </div>
                            <div style={{ marginTop: "10px" }} class="form-group">
                                <label for="city">Град</label>
                                <input type="text" class="form-control" id="city" onChange={this.changeCity} required/>
                            </div>
                            <div style={{ marginTop: "10px" }} class="form-group">
                                <label for="address">Адрес</label>
                                <input type="text" class="form-control" id="address" onChange={this.changeAddress} required/>
                            </div>
                            <div style={{ marginTop: "10px" }} class="form-group">
                                <label for="number">Телефонен номер</label>
                                <input type="text" class="form-control" id="number" onChange={this.changeNumber} required/>
                            </div>

                            <button style={{ marginTop: "10px" }} type="submit" class="btn btn-danger">Потвърди и плати</button>
                        </form>
                        <h7 style={{ textAlign: "right" }}>* Доставката е до адрес с наложен платеж</h7>
                        </div>
                    }

                </div>
            )
        }
    }
}
