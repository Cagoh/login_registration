import { useState } from 'react'
import './App.css'
import ManagerComponent from './components/ManagerComponent'
import WelcomeComponent from './components/WelcomeComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './services/AuthService'

function App() {

  function AuthenticatedRoute({children}){

    const isAuth = isUserLoggedIn();

    if(isAuth) {
      return children;
    }

    return <Navigate to="/" />

  }

  return (
    <>
    <BrowserRouter>
        <HeaderComponent />
          <Routes>
              {/* http://localhost:8082 */}
              <Route path='/' element = { <LoginComponent /> }></Route>

              {/* http://localhost:8082/welcome */}
              <Route path='/welcome' element = { 
              <AuthenticatedRoute>
                <WelcomeComponent />
              </AuthenticatedRoute> 
              }></Route>

              {/* http://localhost:8082/manager */}
              <Route path='/manager' element = { 
              <AuthenticatedRoute>
                <ManagerComponent />
              </AuthenticatedRoute> 
              }></Route>

              {/* http://localhost:8080/register */}
              <Route path='/register' element = { <RegisterComponent />}></Route>

              {/* http://localhost:8080/login */}
              <Route path='/login' element = { <LoginComponent /> }></Route>

          </Routes>
        <FooterComponent />
        </BrowserRouter>
    </>
  )
}

export default App
