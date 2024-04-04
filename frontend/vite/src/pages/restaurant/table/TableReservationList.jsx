import {useEffect, useState} from "react";

function TableReservationList({diningSpotId}) {
    const [reservations, setReservations] = useState(null)

    useEffect(() => {
        const fetchReservations = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = fetch(`/api/reservations/diningSpot/${diningSpotId}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch reservations');
                }

                const reservationList = response.json();
                setReservations(reservationList);
            } catch (error) {
                console.error('Error fetching reservations:', error);
            }
        };
        fetchReservations();
    }, [diningSpotId]);


    return (
        <div>
            <div className={"tableReservationListContainer"}>
            <h3>Reservations</h3>
                <div className={"tableReservationCardContainer"}>
                    {reservations && reservations.map((reservation) => (
                        <div key={reservation.publicId} className={"tableReservationCard"}>
                            <div>{reservation.start}</div>
                        </div>
                    ))}

                </div>
            </div>
        </div>

    )
}

export default TableReservationList;