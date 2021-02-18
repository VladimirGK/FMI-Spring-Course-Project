import React from 'react';
import axios from 'axios';
import { Component } from 'react';

export default class ProfilePage extends Component {
    state = {
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        password: '',
        token: '',
        id: ''
    }

    componentDidMount() {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            this.setState({
                token: user.token,
                firstName: user.user.firstName,
                lastName: user.user.lastName,
                username: user.user.username,
                email: user.user.email,
                id: user.user.id,
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

    updateRow(id, e) {
        const item = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            username: this.state.username,
            password: this.state.password,
        };
        const headers = {
            "Accept": "application/json",
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.state.token,
        }
        console.log(item);
        console.log(headers);
        axios.put(`http://localhost:8080/api/user/${id}`, {item}, {headers} )
            .then(res => {
                console.log(res);
                console.log(res.data);
            })

    }

    render() {
        return (
            <div>
                <div class="row" style={{ marginTop: "20px" }}>
                                    <div class="col-sm">{this.state.firstName}</div>
                                    <div class="col-sm">{this.state.lastName}</div>
                                    <div class="col-sm">{this.state.username}</div>
                                    <div class="col-sm">{this.state.email}</div>
                </div>
                <form >
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
                    <button onClick={(e) => this.updateRow(this.state.id, e)} class="btn btn-secondary" style={{marginTop:"20px", marginLeft: "400px", marginRight: "400px"}}>Промени</button>
                </form >
            </div >
        )
    }
}