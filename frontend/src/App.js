
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import Home from './containers/Home';
import Login from './containers/Login';
import New from './containers/News';
import Register from './containers/Register';
import EventDetail from './containers/EventDetail';
import Layout from './hocs/Layout';
import ProtectedRoute from "./containers/ProtectedRoutes";


function App() {

  //const state = useSelector(state => state.posts);
  // const dispatch = useDispatch()
  return (
    <Router>
      <Layout>
        <Switch>
          
           <ProtectedRoute exact path="/" component={Home} />
            <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
        </Switch>
      </Layout>
    </Router>
  );
}

export default App;
