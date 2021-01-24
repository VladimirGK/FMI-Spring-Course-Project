import React, { Component } from 'react';
import { Container } from 'reactstrap';
import { Form, Alert, FormGroup, Input, Row, Col } from "reactstrap";
import { Button } from 'react-bootstrap';
import AuthenticationService from "./AuthenticationService";
import { withRouter } from "react-router-dom";



class Login extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            error: "",
            redirect: false,
        };
    }

    changeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({ [nam]: val });
    }

    doLogin = async (event) => {

        event.preventDefault();

        AuthenticationService
            .signin(this.state.username,
                this.state.password)
            .then(
                () => {
                    this.setState({ redirect: true });
                },
                error => {
                    console.log("Login fail: error = { " + error.toString() + " }");
                    this.setState({ error: "Can not signin successfully ! Please check username/password again" });
                },
            );
    }

    render() {
        const title = <h2>Влез в профила си!</h2>;
        if (this.state.redirect) {
            window.location.reload();
            this.props.history.push("/");
        }

        return (
            <div>
                <Container fluid>
                    <Row style={{ marginTop: "20px" }}>
                        <Col sm="12" md={{ size: 4, offset: 4 }}>
                            {title}
                            <Form onSubmit={this.doLogin}>
                                <FormGroup>
                                    <Input autoFocus style={{ marginTop: "10px" }}
                                        type="text"
                                        name="username" id="username"
                                        value={this.state.username}
                                        placeholder="Enter Username"
                                        autoComplete="username"
                                        onChange={this.changeHandler}
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Input type="password" style={{ marginTop: "10px" }}
                                        name="password" id="password"
                                        value={this.state.password}
                                        placeholder="Enter Password"
                                        autoComplete="password"
                                        onChange={this.changeHandler}
                                    />
                                </FormGroup>
                                <Button variant="secondary" style={{ marginTop: "10px" }} type="submit" >
                                    Влез
                                </Button>
                                {
                                    this.state.error && (
                                        <Alert color="danger">
                                            {this.state.error}
                                        </Alert>
                                    )
                                }
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </div>);
    }
}

export default withRouter(Login);
