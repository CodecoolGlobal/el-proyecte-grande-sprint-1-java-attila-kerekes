import {Link} from "react-router-dom";
import {Container, Navbar as NavbarBs} from "react-bootstrap";

function NavbarFooter({id}) {

    return (
        <NavbarBs>
          <Container>
            <Link to={`/about`}><button>About</button></Link>
            <Link to={`/contact`}><button>Contact</button></Link>
          </Container>
        </NavbarBs>
    )
}

export default NavbarFooter;
