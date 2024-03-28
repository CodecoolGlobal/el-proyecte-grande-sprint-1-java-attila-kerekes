import {Outlet} from "react-router-dom";
import RestaurantSidebar from "./RestaurantSidebar.jsx";

export default function RestaurantPage() {
    return (
        <div>
            <div className="CustomerMain"
                 style={{display: 'inline-block'}}>
                <h3>RestaurantSidebar</h3>
                <RestaurantSidebar/>
            </div>
            <div className="CustomerDisplayBelow"
                 style={{display: 'inline-block'}}>
                <Outlet/>
            </div>
        </div>
    )
}