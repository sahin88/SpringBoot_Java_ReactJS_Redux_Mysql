import React,{useState,useEffect} from 'react'
import { TextField, Button,Grid, Typography, Paper, Container } from '@material-ui/core';
import useStyles from '../css/form.js'
import NoteCard from '../components/NoteCard.js';

function Events({events,currentId,setCurrentId}) {

    const classes = useStyles();

    if (!events){
        return
    }

    return (
        <Container className={classes.container}>
            {events.map((event, index)=>{return(
                <NoteCard events={event}  currentId={currentId} setCurrentId={setCurrentId}/>
)})}

            
        </Container>
    )
}

export default Events
