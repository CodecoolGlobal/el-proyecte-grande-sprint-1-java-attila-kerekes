import {Link} from "react-router-dom";

function RestaurantSidebar() {

    return (
        <nav className={"Sidebar"}>

            <Link to={`details`}>
                <button>Restaurant details</button>
            </Link>

            <Link to={`tables`}>
                <button>Tables</button>
            </Link>

            <Link to={`reservations`}>
                <button>Reservations</button>
            </Link>

        </nav>
    )
}

export default RestaurantSidebar;
