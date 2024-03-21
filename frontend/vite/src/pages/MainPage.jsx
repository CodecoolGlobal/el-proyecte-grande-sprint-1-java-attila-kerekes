import React, {useEffect, useState} from "react";

import {Outlet, useParams} from "react-router-dom";

import NavbarHeader from "./NavbarHeader.jsx";
import NavbarFooter from "./NavbarFooter.jsx";


function MainPage() {
    const [customerInfo, setCustomerInfo] = useState({});
    const {id} = useParams();

    useEffect(() => {

        //TODO what to fetch
        const fetchData = async () => {
            const response = await fetch(`/api/customers/${id}`);
            const customerData = await response.json();
            setCustomerInfo(customerData);
        }
        fetchData();
    }, [])

    return (
        <div className="CustomerMain">
            <NavbarHeader id={id}/>
            <div className="CustomerDisplayBelow">
                <Outlet/>
            </div>
            <NavbarFooter id={id}/>
            <div className="CustomerDisplayBelow">
            </div>
        </div>
    )
}

export default MainPage;