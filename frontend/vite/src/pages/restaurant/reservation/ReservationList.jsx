import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";

function ReservationList() {

    const [reservations, setReservations] = useState(null)
    const [restaurantDetails, setRestaurantDetails] = useState(null)

    const token = localStorage.getItem("token");
    const decodedToken = jwtDecode(token);
    const email = decodedToken.sub;

    useEffect(() => {
        const fetchDetails = async () => {
            try {
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

    useEffect(() => {
        const fetchReservations = async () => {
            try {
                const response = await fetch(`/api/reservations/restaurant/${restaurantDetails.publicId}`, {
                    headers: {"Authorization": `Bearer ${token}`},
                });
                if (!response.ok) {
                    throw new Error("Failed to fetch reservations")
                }
                const reservationList = await response.json();
                setReservations(reservationList);
            } catch (error) {
                console.error(error)
            }
        };
        restaurantDetails && fetchReservations();
    }, [restaurantDetails]);


    return (
        <div>
            <div className={"tableReservationCardContainer"}>
                {reservations && reservations.map((reservation) => (
                    <div className={"tableReservationCard"}>
                        <div className={"restaurantDetail"} key={reservation.publicId}>Customers: {reservation.numberOfCustomers}</div>
                        <div className={"restaurantDetail"} key={reservation.publicId}>Timeslot: {reservation.start}</div>
                    </div>
                    )
                )}
            </div>
        </div>
    )
}

export default ReservationList;
