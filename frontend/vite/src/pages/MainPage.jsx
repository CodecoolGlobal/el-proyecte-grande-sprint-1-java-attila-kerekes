import {useState} from "react";

import {Outlet, useParams} from "react-router-dom";

import NavbarHeader from "./NavbarHeader.jsx";
import NavbarFooter from "./NavbarFooter.jsx";
import {Container} from "react-bootstrap";


function MainPage({isAuthenticated, setIsAuthenticated}) {
  const [customerInfo, setCustomerInfo] = useState({});
  const {id} = useParams();

  return (
    <>
      <NavbarHeader id={id} isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated}/>
      <Container
        className={"d-flex flex-column justify-content-center align-items-center"}
        style={{minHeight: "100vh"}}
      >
        <Outlet/>
      </Container>
      <NavbarFooter id={id}/>
    </>
  )
}

export default MainPage;