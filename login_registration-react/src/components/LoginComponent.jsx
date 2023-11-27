import React, { useState, useEffect } from 'react'
// import React, { useEffect } from 'react'
import { loginAPICall, saveLoggedInUser, storeToken, isUserLoggedIn } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';
// import { useHistory } from 'react-router-dom';

import cagohLogo from '../assets/images/107544628.svg'
import cagohLogoBackground from '../assets/images/v2osk-1Z2niiBPg5A-unsplash.jpg'

import { useTranslation } from 'react-i18next';
import i18n from '/config/i18n'; // Import the i18n configuration

const LoginComponent = () => {

    const [error, setError] = useState('');
    
    const { t } = useTranslation();

    const [usernameOrEmail, setUsernameOrEmail] = useState('')
    const [password, setPassword] = useState('')
    const [usernameError, setUsernameError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [loginError, setLoginError] = useState('');

    const navigator = useNavigate();

    const username = sessionStorage.getItem("authenticatedUser");

    async function handleLoginForm(e){

        // Reset errors
        setUsernameError('');
        setPasswordError('');
        setLoginError('');

        // Validate username
        if (!usernameOrEmail) {
            setUsernameError(t("enterUsernameOrEmail"));
        }

        // Validate password
        if (!password) {
            setPasswordError(t("enterPassword"));
        }

        // Check if there are any validation errors
        if (usernameError || passwordError) {
            return;
        }

        e.preventDefault();

        e.preventDefault();

        const login = {usernameOrEmail, password}

        await loginAPICall(login).then
        (
            (response) => {
                // () => {
            // const responseSaved = response


            const token = 'Bearer ' + response.data.accessToken;
            storeToken(token);

            saveLoggedInUser(usernameOrEmail);
            navigator("/welcome")

            window.location.reload(false);
        })
        .catch(error => {
            setLoginError(t("loginErrorMsg"))
        })


    }

    useEffect(() => {
        // Reset errors when language changes
        setUsernameError('');
        setPasswordError('');
        setLoginError('');
    }, [i18n.language]);

    useEffect(() => {
        // Check if the user is logged in
        if (isUserLoggedIn()) {
            navigator("/welcome")
        }
        
        }, [navigator]);

        return (
            <div className="sign-up-form-container">
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
                            {t("loginIntro")}
                        </div>
                    </div>
                    <div className="middle-container">
                    <form  className="happy-login" action="#" method="get" onSubmit={(e) => handleLoginForm(e)} // Add onSubmit event
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            e.preventDefault();
                            handleLoginForm(e);
                        }
                    }}
                    >
    
                    
                        <div className="form-group">
                            <label htmlFor="usernameOrEmail">{t('usernameOrEmail')}</label>
                            <input type="text" id="usernameOrEmail" name="usernameOrEmail" required
                            value={usernameOrEmail}
                            onChange={ (e) => setUsernameOrEmail(e.target.value)}/>
                            {usernameError && <div className="error-message">{usernameError}</div>}
                        </div>
    
    
                    
                        <div className="form-group">
                            <label htmlFor="password">{t('password')}</label>
                            <input type="password" id="password" name="password" required
                            value={password}
                            onChange = { (e) => setPassword(e.target.value)}/>
                            {passwordError && <div className="error-message">{passwordError}</div>}
                        </div>
    
                    
    
    
                        {/* <button type="submit">Login</button> */}
                    </form>
                    <div className="error-message">{loginError}</div>
                        
                    </div>
                    
    
                    <div className="bottom-container">
                        <button id="create-account" onClick={ (e) => handleLoginForm(e)}>{t('login')}</button>
                    </div>
                </div>
            </div>
        )
    }
    
    export default LoginComponent