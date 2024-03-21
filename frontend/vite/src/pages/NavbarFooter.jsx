import {Link} from "react-router-dom";

function NavbarFooter({id}) {

    return (
        <nav className="CustomerNavbar">
            <h1>NavbarFooter</h1>
            <button><Link to={`/about`}>About</Link></button>
            <button><Link to={`/contact`}>Contact</Link></button>
        </nav>
    )
}

export default NavbarFooter;
