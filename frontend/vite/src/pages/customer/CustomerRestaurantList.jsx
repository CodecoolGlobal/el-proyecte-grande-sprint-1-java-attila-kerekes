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
        <div>
            <h3>Restaurant List</h3>
            <div>
                {restaurants && restaurants.map((restaurant) => (
                    <div className="restaurantList" style={{border: "1px solid black"}} key={restaurant.publicId}>
                        <h4>{restaurant.name}</h4>
                        <h5>E-mail: {restaurant.email}</h5>
                        <h5>Phone: {restaurant.phoneNumber}</h5>
                        <h5>Address: {restaurant.address}</h5>
                        <Link to={`/`}><button>Reserve a table!</button></Link>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default CustomerRestaurantList;