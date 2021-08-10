import { combineReducers } from 'redux';

import event from './event'
import auth from './auth'

export default combineReducers({ event, auth });