import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import AllOils from "./Oils/AllOils";
import Admin from './Admin'
import Home from './Home'
import AutoParts from './AutoParts'

export default function App() {
    return (
        <Router>
        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Autodeli</a>
                    <div class="collapse navbar-collapse" id="navbarText">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <Link class="nav-link" a to="/oils">Масла</Link>
                            </li>
                            <li class="nav-item">
                                <Link class="nav-link" a to="/about">Акумолатори</Link>
                            </li>
                            <li class="nav-item">
                                <Link class="nav-link" a to="/users">Добавки</Link>
                            </li>
                            <li class="nav-item">
                                <Link class="nav-link" a to="/admin">Админ конзола</Link>
                            </li>
                        </ul>
                        <div>
                            <button type="button" class="btn btn-light" style={{marginRight: "10px"}}>Login</button>
                            <button type="button" class="btn btn-light">Register</button>
                        </div>
                    </div>
                </div>
            </nav>
            <Switch>
                <Route path="/oils">
                    <AllOils/>
                </Route>
                <Route path="/batteries">
                    <Users />
                </Route>
                <Route path="/batteries">
                    <Users />
                </Route>
                <Route path="/supplements">
                    <Users />
                </Route>
                <Route path="/admin">
                    <Admin/>
                </Route>
                <Route path="/autoparts">
                    <AutoParts/>
                </Route>
                <Route path="/">
                    <Home />
                </Route>
            </Switch>
        </div>
        </Router>
    )
}
  
  function Users() {
    return <h2>Users</h2>;
  }
