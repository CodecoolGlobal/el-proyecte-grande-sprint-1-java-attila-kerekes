import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import _RestaurantNav from "../components/_restaurant_components/_RestaurantNav.jsx";
import _RestaurantReservationElements from "../components/_restaurant_components/_RestaurantReservationElements.jsx";
import _CustomerNavbar from '../components/_customer_components/_CustomerNavbar.jsx';

function _RestaurantReservation(){
    const [reservations, setReservations] = useState(null);
    const {id} = useParams();

    useEffect(() => {
        const fetchResorvation = async () => {
            const restaurantResponse = await fetch(`/api/restaurants/${id}`);
            const restaurant = await restaurantResponse.json();
            setReservations(restaurant.reservations);
        }
        fetchResorvation();
    }, [id])

    return(
        <div className="restaurant-reservation restaurant-display">
            <_CustomerNavbar />
            <_RestaurantNav />
            {reservations !== null && <h1>Reservations: </h1>}
            {reservations === null ? <h1>Loading...</h1> : reservations.length ? reservations.map(res => <_RestaurantReservationElements key={Number(res.tableId)} customerData={res}/>) : <h2>The restaurant have no reservations!</h2>}
        </div>
    )
}

export default _RestaurantReservation;