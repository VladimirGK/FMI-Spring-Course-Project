import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AddModel extends Component {
    state = {
        name: '',
        brandName: '',
        logoUrl: '',
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

    changeLogoUrl = event => {
        this.setState({ logoUrl: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const item = {
            name: this.state.name,
            brandName: this.state.brandName,
            logoUrl: this.state.logoUrl
        };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }

        axios.post(`http://localhost:8080/api/model`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        return (
            <div>
                <div class="alert alert-info alert-dismissible fade show" style={{marginTop: "20px"}}>
                    <strong>Добави нова марка кола в магазина</strong>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeName} placeholder="Име на модела" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="brandName" onChange={this.changeBrandName} placeholder="Име на марката" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="logoUrl" onChange={this.changeLogoUrl} placeholder="Снимка на модела" />
                    </div>
                    <button type="submit" class="btn btn-secondary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Добави</button>
                </form >
            </div >
        )
    }
}