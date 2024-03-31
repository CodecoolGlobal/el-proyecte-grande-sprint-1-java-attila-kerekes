import {Outlet} from "react-router-dom";
import CustomerSidebar from "./CustomerSidebar.jsx";

export default function CustomerPage() {
    return (
        <div className={"MainContainer"}>
            <div className={"SidebarContainer"}>
                <h3>CustomerSidebar</h3>
                <CustomerSidebar/>
            </div>
            <div className="CustomerDisplayBelow">
                <Outlet/>
            </div>
        </div>
    )
}