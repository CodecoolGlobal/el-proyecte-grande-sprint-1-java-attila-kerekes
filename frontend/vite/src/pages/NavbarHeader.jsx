import {Link, useNavigate} from "react-router-dom";
import {Container, Nav, Navbar as NavbarBs} from "react-bootstrap";

function NavbarHeader({id, isAuthenticated, setIsAuthenticated}) {
    const navigate = useNavigate();

    const handleLogout = () => {

        setIsAuthenticated(false);

        localStorage.removeItem('token');

        navigate("/");
    };

  return (
    <NavbarBs sticky={"top"} className={"bg-white shadow-lg mb-4"}>
      <Container fluid>
        <NavbarBs.Brand className="me-auto">
          <Link to={isAuthenticated ? `/customer/restaurants` : `/`} style={{ color: "darkgray", fontSize: '30px', fontWeight: 'bold', letterSpacing: '10px', textDecoration: 'none' }}>
            RESTaurant
          </Link>
        </NavbarBs.Brand>
        <Nav className="ms-auto">
          {isAuthenticated ? (
            <>
              <Nav.Item>
                <Nav.Link as={Link} to="/customer/details" className="btn btn-outline-primary mx-5">
                  My Profile
                </Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link onClick={handleLogout} className="btn btn-outline-primary mx-5">
                  Logout
                </Nav.Link>
              </Nav.Item>
            </>
          ) : (
            <>
              <Nav.Item>
                <Nav.Link as={Link} to="/register" className="btn btn-outline-primary mx-5">
                  Register
                </Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link as={Link} to="/login" className="btn btn-outline-primary mx-5">
                  Login
                </Nav.Link>
              </Nav.Item>
            </>
          )}
        </Nav>
      </Container>
    </NavbarBs>
  );
}

export default NavbarHeader;
