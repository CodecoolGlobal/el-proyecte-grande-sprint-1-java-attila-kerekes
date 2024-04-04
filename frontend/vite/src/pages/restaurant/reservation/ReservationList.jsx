import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";

function ReservationList() {

    const [reservations, setReservations] = useState(null)
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

    useEffect(() => {
        const fetchReservations = async () => {
            const response = await fetch(`/api/reservations/restaurant/${restaurantDetails.publicId}`);
            const reservationList = await response.json();
            console.log(reservationList);
            setReservations(reservationList);
        }
        restaurantDetails && fetchReservations();
    }, [restaurantDetails]);


    return (
        <div>
            <div className={"tableReservationCardContainer"}>
                {reservations && reservations.map((reservation) => (
                        <div key={reservation.publicId}>{reservation.stat}</div>

                    )
                )}
            </div>
        </div>
    )
}

export default ReservationList;
