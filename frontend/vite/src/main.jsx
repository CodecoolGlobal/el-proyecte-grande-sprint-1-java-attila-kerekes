import React from 'react'
import ReactDOM from 'react-dom/client'
<<<<<<< Updated upstream
import App from './App.jsx'
=======
>>>>>>> Stashed changes
import './index.css'
import {BrowserRouter} from "react-router-dom";
import App from "./App.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </React.StrictMode>,
)
