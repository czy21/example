import React from 'react';
import {HashRouter} from "react-router-dom";
import {createStore} from "redux";
import {Provider} from "react-redux";
import rootReducer from "@/redux";
import Home from "@/layout/Home";

const store = createStore(rootReducer);

function App() {
    return (
        <Provider store={store}>
            <HashRouter basename={process.env.REACT_APP_BASE_URL}>
                <Home/>
            </HashRouter>
        </Provider>
    );
}

export default App;
