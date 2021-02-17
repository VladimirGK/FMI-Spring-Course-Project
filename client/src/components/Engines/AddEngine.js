import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AddEngine extends Component {
    state = {
        name: '',
        brandName: '',
        modelName: '',
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

    changeBrandName= event => {
        this.setState({ brandName: event.target.value });
    }

    changeModelName= event => {
        this.setState({ modelName: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const item = {
            name: this.state.name,
            brandName: this.state.brandName,
            modelName: this.state.modelName
        };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }

        axios.post(`http://localhost:8080/api/engine`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        return (
            <div>
                <div class="alert alert-info alert-dismissible fade show" style={{marginTop: "20px"}}>
                    <strong>Добави нов двигател за модел и марка в магазина</strong>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeName} placeholder="Име на двигателя" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="brandName" onChange={this.changeBrandName} placeholder="Име на марката" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="logoUrl" onChange={this.changeModelName} placeholder="Има на модела" />
                    </div>
                    <button type="submit" class="btn btn-secondary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Добави</button>
                </form >
            </div >
        )
    }
}