import {Link, Navigate} from "react-router-dom";
import Customer from "./Customer.jsx";

function NavbarHeader({id, isAuthenticated}) {

    return (
        <nav className="CustomerNavbar">
            <h1>NavbarHeader</h1>
            <button><Link to={`/`}>RESTaurant</Link></button>
            {isAuthenticated ? (
                    <>
                        <button><Link to={`/customer`}>My profile</Link></button>
                        <button><Link to={`/`}>Logout</Link></button>
                    </>
                )
                :
                (
                    <>
                        <button><Link to={`/register`}>Register</Link></button>
                        <button><Link to={`/login`}>Login</Link></button>
                    </>
                )
            }
        < /nav>
    )
}

export default NavbarHeader;
