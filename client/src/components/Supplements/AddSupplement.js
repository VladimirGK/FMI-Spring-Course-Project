import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AddSuplement extends Component {
    state = {
        name: '',
        brand: '',
        price: '',
        photoUrl: '',
        token: ''
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({
                token: user.token
            });
        }
    }

    changeName = event => {
        this.setState({ name: event.target.value });
    }
    changeBrand = event => {
        this.setState({ brand: event.target.value });
    }
    changePrice = event => {
        this.setState({ price: event.target.value });
    }
    changePhotoUrl = event => {
        this.setState({ photoUrl: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const item = {
            name: this.state.name,
            brand: this.state.brand,
            price: this.state.price,
            photoUrl: this.state.photoUrl
        };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }

        axios.post(`http://localhost:8080/api/supplement`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        return (
            <div>
                <div class="alert alert-info alert-dismissible fade show" style={{marginTop: "20px"}}>
                    <strong>Добави нова добавка в магазина</strong>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeName} placeholder="Име на продукта" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="brand" onChange={this.changeBrand} placeholder="Марка на продукта" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="price" onChange={this.changePrice} placeholder="Цена на продукта" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="photoUrl" onChange={this.changePhotoUrl} placeholder="Снимка на продукта" />
                    </div>
                    <button type="submit" class="btn btn-primary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Добави</button>
                </form >
            </div >
        )
    }
}