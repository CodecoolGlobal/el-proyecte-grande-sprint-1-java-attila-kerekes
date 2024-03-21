import {Link, Outlet} from "react-router-dom";

export default function Register() {
    return (
        <>
            <h2>Register</h2>
            <button><Link to={`customer`}>As customer</Link></button>
            <button><Link to={`restaurant`}>As restaurant</Link></button>
            <Outlet/>
        </>
    )
}