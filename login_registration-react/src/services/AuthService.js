import axios from "axios";

const AUTH_REST_API_BASE_URL = "http://localhost:8082/api/auth"

export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL + '/register', registerObj);

export const loginAPICall = (login) => {
    return axios.post(AUTH_REST_API_BASE_URL + '/login', login);
}

export const storeToken = (token) => localStorage.setItem("token", token);

export const getName = () => localStorage.getName("name");
export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username) => sessionStorage.setItem("authenticatedUser", username);

export const isUserLoggedIn = () => {

    const username = sessionStorage.getItem("authenticatedUser");

    if(username == null) {
        console.log("isUserLoggedIn = false")
        return false;
    }    
    else {
        console.log("isUserLoggedIn = true")
        return true;
    }   
}

export const isUserManager = () => {


}

export const getLoggedInUser = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username;
}

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
}

export const ManagerAPICall = (managerApiBody, managerApiHeader) => {
    return axios.post(AUTH_REST_API_BASE_URL + '/manager', managerApiBody, {
        headers: managerApiHeader
    });
}

export const WelcomePageAPICall = (welcomeApiBody, welcomeApiHeader) => {
    return axios.post(AUTH_REST_API_BASE_URL + '/welcome', welcomeApiBody, {
        headers: welcomeApiHeader
    });
}
