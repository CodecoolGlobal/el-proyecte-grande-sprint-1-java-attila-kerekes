import {Link, Navigate} from "react-router-dom";
import Customer from "./Customer.jsx";

function NavbarHeader({id, isAuthenticated}) {

    return (
        <nav className="CustomerNavbar">
            <h1>NavbarHeader</h1>
            <Link to={`/`}><button>RESTaurant</button></Link>
            {isAuthenticated ? (
                    <>
                    <Link to={`/customer`}>    <button>My profile</button></Link>
                    <Link to={`/`}> <button>Logout</button></Link>
                    </>
                )
                :
                (
                    <>
                    <Link to={`/register`}><button>Register</button></Link>
                    <Link to={`/login`}> <button>Login</button></Link>
                    </>
                )
            }
        < /nav>
    )
}

export default NavbarHeader;
