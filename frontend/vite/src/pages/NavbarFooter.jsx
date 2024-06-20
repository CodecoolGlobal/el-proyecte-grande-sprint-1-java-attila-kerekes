import { Link } from "react-router-dom";
import { Container, Nav, Navbar as NavbarBs } from "react-bootstrap";
import {Button} from "antd";

function NavbarFooter({ id }) {
  return (
    <NavbarBs fixed="bottom" className={"bg-white shadow-lg mt-4"} >
      <Container fluid>
        <NavbarBs.Brand className="me-auto" />
        <Nav className="ms-auto">
          <Nav.Item>
            <Nav.Link as={Link} to="/about" className="btn btn-outline-primary mx-5">
              About
            </Nav.Link>
          </Nav.Item>
          <Nav.Item>
            <Nav.Link as={Link} to="/contact" className="btn btn-outline-primary mx-5">
              Contact
            </Nav.Link>
          </Nav.Item>
        </Nav>
      </Container>
    </NavbarBs>
  );
}

export default NavbarFooter;
