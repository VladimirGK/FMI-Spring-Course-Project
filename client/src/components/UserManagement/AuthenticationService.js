import axios from 'axios';

class AuthenticationService {
    signin = (username, password) => {
        return axios.post("http://localhost:8080/api/auth/signin", {username, password})
          .then(response => {
            if (response.data.token) {
              localStorage.setItem("user", JSON.stringify(response.data));
              console.log(response.data);
            }
            return response.data;
          })
          .catch(err => {
            console.log(err);
            throw err;
          });
    }
  
    signOut() {
      localStorage.removeItem("user");
    }
  
    register = async(firstName, lastName, username, email, password) => {
      return axios.post("http://localhost:8080/api/auth/signup", {
        firstName,
        lastName,
        username,
        email,
        password
      });
    }
  
    getCurrentUser() {
      console.log("GET USER: " + JSON.parse(localStorage.getItem('user')));
      return JSON.parse(localStorage.getItem('user'));
    }
  }
  
  export default new AuthenticationService();