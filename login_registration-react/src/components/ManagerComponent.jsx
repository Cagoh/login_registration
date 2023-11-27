import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { loginAPICall, saveLoggedInUser, storeToken, isUserLoggedIn, ManagerAPICall } from '../services/AuthService';


const ManagerComponent = () => {

    const [todos, setTodos] = useState([])

    const [responseData, setResponseData] = useState('')

    const navigate = useNavigate()


    useEffect(() => {



            // Check whether the user have manager role to allow access
            // sending token in authorization is the best practise

            // get the session user
            const managerApiBody = {
                "authenticatedUser": sessionStorage.getItem("authenticatedUser")
            }

            const managerApiHeader = {
                "token": localStorage.getItem("token")
            }

            ManagerAPICall(managerApiBody, managerApiHeader).then
            (
                (response) => {
                    setResponseData(response.data)
                }
            ).catch(error => {
                setResponseData("402 UNAUTHORISED")
            })
        // }



    }, [navigate])


    return (
        <div className="manager-content">

        {responseData && (
            <div dangerouslySetInnerHTML={{ __html: responseData }} />
        )}
        </div>
    );
}

export default ManagerComponent