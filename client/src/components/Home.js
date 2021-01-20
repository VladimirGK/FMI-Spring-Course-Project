import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import Select from 'react-select';
import audi_logo from './audi_logo.jpg';
import {
    BrowserRouter as Router,
    Link,
    NavLink
} from "react-router-dom";

export var partsUrl = () => {}
export default class Home extends Component {

    constructor(props) {
        super(props)
        this.state = {
            baseUrl: 'http://localhost:8080/api/brand',
            brands: [],
            models: [],
            engines: [],
            brand: '',
            model: '',
            engine: ''
        }
    }

    componentDidMount() {
        this.getBrands()
    }

    async getBrands() {
        const res = await axios.get(this.state.baseUrl)
        const data = res.data
        const options = data.map(d => ({
            "value": d.id,
            "label": d.name
        }))

        this.setState({ brands: options })
    }

    async getModels(e) {
        const res = await axios.get(this.state.baseUrl + "/" + e.label + "/model");
        const data = res.data;
        const options = data.map(d => ({
            "value": d.id,
            "label": d.name
        }))
        this.setState({ models: options,  brand: e.label});
    }

    async getEngines(e) {
        const res = await axios.get(this.state.baseUrl + "/" + this.state.brand + "/model/" + e.label);
        const data = res.data;
        const options = data.map(d => ({
            "value": d.id,
            "label": d.name
        }))
        this.setState({ engines: options, model: e.label })
    }

    redirect(e) {
        this.setState({engine: e.label});
        
    }

    getAutoParts() {
        partsUrl = this.state.baseUrl + "/" + this.state.brand + "/model/" + this.state.model + "/engine/" + this.state.engine;
        console.log(partsUrl);
    }

    render() {
        return (
            <div>
                <img src={audi_logo} width="100%" height="640px"></img>
                <div style={{marginLeft:"400px", marginRight:"400px", marginTop:"20px"}} >
                    <div style={{marginTop:"10px"}}>
                        <Select options={this.state.brands} defaultValue={{ label: "Избери марка на автомобил", value: 0 }} onChange={this.getModels.bind(this)} />
                    </div>
                    <div style={{marginTop:"10px"}}>
                        <Select options={this.state.models} defaultValue={{ label: "Избери модел на автомобил", value: 0 }} onChange={this.getEngines.bind(this)} />
                    </div>
                    <div style={{marginTop:"10px"}}>
                        <Select options={this.state.engines} defaultValue={{ label: "Избери двигател на автомобил", value: 0 }} onChange={this.redirect.bind(this)} />
                    </div>
                    <div style={{marginTop:"10px"}}>
                        <NavLink onClick={this.getAutoParts.bind(this)} class="btn btn-dark" to="/autoparts">Търси авточасти</NavLink>
                    </div>
                </div>
            </div>
        )
    }
}