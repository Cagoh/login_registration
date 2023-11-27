import React, {useEffect} from 'react'
import { NavLink } from 'react-router-dom'
import { isUserLoggedIn, logout } from '../services/AuthService'
import { useNavigate } from 'react-router-dom'

import { useTranslation } from 'react-i18next';
import i18n from '/config/i18n'; // Import the i18n configuration

import { useJwt } from 'react-jwt'

const HeaderComponent = () => {



    const { t } = useTranslation();

    const isAuth = isUserLoggedIn();

    const { decodedToken, isExpired } = useJwt(localStorage.getItem("token"));

    // Check if the user has the ROLE_MANAGER role
    const isManager = decodedToken?.roles?.includes('ROLE_MANAGER');

    // const isManager = isUserManager();

    const navigator = useNavigate();

    function handleLogout(){
        logout();
        navigator('/login')
    }

    function changeLanguage(language) {
        i18n.changeLanguage(language)
    }

    const languageOptions = [
        { value: 'en', label: 'English' },
        { value: 'fr', label: 'Français' },
    ];



    useEffect(() => {
        
    }, [])



    return (
        <div>
            <header>
                <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                    <div>
                        <a href='http://localhost:3000' className='navbar-brand'>
                            {t("headerTitle")}
                        </a>
                    </div>
                    <div className='collapse navbar-collapse'>
                        <ul className='navbar-nav'>

                            {
                                isAuth &&                         
                                <li className='nav-item'>
                                <NavLink to="/welcome" className="nav-link">{t("welcome")}</NavLink>
                            </li>
                            }

                            {
                                isManager &&                         
                                <li className='nav-item'>
                                <NavLink to="/manager" className="nav-link">{t("manager")}</NavLink>
                            </li>
                            }

                        </ul>

                    </div>
                    <ul className='navbar-nav'>
                        {
                            !isAuth &&                         
                            <li className='nav-item'>
                            <NavLink to="/register" className="nav-link">{t("register")}</NavLink>
                        </li>
                        }

                        {
                            !isAuth &&    
                            <li className='nav-item'>
                            <NavLink to="/login" className="nav-link">{t("login")}</NavLink>
                        </li>
                        }

                        {
                            isAuth &&    
                            <li className='nav-item'>
                            <NavLink to="/login" className="nav-link" onClick={handleLogout}>Logout</NavLink>
                        </li>
                        }

                        {/* Language dropdown */}
                        <li className='nav-item'>
                        <select
                            className='nav-link'
                            onChange={(e) => changeLanguage(e.target.value)}
                        >
                            {languageOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                            ))}
                        </select>
                        </li>

                        </ul>
                </nav>
            </header>

        </div>
    )
}

export default HeaderComponent