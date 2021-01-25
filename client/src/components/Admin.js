import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import AddOil from './Oils/AddOil';
import GetOils from './Oils/GetOils';
import AddSuplement from './Supplements/AddSupplement';
import GetSupplements from './Supplements/GetSupplements';
import AddBattery from './Batteries/AddBattery';
import GetBatteries from './Batteries/GetBatteries';
import AddBrand from './Brands/AddBrand'
import GetBrands from './Brands/GetBrands'
import AuthenticationService from './UserManagement/AuthenticationService'

export default class Admin extends Component {
    constructor(props) {
        super(props)
        this.state = {
            action: ''
        }
    }
    componentDidMount() {
        const user = AuthenticationService.getCurrentUser();
        if (user) {
            const roles = [];

            user.user.authorities.forEach(authority => {
                roles.push(authority.authority);
            });
            this.setState({
                showUser: true,
                showAdmin: roles.includes("ROLE_ADMIN"),
                login: true,
                username: user.user.username
            });
        }
    }
    showAction(action) {
        console.log(action)
        switch (action) {
            case "addOil":
                return <AddOil />;
            case "addSupplement":
                return <AddSuplement />
            case "addBattery":
                return <AddBattery />
            case "addBrand":
                return <AddBrand />
            case "getOils":
                return <GetOils />
            case "getSupplements":
                return <GetSupplements />
            case "getBatteries":
                return <GetBatteries />
            case "getBrands":
                return <GetBrands />
        }
    }
    changeAction(e) {
        this.setState({ action: e });
    }

    render() {
        if (!this.showAdmin) {
            return (
                <div>
                    <div class="alert alert-danger alert-dismissible fade show">
                    <strong>Вие не сте админ!</strong>
                </div>
                </div>
            )
        } else {
            return (
                <div>
                    <div>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addOil")} type="button">Добави масло</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addBattery")} type="button">Добави акумолатор</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addSupplement")} type="button">Добави добавка</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addBrand")} type="button">Добави марка</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addModel")} type="button">Добави модел</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addEngine")} type="button">Добави двигател</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addAutoPart")} type="button">Добави авточаст</button>
                    </div>
                    <div>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getOils")} type="button">Изведи масла</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getBatteries")} type="button">Изведи акумолатори</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getSupplements")} type="button">Изведи добавки</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getBrands")} type="button">Изведи марки</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getModels")} type="button">Изведи модели</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getEngines")} type="button">Изведи двигатели</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getAutoParts")} type="button">Изведи авточасти</button>
                    </div>
                    <div>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("addUser")} type="button">Добави потребител</button>
                        <button style={{ margin: "10px" }} class="btn btn-secondary" onClick={(e) => this.changeAction("getUsers")} type="button">Изведи потребители</button>
                    </div>
                    <div style={{ marginTop: "20px" }}>
                        {this.showAction(this.state.action)}
                    </div>
                </div>
            )
        }
    }
}