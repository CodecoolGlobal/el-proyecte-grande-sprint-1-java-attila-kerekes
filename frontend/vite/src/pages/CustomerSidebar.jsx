import {Link} from "react-router-dom";

function CustomerSidebar() {

    return (
        <nav>
            <ul>
                <li>
                    <Link to={`details`}>
                        <button>My details</button>
                    </Link>
                </li>
                <li>
                    <Link to={`restaurants`}>
                        <button>Restaurant</button>
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

export default CustomerSidebar;
