import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class AddUser extends Component {
    state = {
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        password: '',
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

    changeFirstName = event => {
        this.setState({ firstName: event.target.value });
    }

    changeLastName= event => {
        this.setState({ lastName: event.target.value });
    }

    changeEmail = event => {
        this.setState({ email: event.target.value });
    }

    changeUsername = event => {
        this.setState({ username: event.target.value });
    }

    changePassword = event => {
        this.setState({ password: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const item = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            username: this.state.username,
            password: this.state.password,
        };
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }

        axios.post(`http://localhost:8080/api/user`, item, { headers })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        return (
            <div>
                <div class="alert alert-info alert-dismissible fade show" style={{marginTop: "20px"}}>
                    <strong>Добави нов потребител в магазина</strong>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="name" onChange={this.changeFirstName} placeholder="Име" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="brandName" onChange={this.changeLastName} placeholder="Фалимия" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="logoUrl" onChange={this.changeUsername} placeholder="Username" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="logoUrl" onChange={this.changePassword} placeholder="Парола" />
                    </div>
                    <div class="form-group" style={{marginTop: "20px", marginLeft: "400px", marginRight: "400px"}}>
                        <input class="form-control" type="text" name="logoUrl" onChange={this.changeEmail} placeholder="Емайл" />
                    </div>
                    <button type="submit" class="btn btn-secondary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Добави</button>
                </form >
            </div >
        )
    }
}