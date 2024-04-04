import {useEffect, useState} from "react";

function TableReservationList({diningSpotId}) {
    const [reservations, setReservations] = useState(null)


    useEffect(() => {
        const fetchReservations = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await fetch(`/api/reservations/diningSpot/${diningSpotId}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch reservations');
                }
                const reservationList = await response.json();
                setReservations(reservationList);
            } catch (error) {
                console.error('Error fetching reservations:', error);
            }
        };
        fetchReservations();
    }, [diningSpotId]);


    return (
        <div className={"tableReservationListContainer"}>
            <h3>Reservations</h3>
            <div className={"tableReservationCardContainer"} >
                {reservations && reservations.map((reservation) => (
                    <div key={reservation.publicId} className={"tableReservationCard"}>
                        <div className={"restaurantDetail"}>{reservation.start}</div>
                        <div className={"restaurantDetail"}>Number of guests:{reservation.numberOfCustomers}</div>
                    </div>
                    )
                )}
            </div>
        </div>
    );
}

export default TableReservationList;