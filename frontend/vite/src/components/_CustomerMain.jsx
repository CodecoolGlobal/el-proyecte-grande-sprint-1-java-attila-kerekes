import React, { useEffect, useState } from "react";

import { Outlet, useParams } from "react-router-dom";

import _CustomerNavbar from "./_customer_components/_CustomerNavbar.jsx";
import _CustomerButtons from "./_customer_components/_CustomerButtons.jsx";


function _CustomerMain() {
  const [customerInfo, setCustomerInfo] = useState({});
  const { id } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`/api/customers/${id}`);
      const customerData = await response.json();
      setCustomerInfo(customerData);
    }
    fetchData();
  }, [])

  return (
    <div className="CustomerMain">
      <_CustomerNavbar id={id} />
      <div className="CustomerDisplayBelow">
        <_CustomerButtons id={id} />
        <div className="CustomerContent">
          <Outlet />
        </div>
      </div>
    </div>
  )
}

export default _CustomerMain;