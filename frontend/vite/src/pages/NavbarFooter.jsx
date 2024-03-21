import React from "react";
import { Link } from "react-router-dom";

function NavbarFooter({id}) {

    return (
        <>
            <h1>NavbarFooter</h1>
        </>
        /*  <nav className="CustomerNavbar">
              <button><Link to={`/`}>Logout</Link></button>
              <button><Link to={`/customer/${id}/about`}>About</Link></button>
              <button><Link to={`/customer/${id}/contact`}>Contact</Link></button>
          </nav>
              */
    )
}

export default NavbarFooter;
