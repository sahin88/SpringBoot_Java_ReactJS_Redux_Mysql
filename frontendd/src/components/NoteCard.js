import React from 'react';
import Card from '@material-ui/core/Card';
import Typography from '@material-ui/core/Typography';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Collapse from '@material-ui/core/Collapse';
import Avatar from '@material-ui/core/Avatar';
import { IconButton } from '@material-ui/core';
import { red } from '@material-ui/core/colors';
import { makeStyles } from '@material-ui/core/styles';
import moment from 'moment';
import { Grid } from '@material-ui/core';
import {DeleteOutlined, UpdateOutlined} from '@material-ui/icons'
import {deleteEvent} from '../actions/actions'
import { useDispatch } from 'react-redux';

const useStyles = makeStyles((theme) => ({
    root: {
      minWidth: 345,
      margin:"20px"
    },
    media: {
      height: 0,
      paddingTop: '56.25%', // 16:9
    },
    expand: {
      transform: 'rotate(0deg)',
      marginLeft: 'auto',
      transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
      }),
    },
    expandOpen: {
      transform: 'rotate(180deg)',
    },
    avatar: {
      backgroundColor: red[500],
    },

  gridCard:{
    boder:'1px solid blue'
  }
  }));



function NoteCard({events,currentId, setCurrentId}) {
    const classes = useStyles();
    const dispatch = useDispatch()

    const handleDelete=(item)=>{
     dispatch(deleteEvent(item));


    }

    const handleUpdate=(item)=>{
      setCurrentId(item)
   

    }
    return (
        <div className={classes.gridCard}>
          <Grid  item sm={12} xs={12} md={12} >
            <Card className={classes.root}>
                <CardHeader avatar={
                <Avatar aria-label="recipe" className={classes.avatar}>
                   
                    {events.name[0]}
                    
                </Avatar> 
        }
        
        title={events.name}
     
        subheader={moment(events.eventDay).format('MM/DD/YYYY')}
        />
                  
             
                <CardContent>
                <Typography  variant='body2'>
                    {events.description}
                </Typography>
                <CardActions disableSpacing>
                        <IconButton aria-label="delete" onClick={()=> {handleDelete(events.id)}} >
                        <DeleteOutlined/>
                        </IconButton>
                        <IconButton aria-label="update" onClick={()=> {handleUpdate(events.id)}} >
                        <UpdateOutlined />
                        </IconButton   >
    
                    </CardActions>
                    
                </CardContent>
            </Card>
            </Grid>
            
        </div>
    )
}

export default NoteCard
