
import React, { useState, useEffect } from 'react';
import { TextField, Button, Typography, Paper } from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';

import moment from 'moment';
import useStyles from '../css/form.js'

import {createEvent, updateEvent,deleteEvent} from '../actions/actions'

const Form = ({ currentId, setCurrentId }) => {
  const [eventData, setEventData] = useState({ name: '', description: '', eventDay: '' });
  const event = useSelector((state) => (currentId ? state.event.events.find((message) => message.id === currentId) : null));
  const dispatch = useDispatch();
  const classes = useStyles();

  useEffect(() => {
    if (event) setEventData({name:event.name,description:event.description,eventDay:event.eventDay});
  }, [event]);

  const clear = () => {
    setCurrentId(0);
    setEventData({ name: '', description: '', eventDay: ''  });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (currentId === 0) {
      dispatch(createEvent(eventData));
      clear();
    } else {
      dispatch(updateEvent(currentId, eventData));
      clear();
    }
  };

  let eventDay=moment(eventData.eventDay).format('MM/DD/YYYY')
  return (
    <Paper  className={classes.paper}>
      <form autoComplete="off" noValidate className={`${classes.root} ${classes.form}`} onSubmit={handleSubmit}>
        <Typography variant="h6">{'Remember a Date'}</Typography>
        <TextField name="name" variant="outlined" label="Name" fullWidth value={eventData.name} onChange={(e) => setEventData({ ...eventData, name: e.target.value })} />
        
        <Typography variant="h6">{currentId!==0?`Current Date:${eventDay}`:null}</Typography>
        <TextField  name="eventData" variant="outlined" label="Date" fullWidth  type="date" defaultValue={eventDay} InputLabelProps={{
      shrink: true,
    }}  value={eventData.eventDay} onChange={(e) => setEventData({ ...eventData,eventDay : e.target.value })} />
        <TextField name="description"variant="outlined" label="Description" fullWidth multiline rows={4} value={eventData.description} onChange={(e) => setEventData({ ...eventData, description: e.target.value })} />
        
        <Button className={classes.buttonSubmit} variant="contained" color="primary" size="large" type="submit" fullWidth>Submit</Button>
        <Button variant="contained" color="secondary" size="small" onClick={clear} fullWidth>Clear</Button>
      </form>
    </Paper>
  );
};

export default Form;