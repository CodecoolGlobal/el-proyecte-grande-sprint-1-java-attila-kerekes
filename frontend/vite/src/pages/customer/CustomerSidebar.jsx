import {Link} from "react-router-dom";

function CustomerSidebar() {

    return (
        <nav className={"Sidebar"}>


            <Link to={`details`}>
                <button>My details</button>
            </Link>

            <Link to={`restaurants`}>
                <button>Restaurant</button>
            </Link>

            <Link to={`reservations`}>
                <button>Reservations</button>
            </Link>
        </nav>
    )
}

export default CustomerSidebar;
