import './App.css'
import {Navigate, useRoutes} from 'react-router-dom'

import MainPage from "./pages/MainPage.jsx";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import CustomerRegistration from "./pages/customer/CustomerRegistration.jsx";
import RestaurantRegistration from "./pages/restaurant/RestaurantRegistration.jsx";
import CustomerPage from "./pages/customer/CustomerPage.jsx";
import RestaurantPage from "./pages/restaurant/RestaurantPage.jsx";
import {useEffect, useState} from "react";
import About from "./pages/About.jsx";
import Contact from "./pages/Contact.jsx";
import CustomerDetails from "./pages/customer/CustomerDetails.jsx";
import ReservationList from "./pages/ReservationList.jsx";
import RestaurantDetails from "./pages/restaurant/RestaurantDetails.jsx";
import TableList from "./pages/restaurant/table/TableList.jsx";
import Home from "./pages/Home.jsx";
import CustomerRestaurantList from "./pages/customer/CustomerRestaurantList.jsx";
import ReservationPage from "./pages/customer/ReservationPage.jsx";
import CustomerReservationList from "./pages/customer/CustomerReservationList.jsx";

function App() {
    // const [userId, setUserId] = useState('');
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isRestaurant, setRestaurant] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            setIsAuthenticated(true);
        }
        console.log(localStorage)
    }, []);

    // const logInUser = async (id) => {
    //     setUserId(id);
    // }

    const routes = useRoutes([
        {
            element: <MainPage isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated}/>,
            path: '/',
            children: [
                {
                    element: <Home/>,
                    path: '/'
                },
                {
                    element: <Login setIsAuthenticated={setIsAuthenticated} setRestaurant={setRestaurant} />,
                    path: '/login'
                },
                {
                    element: isAuthenticated ?
                        (isRestaurant ? <Navigate to="/restaurant" /> : <CustomerPage />)
                        : <Navigate to="/" />,
                    path: '/customer',
                    children: [
                        {
                            element: <CustomerDetails />,
                            path: 'details'
                        },
                        {
                            element: <ReservationPage/>,
                            path: 'restaurants/:id'
                        },
                        {
                            element: <CustomerRestaurantList />,
                            path: 'restaurants'
                        },
                        {
                            element: <CustomerReservationList />,
                            path: 'reservations'
                        },
                    ]
                },
                {
                    element: isAuthenticated ?
                        (isRestaurant ? <RestaurantPage /> : <Navigate to="/customer" />)
                        : <Navigate to="/" />,
                    path: '/restaurant',
                    children: [
                        {
                            element: <RestaurantDetails />,
                            path: 'details'
                        },
                        {
                            element: <TableList />,
                            path: 'tables'
                        },
                        {
                            element: <ReservationList />,
                            path: 'reservations'
                        },
                    ]
                },
                {
                    element: <Register/>,
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
                {
                    element: <About />,
                    path: '/about'
                },
                {
                    element: <Contact />,
                    path: '/contact'
                },
            ]
        },
    ])

    return routes;
}

export default App
