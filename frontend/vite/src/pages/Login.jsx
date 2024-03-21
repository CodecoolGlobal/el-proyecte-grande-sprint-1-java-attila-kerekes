import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';

const Login = ({ setIsAuthenticated, setRestaurant }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await fetch('/api/clients/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });
            const data = await response.json();

            localStorage.setItem('token', data.jwt);

            const role = data.role;

            if (role === 'ROLE_RESTAURANT') {
                setRestaurant(true);
                navigate('/restaurant');
            } else {
                setRestaurant(false);
                navigate('/customer');
            }

            setIsAuthenticated(true);

        } catch (error) {
            console.error('Error logging in:', error);
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <input
                type="text"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleLogin}>Login</button>
        </div>
    );
};

export default Login;
