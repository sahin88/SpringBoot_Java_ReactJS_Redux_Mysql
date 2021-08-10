import { LOGIN,SIGNUP,AUTH } from "./types";

import axios from "axios";

 const url= 'http://localhost:8080/auth'


 export const login= (loginData)=>async(dispatch)=>{
const loginPage='loginPage'
console.log("data",loginData)
    try {
        const{ data}= await  axios.post("http://localhost:8080/api/users/login", loginData);
        
        dispatch({type:AUTH, payload:data})
        return data;
    } catch (error) {
        console.log("error from login", error.message)
    }
}


 export const register= (registerData)=>async(dispatch)=>{
    const registerPage='registerPage';
    try {
        const registerResponse= await  axios.post(`${url}/${registerPage}`, registerData);
        dispatch({type:SIGNUP, payload:registerResponse})
    } catch (error) {
        console.log("error from register", error)
    }
}


