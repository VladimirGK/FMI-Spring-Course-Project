import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AddAutoPart extends Component {
    state = {
        name: '',
        partPhoto: '',
        brandName: '',
        modelName: '',
        engineName: '',
        price: '',
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

    changeBrandName = event => {
        this.setState({ brandName: event.target.value });
    }

    changeModelName = event => {
        this.setState({ modelName: event.target.value });
    }

    changeEngineName = event => {
        this.setState({ engineName: event.target.value });
    }

    changePrice = event => {
        this.setState({ price: event.target.value });
    }

    changeLogoUrl = event => {
        this.setState({ partPhoto: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const item = {
            name: this.state.name,
            brandName: this.state.brandName,
            modelName: this.state.modelName,
            engineName: this.state.engineName,
            price: this.state.price,
            partPhoto: this.state.partPhoto
        };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }

        axios.post(`http://localhost:8080/api/autopart`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        return (
            <div>
                <div class="alert alert-info alert-dismissible fade show" style={{marginTop: "20px"}}>
                    <strong>Добави нова авточаст в магазина</strong>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeName} placeholder="Име на авточаст" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeBrandName} placeholder="Име на марка" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeModelName} placeholder="Име на модел" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeEngineName} placeholder="Име на двигател" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changePrice} placeholder="Цена" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="partPhoto" onChange={this.changeLogoUrl} placeholder="Снимка на продукта" />
                    </div>
                    <button type="submit" class="btn btn-secondary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Добави</button>
                </form >
            </div >
        )
    }
}