import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { loginAPICall, saveLoggedInUser, storeToken, isUserLoggedIn, ManagerAPICall, WelcomePageAPICall } from '../services/AuthService';

import { useTranslation } from 'react-i18next';

const ListTodoComponent = () => {

    const { t } = useTranslation();

    const [todos, setTodos] = useState([])
    const [responseData, setResponseData] = useState([])

    const navigate = useNavigate()


    useEffect(() => {

        // get the session user
        const welcomeApiBody = {
            "authenticatedUser": sessionStorage.getItem("authenticatedUser")
        }

        const welcomeApiHeader = {
            "token": localStorage.getItem("token")
        }

        // if (isUserLoggedIn()) {
            WelcomePageAPICall(welcomeApiBody, welcomeApiHeader).then
            (
                (response) => {
                    setResponseData(response.data)
                    // Set data in localStorage
                    localStorage.setItem('response', response.data);
                }
            ).catch(error => {
                console.error(error)
            })
        // }

    }, [])

    return (
        <div className="welcome-content">
            <div>Welcome</div>
            <div>Name: 
                {responseData[0]}
            </div>
            <div>User Name: 
                {responseData[1]}
            </div>
            <div>Roles:<br></br>
            {responseData.slice(2).map((item, index) => (
            <React.Fragment key={index}>
                {item}
                <br />
            </React.Fragment>
            ))}
        </div>


        </div>

        )
}

export default ListTodoComponent