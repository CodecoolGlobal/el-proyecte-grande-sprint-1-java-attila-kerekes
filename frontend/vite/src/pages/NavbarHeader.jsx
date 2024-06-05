import {Link, Navigate, useNavigate} from "react-router-dom";

function NavbarHeader({id, isAuthenticated, setIsAuthenticated}) {
  const navigate = useNavigate();

  const handleLogout = () => {

    setIsAuthenticated(false);

    localStorage.removeItem('token');

    navigate("/");
  };

  return (
    <nav className="CustomerNavbar">
      <div className={"TitleContainer"}>
        <h2 className={"Title"}>RESTaurant</h2>
      </div>
      <div>

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
                <button>RESTaurant</button>
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
      </div>
    < /nav>
  )
}

export default NavbarHeader;
