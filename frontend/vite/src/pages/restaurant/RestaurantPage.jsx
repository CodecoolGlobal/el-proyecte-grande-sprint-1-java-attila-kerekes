import {Outlet} from "react-router-dom";
import RestaurantSidebar from "./RestaurantSidebar.jsx";

export default function RestaurantPage() {
    return (
        <div>
            <div className="CustomerMain">
                <h3>RestaurantSidebar</h3>
                <RestaurantSidebar/>
            </div>
            <div className="CustomerDisplayBelow">
                <Outlet/>
            </div>
        </div>
    )
}