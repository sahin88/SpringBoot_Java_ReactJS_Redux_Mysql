import React,{useEffect, useState} from 'react'
import { getPost } from '../actions/actions'
import{useDispatch, useSelector} from 'react-redux'
import '../css/home.css'
import Loader from "react-loader-spinner";
import Form from './Form';
import Events from './Events';
function Home() {
 const dispatch = useDispatch()
 const [currentId, setCurrentId]=useState(0)

// const [isLoading, setLoading]=useState(true)


 const{events, isLoading} =useSelector((state)=>state.event)

    useEffect(() => {
        dispatch(getPost())
        return () => {
        }
      },[])


    return (
        <div>
            {!isLoading?<div className="home-main-div">
                <div className="home-main-div-left">
                    <Events events={events} currentId={currentId} setCurrentId={setCurrentId}/>
                </div>
                <div className="home-main-div-right">
                   <Form currentId={currentId} setCurrentId={setCurrentId} /> 
                </div>

            </div>:<Loader type="Oval" className='loader' color="#00BFFF" height={80} width={80}/>}
          
        </div>
    )
}

export default Home
