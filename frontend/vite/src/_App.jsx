import './App.css'
import { useRoutes } from 'react-router-dom'
import _CustomerMain from './components/_CustomerMain.jsx';
import _About from './components/_customer_components/_About.jsx';
import _Contact from './components/_customer_components/_Contact.jsx';
import _CustomerFindRestaurant from './components/_customer_components/_CustomerFindRestaurant.jsx';
import _CustomerEditor from './components/_customer_components/_CustomerEditor.jsx';
import _CustomerReservations from './components/_customer_components/_CustomerReservations.jsx';
import MyRestaurant from './_pages/_MyRestaurant.jsx'
import { Login } from './_pages/./_Login.jsx'
import { _Register } from './_pages/_Register.jsx'
import _UpdateRestaurant from './_pages/_UpdateRestaurant.jsx'
import { useState } from 'react';
import _AddTables from './_pages/_AddTables.jsx';
import _RestaurantReservation from './_pages/_RestaurantReservation.jsx';
import _ReservationPage from './components/_customer_components/_ReservationPage.jsx';

function _App() {
    const [userId, setUserId] = useState('');

    const logInUser = async (id) => {
        setUserId(id);
    }

    const routes = useRoutes([
        {

            element: <_CustomerMain />,
            path: '/customer/:id',
            children: [
                {
                    element: <_About />,
                    path: 'about'
                },
                {
                    element: <_Contact />,
                    path: 'contact'
                },
                {
                    element: <_CustomerEditor />,
                    path: 'editor'
                },
                {
                    element: <_CustomerFindRestaurant />,
                    path: 'restaurants'
                },
                {
                    element: <_ReservationPage />,
                    path: ':id2'
                },
                {
                    element: <_CustomerReservations />,
                    path: 'reservations'
                },
                {
                    element: <h1>Hello</h1>,
                    path: 'hello'
                },
            ]
        },
        {
            element: <Login onSubmit={logInUser}/>,
            path: '/'
        },
        {
            element: <_Register onSubmit={logInUser}/>,
            path: '/register'
        },
        {
            element: <MyRestaurant/>,
            path: '/restaurant/myrestaurant/:id',
        },
        {
            element: <_UpdateRestaurant />,
            path: '/restaurant/update/:id'
        },
        {
            element: <_AddTables />,
            path: '/restaurant/addtable/:id',
        },
        {
            path: '/restaurant/reservations/:id',
            element: <_RestaurantReservation />
        }
    ])

    return routes;
}

export default _App
