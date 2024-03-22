import {useState} from "react";

import {Outlet, useParams} from "react-router-dom";

import NavbarHeader from "./NavbarHeader.jsx";
import NavbarFooter from "./NavbarFooter.jsx";


function MainPage({isAuthenticated, setIsAuthenticated}) {
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
        <>
            <div className="CustomerMain" style={{ border: "1px solid black", backgroundColor: 'cornflowerblue' }}>
                <NavbarHeader id={id} isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated}/>
            </div>
            <div className="CustomerDisplayBelow" style={{ border: "1px solid black", backgroundColor: 'lightblue'}}>
                <Outlet/>
            </div>
            <div className="CustomerMain" style={{ border: "1px solid black", backgroundColor: 'cornflowerblue' }}>
                <NavbarFooter id={id}/>
            </div>
        </>
    )
}

export default MainPage;