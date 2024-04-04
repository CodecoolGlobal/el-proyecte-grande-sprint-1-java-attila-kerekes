import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";

function RestaurantDetails() {
    const [restaurantDetails, setRestaurantDetails] = useState(null)


    useEffect(() => {
        const fetchDetails = async () => {
            try {
                const token = localStorage.getItem("token");
                const decodedToken = jwtDecode(token);
                const email = decodedToken.sub;
                const response = await fetch(`/api/restaurants/email/${email}`, {
                    headers: {"Authorization": `Bearer ${token}`},
                })
                if (!response.ok) {
                    throw new Error("Failed to fetch restaurant")
                }
                const details = await response.json();
                setRestaurantDetails(details);
            } catch (error) {
                console.error(error)
            }
        };
        fetchDetails();
    }, []);


    return (
        restaurantDetails && <div className={"restaurantDetailsContainer"}>
            <h3>Restaurant details</h3>
            <div className="customerDetails">
                <h5>Name: {restaurantDetails.name}</h5>
                <h5>e-mail: {restaurantDetails.email}</h5>
                <h5>Phone number: {restaurantDetails.phoneNumber}</h5>
                <h5>Address: {restaurantDetails.address}</h5>
            </div>
        </div>
    )
}

export default RestaurantDetails;
