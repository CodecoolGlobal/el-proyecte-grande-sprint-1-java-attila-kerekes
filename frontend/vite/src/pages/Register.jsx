import {Link, Outlet} from "react-router-dom";

export default function Register() {
    return (
        <>
            <h2>Register</h2>
            <Link to={`customer`}> <button>As customer</button></Link>
            <Link to={`restaurant`}><button>As restaurant</button></Link>
            <Outlet/>
        </>
    )
}