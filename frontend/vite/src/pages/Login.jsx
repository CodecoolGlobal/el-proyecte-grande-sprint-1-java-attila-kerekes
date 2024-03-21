import {useState} from 'react';
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
            if (!response.ok) {
                navigate("/");
            }

            const data = await response.json();

            if (!data.jwt) {
                throw new Error('Token is missing in response.');
            }

            localStorage.setItem('token', data.jwt);
            const role = data.role;

            if (role === 'ROLE_RESTAURANT') {
                setRestaurant(true);
                navigate('/restaurant');
            } else if (role === 'ROLE_CUSTOMER') {
                setRestaurant(false);
                navigate('/customer');
            } else {
                navigate('/');
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
            <br/>
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <br/>
            <button onClick={handleLogin}>Login</button>
        </div>
    );
};

export default Login;
