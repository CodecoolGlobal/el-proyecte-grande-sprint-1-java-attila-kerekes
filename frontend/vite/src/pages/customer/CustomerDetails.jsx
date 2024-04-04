import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";

function CustomerDetails() {

    const [customerDetails, setCustomerDetails] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                const decodedToken = jwtDecode(token);
                const email = decodedToken.sub;
                const response = await fetch(`/api/customers/email/${email}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch customer');
                }

                const customerData = await response.json();
                setCustomerDetails(customerData);
            } catch (error) {
                console.error('Error fetching customer:', error);
            }
        };
        fetchData();
    }, []);

    return (
        <div>
            <h3>Customer details</h3>
            <div className={"detailsCard"} key={customerDetails.publicId}>
                <h5 className={"restaurantDetail"}>First name:{customerDetails.firstName}</h5>
                <h5 className={"restaurantDetail"}>Last name:{customerDetails.lastName}</h5>
                <h5 className={"restaurantDetail"}>E-mail: {customerDetails.email}</h5>
                <h5 className={"restaurantDetail"}>Phone: {customerDetails.phoneNumber}</h5>
            </div>
        </div>
    )
}

export default CustomerDetails;
