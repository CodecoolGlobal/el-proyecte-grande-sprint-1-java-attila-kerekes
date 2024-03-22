import {Link, Navigate, useNavigate} from "react-router-dom";

function NavbarHeader({id, isAuthenticated, setIsAuthenticated}) {
    const navigate = useNavigate();

    const handleLogout = () => {

        setIsAuthenticated(false);

        localStorage.removeItem('token');

        navigate("/");
    };

    return (
        <nav className="CustomerNavbar">
            <h2>NavbarHeader</h2>
            <Link to={`/`}>
                <button>RESTaurant</button>
            </Link>
            {isAuthenticated ? (
                    <>
                        <Link to={`/customer`}>
                            <button>My profile</button>
                        </Link>
                        <button onClick={handleLogout}>Logout</button>
                    </>
                )
                :
                (
                    <>
                        <Link to={`/register`}>
                            <button>Register</button>
                        </Link>
                        <Link to={`/login`}>
                            <button>Login</button>
                        </Link>
                    </>
                )
            }
        < /nav>
    )
}

export default NavbarHeader;
