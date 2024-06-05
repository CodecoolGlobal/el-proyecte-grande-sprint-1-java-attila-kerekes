import {Link, Navigate, useNavigate} from "react-router-dom";
import {Container, Navbar as NavbarBs} from "react-bootstrap";

function NavbarHeader({id, isAuthenticated, setIsAuthenticated}) {
  const navigate = useNavigate();

  const handleLogout = () => {

    setIsAuthenticated(false);

    localStorage.removeItem('token');

    navigate("/");
  };

  return (
    <NavbarBs>
      <Container>
        <h2 className={"Title"}>RESTaurant</h2>
        {isAuthenticated ? (
            <>
              <Link to={`/customer/restaurants`}>
                <button>RESTaurant</button>
              </Link>
              <Link to={`/customer/details`}>
                <button>My profile</button>
              </Link>
              <button onClick={handleLogout}>Logout</button>
            </>
          )
          :
          (
            <>
              <Link to={`/`}>
                <button className="btn btn-primary">RESTaurant</button>
              </Link>

              <Link to={`/register`}>
                <button>Register</button>
              </Link>
              <Link to={`/login`}>
                <button>Login</button>
              </Link>
            </>
          )
        }
      </Container>
    </NavbarBs>
  )
}

export default NavbarHeader;
