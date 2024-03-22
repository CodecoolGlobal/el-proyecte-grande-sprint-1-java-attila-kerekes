import {Outlet} from "react-router-dom";
import CustomerSidebar from "./CustomerSidebar.jsx";

export default function CustomerPage() {
    return (
        <div>
            <div className="CustomerMain"
                 style={{display: 'inline-block'}}>
                <h3>CustomerSidebar</h3>
                <CustomerSidebar/>
            </div>
            <div className="CustomerDisplayBelow"
                 style={{display: 'inline-block'}}>
                <Outlet/>
            </div>
        </div>
    )
}