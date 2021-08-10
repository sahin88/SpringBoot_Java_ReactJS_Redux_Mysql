import React, { useState, useEffect, Fragment } from 'react'
import { Link, useHistory, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { GiHamburgerMenu } from "react-icons/gi";
import { GrClose } from 'react-icons/gr'
import '../css/navbar.css'

function Navbar() {
    const [clicked, setClicked] = useState(true)
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('token')))
    const [token, setToken] = useState(JSON.parse(localStorage.getItem('auth-token')))
    // const history = useHistory()
    // const dispatch = useDispatch();
    const dispatch = useDispatch();
    const handleClick = (e) => {
        setClicked(!clicked)
        
    }



    useEffect(() => {
        const token = user?.token;

        //history.push('/')
        return () => {

        }

    }, [])


    const logoutHandler = () => {

        setUser(null)
        setToken(null)
        dispatch({type:"LOGOUT"})
    }
    return (
        <nav class="navigatin-bar">
            <div class="navigation-bar-logo">
                Springboot Remember Special Day
            </div>
            <div className={clicked ? "navigation-bar-routes backing" : "navigation-bar-routes forward"}>
                <Link style={{ color: 'white', textDecoration: 'None' }} to="/">Home</Link>

                {user || token ? <Fragment> <Link style={{ color: 'white', textDecoration: 'None', padding: '10px', backgroundColor: "#DB4437" }} onClick={logoutHandler}>Logout</Link></Fragment> : <Fragment><Link style={{ color: 'white', textDecoration: 'None' }} to="/login">Login</Link>  <Link style={{ color: 'white', textDecoration: 'None' }} to="/register">Register</Link></Fragment>}
            </div>
            <div className="navigation-bar-hamburgerbtn">
                {clicked ? (<GiHamburgerMenu onClick={(e) => { handleClick() }} style={{ width: "100%", height: "100%" }} />) : (<GrClose onClick={(e) => { handleClick() }} style={{ width: "100%", height: "100%", color: "white" }} />)}
            </div>
        </nav>
    )
}

export default Navbar
