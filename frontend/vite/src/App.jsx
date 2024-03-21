import './App.css'
import {useRoutes} from 'react-router-dom'

import MainPage from "./pages/MainPage.jsx";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import CustomerRegistration from "./pages/CustomerRegistration.jsx";
import RestaurantRegistration from "./pages/RestaurantRegistration.jsx";

function App() {
    const [userId, setUserId] = useState('');

    const logInUser = async (id) => {
        setUserId(id);
    }

    const routes = useRoutes([
        {
            element: <MainPage onSubmit={logInUser}/>,
            path: '/'
        },
        {
            element: <Login onSubmit={logInUser}/>,
            path: '/login'
        },
        {
            element: <Register onSubmit={logInUser}/>,
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
