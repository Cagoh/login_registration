import React, { useState, useEffect } from 'react'
import { registerAPICall, isUserLoggedIn } from '../services/AuthService'
import { BrowserRouter, Routes, Route, Navigate, useNavigate} from 'react-router-dom'

import cagohLogo from '../assets/images/107544628.svg'
import cagohLogoBackground from '../assets/images/v2osk-1Z2niiBPg5A-unsplash.jpg'
// import '../../index.css'; // Adjust the path based on your project structure
// import './RegisterComponent.css'
import i18n from '/config/i18n'; // Import the i18n configuration
import { useTranslation } from 'react-i18next';

const RegisterComponent = () => {

    const { t } = useTranslation();

    const navigator = useNavigate()

    const [name, setName] = useState('')
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [role, setRole] = useState("ROLE_USER")

    const [nameError, setNameError] = useState('');
    const [usernameError, setUsernameError] = useState('');
    const [emailError, setEmailError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [confirmPasswordError, setConfirmPasswordError] = useState('');
    const [passwordNotMatchedError, setPasswordNotMatchedError] = useState('');

    const [registerMsg, setRegisterMsg] = useState('');


    function handleRegistrationForm(e){

        e.preventDefault();

        // Reset errors
        setNameError('')
        setUsernameError('')
        setEmailError('')
        setPasswordError('')
        setConfirmPasswordError('')
        setRegisterMsg('')

        // Validate name
        if (!name) {
            setNameError(t("enterName"));
        }

        // Validate username
        if (!username) {
            setUsernameError(t("enterUsername"));
        }

        // Validate password
        if (!email) {
            setEmailError(t("enterEmail"));
        }

        // Validate password
        if (!password) {
            setPasswordError(t("enterPassword"));
        }

        // Validate password
        if (!confirmPassword) {
            setConfirmPasswordError(t("enterConfirmPassword"));
        }



        // Check if password and confirm password matched
        if (password !== confirmPassword) {
            setPasswordNotMatchedError(t("enterPasswordNotMatched"))
            return;
        }

        // Check if there are any validation errors
        if (nameError || usernameError || emailError || passwordError || confirmPasswordError) {
            return;
        }

        const register = {name, username, email, password, confirmPassword, role}

        registerAPICall(register).then((response) => {
            setRegisterMsg(t("registerSuccessfulMsg"))
            navigator("/welcome")
        }).catch(error => {
            console.error(error);
            setRegisterMsg(t("registerUnsuccessfulMsg"))
        })
    }

    /*
    In the context of the useEffect hook in React, the empty array ([]) is used as the dependency array. The dependency array is an optional second argument in the useEffect hook that specifies the values (or variables) that the effect depends on. When the values in the dependency array change, the effect is re-run.
    */

    useEffect(() => {
        // Reset errors when language changes
        setNameError('');
        setUsernameError('');
        setEmailError('');
        setPasswordError('');
        setConfirmPasswordError('');
        setPasswordNotMatchedError('');
        setRegisterMsg('');
    }, [i18n.language]);

    useEffect(() => {
        // Check if the user is logged in
        if (isUserLoggedIn()) {
            // Redirect to the "/welcome" page
            // navigator.push('/welcome');
            navigator("/welcome")
        }
        }, [navigator]);

        return (
            <div className="sign-up-form-container">
            {/* <div style={containerStyle}> */}
            <div className="sign-up-form-logo-container">
                <div className="logo">
                    <div className="logo-img">
                        <img src={cagohLogo} alt="Cagoh"/>
                    </div>
                    <div id="cagoh">
                        Cagoh
                    </div>
                </div>
    
            </div>
            <div className="sign-up-form">
                <div className="top-container">
                    <div>
                    {t("registerIntro")}
                    </div>
                </div>
                <div className="middle-container">
                    <form  className="happy-register" action="#" method="get" onSubmit={(e) => handleRegistrationForm(e)} // Add onSubmit event
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            e.preventDefault();
                            handleRegistrationForm(e);
                        }
                    }}>
                        <div className="form-group">
                            <label htmlFor="name">{t("name")}</label>
                            <input type="text" id="name" name="name" required
                            value={name}
                            onChange={ (e) => setName(e.target.value)}/>
                            {nameError && <div className="error-message">{nameError}</div>}
                        </div>
                        <br/>
                    
                        <div className="form-group">
                            <label htmlFor="username">{t("userName")}</label>
                            <input type="text" id="username" name="username" required
                            value={username}
                            onChange={ (e) => setUsername(e.target.value)}/>
                            {usernameError && <div className="error-message">{usernameError}</div>}
                        </div>
    
                        <div className="form-group">
                            <label htmlFor="email">{t("email")}</label>
                            <input type="email" id="email" name="email" required
                            value={email}
                            onChange={ (e) => setEmail(e.target.value)}/>
                            {emailError && <div className="error-message">{emailError}</div>}
                        </div>
                        <br/>
                    
                        <div className="form-group">
                            <label htmlFor="password">{t("password")}</label>
                            <input type="password" id="password" name="password" required
                            value={password}
                            onChange = { (e) => setPassword(e.target.value)}/>
                            {passwordError && <div className="error-message">{passwordError}</div>}
                        </div>
    
                    
                        <div className="form-group">
                            <label htmlFor="user_password">{t("confirmPassword")}</label>
                            <input type="password" id="user_password" name="user_password" required
                            value={confirmPassword}
                            onChange = { (e) => setConfirmPassword(e.target.value)}/>
                            {confirmPasswordError && <div className="error-message">{confirmPasswordError}</div>}
                        </div>
    
                        {/* <button type="submit">Login</button> */}
                    </form>
                    
                </div>
                
    
                <div className="bottom-container">
                    {passwordNotMatchedError && <div className="error-message">{passwordNotMatchedError}</div>}
                    {registerMsg && <div className="error-message">{registerMsg}</div>}
                    <button id="create-account" type="submit" 
                    onClick={ (e) =>
                    handleRegistrationForm(e)}>{t("createAccount")}</button>
                </div>
                <div id="log-in" onClick = { (e) => {
                        return <Navigate to="/login" />
                }}> 
                    {t("haveAnAccount")}
                    <a href="/login">{t("login")}</a>
                </div>
                    {/* Already have an account?<a href="index.html"> Log in</a> */}
    
                    <div  />
                    
                </div>
            </div>
    
            )
        }

export default RegisterComponent