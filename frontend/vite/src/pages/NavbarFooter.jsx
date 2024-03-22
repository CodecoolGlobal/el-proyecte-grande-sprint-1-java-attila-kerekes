import {Link} from "react-router-dom";

function NavbarFooter({id}) {

    return (
        <nav className="CustomerNavbar">
            <h2>NavbarFooter</h2>
            <Link to={`/about`}><button>About</button></Link>
            <Link to={`/contact`}><button>Contact</button></Link>
        </nav>
    )
}

export default NavbarFooter;
