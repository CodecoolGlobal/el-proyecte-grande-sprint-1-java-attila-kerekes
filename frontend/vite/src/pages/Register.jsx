import Restaurant from "./Restaurant.jsx";
import {Outlet} from "react-router-dom";

export default function Register() {
    return (
        <>
            <h1>Register</h1>
            <Outlet></Outlet>
        </>
    )
}