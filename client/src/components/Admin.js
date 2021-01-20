import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import AddOil from './Oils/AddOil';

export default class Admin extends Component {
    constructor(props) {
        super(props)
        this.state = {
            action: ''
        }
    }
    showAction(action) {
        console.log(action)
        switch(action) {
            case "addOil":
                return <AddOil/>;
        }
    }
    changeAction(e) {
        this.setState({ action: e });
    }

    render() {
        return (
            <div>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addOil")} type="button">Добави масло</button>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addBattery")} type="button">Добави акумолатор</button>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addSupplement")} type="button">Добави добавка</button>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addBrand")} type="button">Добави марка</button>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addModel")} type="button">Добави модел</button>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addEngine")} type="button">Добави двигател</button>
                <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("addAutoPart")} type="button">Добави авточаст</button>
                <div>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getOils")} type="button">Изведи масла</button>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getBatteries")} type="button">Изведи акумолатори</button>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getSupplements")} type="button">Изведи добавки</button>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getBrands")} type="button">Изведи марки</button>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getModels")} type="button">Изведи модели</button>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getEngines")} type="button">Изведи двигатели</button>
                    <button style={{margin:"10px"}} class="btn btn-secondary" onClick={(e) => this.changeAction("getAutoParts")} type="button">Изведи авточасти</button>

                </div>
                <div style={{marginTop:"20px"}}>
                    {this.showAction(this.state.action)}
                </div>
            </div>
        )
    }
}