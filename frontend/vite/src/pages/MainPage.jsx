import {useState} from "react";

import {Outlet, useParams} from "react-router-dom";

import NavbarHeader from "./NavbarHeader.jsx";
import NavbarFooter from "./NavbarFooter.jsx";
import {Container} from "react-bootstrap";


function MainPage({isAuthenticated, setIsAuthenticated}) {
  const [customerInfo, setCustomerInfo] = useState({});
  const {id} = useParams();
  //
  // useEffect(() => {
  //
  //     //TODO what to fetch
  //     const fetchData = async () => {
  //         const response = await fetch(`/api/customers/${id}`);
  //         const customerData = await response.json();
  //         setCustomerInfo(customerData);
  //     }
  //     fetchData();
  // }, [])

  return (
    <>
      <div className="CustomerMain">
        <NavbarHeader id={id} isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated}/>
      </div>

      <Container className={"mb-4"}>
          <Outlet/>
      </Container>

      <div className="CustomerMain">
        <NavbarFooter id={id}/>
      </div>
    </>
  )
}

export default MainPage;