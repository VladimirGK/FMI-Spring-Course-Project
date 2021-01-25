import React from "react";
import { Component } from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    NavLink,
    Link
} from "react-router-dom";
import GetOils from "./Oils/GetOils";
import GetBatteries from './Batteries/GetBatteries';
import GetSupplements from './Supplements/GetSupplements';
import Admin from './Admin'
import Home from './Home'
import AutoParts from './AutoParts/AutoParts'
import Register from "./UserManagement/Register";
import Login from "./UserManagement/Login";
import Cart from './Cart'
import AuthenticationService from './UserManagement/AuthenticationService'
import cart from '../cart.png'


class Navbar extends Component {

    constructor(props) {
        super(props);
        this.state = {isOpen: false};

        this.state = {
          showUser: false,
          showAdmin: false,
          username: undefined,
          login: false
        };
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

      signOut = () => {
        AuthenticationService.signOut();
        window.location.reload();
      }

    render() {
        return (
            <div>
              <Router>
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="/">Autodeli</a>
                        <div class="collapse navbar-collapse" id="navbarText">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <Link class="nav-link" a to="/oils">Масла</Link>
                                </li>
                                <li class="nav-item">
                                    <Link class="nav-link" a to="/batteries">Акумолатори</Link>
                                </li>
                                <li class="nav-item">
                                    <Link class="nav-link" a to="/supplements">Добавки</Link>
                                </li>
                                <li class="nav-item">
                                    {this.state.showAdmin && <Link class="nav-link" a to="/admin">Админ конзола</Link>}
                                </li>
                            </ul>
                            <div>
                                {!this.state.login && <Link class="btn btn-light" style={{ marginRight: "10px" }} a to="/register">Регистрация</Link>}
                                {!this.state.login && <Link class="btn btn-light" style={{ marginRight: "10px" }} a to="/login">Вход</Link>}
                                {this.state.login && <Link class="btn btn-light"  style={{ marginRight: "10px" }} a to="/"> Добре дошли, {this.state.username}</Link>}
                                {this.state.login && <Link a to="/cart"><img src={cart} width="40px" height="40"/></Link>}
                                {this.state.login && <Link class="btn btn-light" onClick={this.signOut} style={{ marginRight: "10px" }} a to="/">Изход</Link>}
                            </div>
                        </div>
                    </div>
                </nav>
                    <Switch>
                        <Route path="/login">
                            <Login />
                        </Route>
                        <Route path="/register">
                            <Register />
                        </Route>
                        <Route path="/oils">
                            <GetOils />
                        </Route>
                        <Route path="/batteries">
                            <GetBatteries />
                        </Route>
                        <Route path="/supplements">
                            <GetSupplements />
                        </Route>
                        <Route path="/admin">
                            <Admin />
                        </Route>
                        <Route path="/autoparts">
                            <AutoParts/>
                        </Route>
                        <Route path="/cart">
                            <Cart/>
                        </Route>
                        <Route path="/">
                            <Home/>
                        </Route>
                        
                    </Switch>
                    </Router>

            </div>

        )
    }
}

export default Navbar;