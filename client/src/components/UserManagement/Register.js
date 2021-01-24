import Authentication from './AuthenticationService'
import React from 'react';
import { Component } from 'react';
import { Container } from 'reactstrap';
import { Button, Form, FormGroup, Input, Label, Row, Col } from "reactstrap";
import { Alert } from "react-bootstrap"
import { Link } from "react-router-dom";


const validEmailRegex = RegExp(/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);

const validateForm = (errors) => {
  let valid = true;
  Object.values(errors).forEach(
    (val) => val.length > 0 && (valid = false)
  );
  return valid;
}

class Register extends Component {

  constructor(props) {
    super(props);
    this.state = {
      firstName: "",
      lastName: "",
      username: "",
      email: "",
      password: "",
      message: "",
      successful: false,
      validForm: true,
      errors: {
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        password: ''
      }
    };
  }

  changeHandler = (event) => {
    const { name, value } = event.target;

    let errors = this.state.errors;

    switch (name) {
      case 'firstName':
        errors.firstName =
          value.length < 3
            ? 'firstName must be 3 characters long!'
            : '';
        break;
      case 'lastName':
        errors.lastName =
          value.length < 3
            ? 'lastName must be 3 characters long!'
            : '';
        break;
      case 'username':
        errors.username =
          value.length < 5
            ? 'Username must be 5 characters long!'
            : '';
        break;
      case 'email':
        errors.email =
          validEmailRegex.test(value)
            ? ''
            : 'Email is not valid!';
        break;
      case 'password':
        errors.password =
          value.length < 8
            ? 'Password must be 8 characters long!'
            : '';
        break;
      default:
        break;
    }

    this.setState({ errors, [name]: value }, () => {
    })
  }

  signUp = (e) => {
    e.preventDefault();
    const valid = validateForm(this.state.errors);
    this.setState({ validForm: valid });
    if (valid) {
      console.log("VALID")
      Authentication.register(
        this.state.firstName,
        this.state.lastName,
        this.state.username,
        this.state.email,
        this.state.password
      ).then(
        response => {
          console.log("here");
          this.setState({
            message: response.data.message,
            successful: true
          });
        },
        error => {
          console.log("Fail! Error = " + error.toString());

          this.setState({
            successful: false,
            message: error.toString()
          });
        }
      );
    }
  }

  render() {
    const title = <h2>Регистрирай се!</h2>;
    const errors = this.state.errors;

    let alert = "";
    console.log(this.state.message);
    console.log(this.state.successful);
    if (this.state.successful) {
      alert = (
        <Alert style={{marginTop:"10px"}} variant="success">
          Успешно се регистрирахте
        </Alert>
      );
    } else if(this.state.message) {
      alert = (
        <Alert style={{marginTop:"10px"}} variant="danger">
          {this.state.message}
        </Alert>
      );
    }

    return (
      <div>
        <Container fluid>
          <Row>
            <Col sm="12" md={{ size: 4, offset: 4 }}>
              {title}
              <Form onSubmit={this.signUp}>
                <FormGroup controlId="forfirstName">
                  <Input required style={{ marginTop: "10px" }}
                    type="text"
                    placeholder="Enter First Name"
                    name="firstName" id="firstName"
                    value={this.state.firstName}
                    autoComplete="firstName"
                    onChange={this.changeHandler}
                  />
                  {
                    errors.firstName && (
                      <Alert variant="danger">
                        {errors.firstName}
                      </Alert>
                    )
                  }
                </FormGroup>

                <FormGroup controlId="forlastName">
                  <Input required style={{ marginTop: "10px" }}
                    type="text"
                    placeholder="Enter Last Name"
                    name="lastName" id="lastName"
                    value={this.state.lastName}
                    autoComplete="lastName"
                    onChange={this.changeHandler}
                  />
                  {
                    errors.lastName && (
                      <Alert variant="danger">
                        {errors.firstName}
                      </Alert>
                    )
                  }
                </FormGroup>

                <FormGroup controlId="forUsername">
                  <Input required style={{ marginTop: "10px" }}
                    type="text"
                    placeholder="Enter UserName"
                    name="username" id="username"
                    value={this.state.username}
                    autoComplete="username"
                    onChange={this.changeHandler}
                  />
                  {
                    errors.username && (
                      <Alert variant="danger">
                        {errors.username}
                      </Alert>
                    )
                  }
                </FormGroup>

                <FormGroup controlId="formEmail">
                  <Input required style={{ marginTop: "10px" }}
                    type="text"
                    placeholder="Enter Email"
                    name="email" id="email"
                    value={this.state.email}
                    autoComplete="email"
                    onChange={this.changeHandler}
                  />
                  {
                    errors.email && (
                      <Alert variant="danger">
                        {errors.email}
                      </Alert>
                    )
                  }
                </FormGroup>

                <FormGroup controlId="formPassword">
                  <Input required style={{ marginTop: "10px" }}
                    type="password"
                    placeholder="Enter Password"
                    name="password" id="password"
                    value={this.state.password}
                    autoComplete="password"
                    onChange={this.changeHandler}
                  />
                  {
                    errors.password && (
                      <Alert key="errorspassword" variant="danger">
                        {errors.password}
                      </Alert>
                    )
                  }
                </FormGroup>
                <Button class="btn btn-dark" type="submit" style={{ marginTop: "10px" }}>
                  Създай профил
              </Button>
                {
                  !this.state.validForm && (
                    <Alert key="validForm" variant="danger">
                      Please check the inputs again!
                    </Alert>
                  )
                }
                {alert}
              </Form>
            </Col>
          </Row>
        </Container>
      </div>);
  }
}

export default Register;