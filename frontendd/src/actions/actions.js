import { FETCH_ALL_EVENT, CREATE_EVENT, DELETE_EVENT, UPDATE_EVENT, SEARCH_BY_QUERY, START_LOADING, END_LOADING, GET_EVENT_BY_ID } from "./types";
import axios from 'axios';


export const getPost = () => async (dispatch) => {
    try {

        dispatch({ type: START_LOADING })
       
        axios.interceptors.request.use(function (config) {
            const token =  `Bearer ${JSON.parse(localStorage.getItem('token')).token}`;
            config.headers.Authorization =  token;
        
            return config;
        });

        const {data} = await axios.get(`http://localhost:8080/api/events`);

        dispatch({ type: FETCH_ALL_EVENT, payload: data })
        dispatch({ type: END_LOADING });


    } catch (error) {
        console.log("error from getUserEVents", error)

    }
}




export const createEvent = (formData) => async (dispatch) => {

    try {

        dispatch({ type: START_LOADING })
       
        axios.interceptors.request.use(function (config) {
            const token =  `Bearer ${JSON.parse(localStorage.getItem('token')).token}`;
            config.headers.Authorization =  token;
        
            return config;
        });

  
            const {data} = await axios.post(`http://localhost:8080/api/events`,formData)
          
            dispatch({ type:CREATE_EVENT, payload:data})
           
            
    

     
        dispatch({ type: END_LOADING });


    } catch (error) {
        console.log("error from getUserEVents", error)

    }
}



export const updateEvent = (eventId,formData) => async (dispatch) => {

    try {

        dispatch({ type: START_LOADING })
       
        axios.interceptors.request.use(function (config) {
            const token =  `Bearer ${JSON.parse(localStorage.getItem('token')).token}`;
            config.headers.Authorization =  token;
        
            return config;
        });
            console.log("formData", formData, eventId)
  
            const {data} = await axios.put(`http://localhost:8080/api/events/update/${eventId}`,formData)
          
            dispatch({ type:UPDATE_EVENT, payload:data})
     
        dispatch({ type: END_LOADING });


    } catch (error) {
        console.log("error from getUserEVents", error)

    }
}




export const deleteEvent = (eventId) => async (dispatch) => {

    try {

        dispatch({ type: START_LOADING })
       
        axios.interceptors.request.use(function (config) {
            const token =  `Bearer ${JSON.parse(localStorage.getItem('token')).token}`;
            config.headers.Authorization =  token;
        
            return config;
        });
  
            const {data} = await axios.delete(`http://localhost:8080/api/events/${eventId}`)
          
            dispatch({ type:DELETE_EVENT, payload:eventId})
     
        dispatch({ type: END_LOADING });


    } catch (error) {
        console.log("error from  deleteEvents", error)

    }
}



