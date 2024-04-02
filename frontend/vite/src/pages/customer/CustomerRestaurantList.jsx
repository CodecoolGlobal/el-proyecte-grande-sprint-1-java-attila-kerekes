import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

function CustomerRestaurantList() {
    const [restaurants, setRestaurants] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await fetch(`/api/restaurants`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch restaurants');
                }

                const restaurantsData = await response.json();
                setRestaurants(restaurantsData);
            } catch (error) {
                console.error('Error fetching restaurants:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <div className={"CustomerRestaurantList"} >
            <h3>Restaurants</h3>
            <div className={"restaurantCardContainer"}>
                {restaurants && restaurants.map((restaurant) => (
                    <div className="restaurantCard" key={restaurant.publicId}>
                        <Link to={`/customer/restaurants/${restaurant.publicId}`}>
                        <h4 className={"restaurantName"}>{restaurant.name}</h4>
                        <h5 className={"restaurantDetail"}>E-mail: {restaurant.email}</h5>
                        <h5 className={"restaurantDetail"}>Phone: {restaurant.phoneNumber}</h5>
                        <h5 className={"restaurantDetail"}>Address: {restaurant.address}</h5>
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default CustomerRestaurantList;