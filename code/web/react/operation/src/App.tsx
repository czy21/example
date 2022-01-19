import React from 'react';
import {BrowserRouter} from "react-router-dom";
import {createStore} from "redux";
import {Provider} from "react-redux";
import rootReducer from "@/redux";
import Home from "@/layout/Home";

const store = createStore(rootReducer);

function App() {
    console.log(process.env.BASE_URL)
    return (
        <Provider store={store}>
            <BrowserRouter basename={process.env.BASE_URL??"/react/"}>
                <Home/>
            </BrowserRouter>
        </Provider>
    );
}

export default App;
