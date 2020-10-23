import React from 'react';
import {Provider} from "react-redux";
import {BrowserRouter} from "react-router-dom";
import Home from "@/layout/Home";
import {createStore} from "redux";
import rootReducer from "@/redux/reducer";

const store = createStore(rootReducer);

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Home/>
            </BrowserRouter>
        </Provider>
    );
}

export default App;
