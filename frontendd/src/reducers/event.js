import { FETCH_ALL_EVENT, CREATE_EVENT, GET_EVENT_BY_ID, DELETE_EVENT, UPDATE_EVENT, SEARCH_BY_QUERY, START_LOADING, END_LOADING } from "../actions/types";

export default function (state = { events: [], isLoading: true }, action) {
    const { type, payload } = action
    console.log("type", type, "payload", payload)
    console.log("state", state)



    switch (type) {

        case START_LOADING:
            return { ...state, isLoading: true };
        case END_LOADING:
            return { ...state, isLoading: false };

        case FETCH_ALL_EVENT:


            return {
                ...state,
                events: payload,

            }
        case CREATE_EVENT:

    
            return {...state,events:[...state.events,payload]};
        

        case UPDATE_EVENT:
            return {...state, events: state.events.map((event, index)=>event.id==payload.id? payload :event)}


        case DELETE_EVENT:
            return {...state, events: state.events.filter((evnt)=>evnt.id!==payload)};

   

                // case CREATE_EVENT:


        //     return { ...state, posts: [...state.posts, payload] };
        // case POST_ID:
        //     return { ...state, post: payload };

        // case DELETE_EVENT:


        //     return { ...state, posts: state.posts.filter((post) => (post._id !== payload)) }



        default:
            return state;
    }
}