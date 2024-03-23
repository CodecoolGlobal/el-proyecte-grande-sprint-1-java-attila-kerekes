import {Link} from "react-router-dom";

function RestaurantSidebar() {

    return (
        <nav>
            <ul>
                <li>
                    <Link to={`details`}>
                        <button>Restaurant details</button>
                    </Link>
                </li>
                <li>
                    <Link to={`tables`}>
                        <button>Tables</button>
                    </Link>
                </li>
                <li>
                    <Link to={`reservations`}>
                        <button>Reservations</button>
                    </Link>
                </li>
            </ul>
        </nav>
    )
}

export default RestaurantSidebar;
