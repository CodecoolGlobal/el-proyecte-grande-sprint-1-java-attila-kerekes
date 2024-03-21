import {useState} from "react";

import {Outlet, useParams} from "react-router-dom";

import NavbarHeader from "./NavbarHeader.jsx";
import NavbarFooter from "./NavbarFooter.jsx";


function MainPage({isAuthenticated}) {
    const [customerInfo, setCustomerInfo] = useState({});
    const {id} = useParams();
    //
    // useEffect(() => {
    //
    //     //TODO what to fetch
    //     const fetchData = async () => {
    //         const response = await fetch(`/api/customers/${id}`);
    //         const customerData = await response.json();
    //         setCustomerInfo(customerData);
    //     }
    //     fetchData();
    // }, [])

    return (
        <div className="CustomerMain">
            <div className="CustomerMain">
                <NavbarHeader id={id} isAuthenticated={isAuthenticated}/>
            </div>
            <div className="CustomerDisplayBelow">
                <Outlet/>
            </div>
            <div className="CustomerMain">
                <NavbarFooter id={id}/>
            </div>
        </div>
    )
}

export default MainPage;