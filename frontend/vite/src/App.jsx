import './App.css'
import {Navigate, useRoutes} from 'react-router-dom'

import MainPage from "./pages/MainPage.jsx";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import CustomerRegistration from "./pages/CustomerRegistration.jsx";
import RestaurantRegistration from "./pages/RestaurantRegistration.jsx";
<<<<<<< Updated upstream
import Customer from "./pages/Customer.jsx";
import Restaurant from "./pages/Restaurant.jsx";
=======
>>>>>>> Stashed changes
import {useState} from "react";

function App() {
    // const [userId, setUserId] = useState('');
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isRestaurant, setRestaurant] = useState(false);

    // const logInUser = async (id) => {
    //     setUserId(id);
    // }

    const routes = useRoutes([
        {
            element: <MainPage /*onSubmit={logInUser}*//>,
            path: '/'
        },
        {
            element: <Login setIsAuthenticated={setIsAuthenticated} setRestaurant={setRestaurant} />,
            path: '/login'
        },
        {
            element: isAuthenticated ?
                (isRestaurant ? <Navigate to="/restaurant" /> : <Customer />)
                : <Navigate to="/" />,
            path: '/customer',
        },
        {
            element: isAuthenticated ?
                (isRestaurant ? <Restaurant /> : <Navigate to="/customer" />)
                : <Navigate to="/" />,
            path: '/restaurant',
        },
        {
            element: <Register /*onSubmit={logInUser}*//>,
            path: '/register',
            children: [
                {
                    element: <CustomerRegistration />,
                    path: 'customer'
                },
                {
                    element: <RestaurantRegistration />,
                    path: 'restaurant'
                }
            ]
        },
    ])

    return routes;
}

export default App
