import React, { useState } from 'react';
import { Link, Redirect } from 'react-router-dom';
import '../css/login.css'
import axios from 'axios';
import { useStore, useDispatch } from 'react-redux';
import {login}  from '../actions/auth';
import { FaUserAlt,FaUnlockAlt } from 'react-icons/fa'

function Login() {
    const [formData, setFormData] = useState({email:'',password:""});
    const onChange = e => setFormData({ ...formData, [e.target.name]: e.target.value });

    const dispatch = useDispatch();
    const onSubmit = e => {
        try {
            dispatch(login(formData));

        } catch (error) {
            alert(alert)

        }
        console.log("signal has been detected")
        e.preventDefault();

    };

    return (
        <div className="container">
            <div className="forms-container">
                <div className="signin">
                    <div className="social-media">
                    </div>
                    <form onSubmit={e => onSubmit(e)} className="signin-form">
                        <h2 className="title">Sign in</h2>
                        <p className="p-tag">Sign in with social platforms</p>

                        <div className="input-field">
                            <FaUserAlt className="form-icons" />
                            <input type="email" name="email" value={formData.email} onChange={(e) => onChange(e)} minLength='6' placeholder="Enter your Email" />


                        </div>
                        <div className="input-field">
                            <FaUnlockAlt className="form-icons" />
                            <input type="password" name="password" onChange={(e) => onChange(e)} value={formData.password} placeholder="Enter your Password" />
                        </div>

                        <input type="submit" value="Login" className="btn solid" />


                        <div className="form-bottom">

                            <Link className="p-tag" to='signup'>Don't have any account ?</Link>
                        </div>
                        <div className="form-bottom">
                            <Link className="p-tag" to='reset-password'>Forgot your Password?</Link>
                        </div>
                    </form>

                </div>

            </div>
            <div className="right-panel">
            </div>

        </div>
    )
}

export default Login
