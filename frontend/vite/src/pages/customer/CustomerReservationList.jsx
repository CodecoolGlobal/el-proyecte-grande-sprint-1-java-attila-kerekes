import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";

function CustomerReservationList() {
    const [reservations, setReservations] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                const decodedToken = jwtDecode(token);
                const email = decodedToken.sub;
                const response = await fetch(`/api/reservations/customer/${email}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch reservations');
                }

                const reservationsData = await response.json();
                setReservations(reservationsData);
            } catch (error) {
                console.error('Error fetching reservations:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <div className={"CustomerRestaurantList"} >
            <h3>Reservations</h3>
            <div className={"restaurantCardContainer"}>
                {reservations && reservations.map((reservation) => (
                    <div className="reservationCard" key={reservation.publicId}>
                        <h5 className={"reservationDetail"}>Number of Guests: {reservation.numberOfCustomers}</h5>
                        <h5 className={"reservationDetail"}>Start: {reservation.start}</h5>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default CustomerReservationList;