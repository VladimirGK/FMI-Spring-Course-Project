import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AddOil extends Component {
    state = {
        name: '',
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

    changeLogoUrl = event => {
        this.setState({ logoUrl: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const item = {
            name: this.state.name,
            logoUrl: this.state.logoUrl
        };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }

        axios.post(`http://localhost:8080/api/brand`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
            .catch((reason: AxiosError) => {
                if (reason.res.status === 400) {
                } else {
                }
                console.log(reason.message)
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
                        <input class="form-control" type="text" name="name" onChange={this.changeName} placeholder="Име на продукта" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="logoUrl" onChange={this.changeLogoUrl} placeholder="Снимка на продукта" />
                    </div>
                    <button type="submit" class="btn btn-primary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Добави</button>
                </form >
            </div >
        )
    }
}