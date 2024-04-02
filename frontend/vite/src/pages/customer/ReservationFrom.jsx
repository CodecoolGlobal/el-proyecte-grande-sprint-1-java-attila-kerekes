import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {jwtDecode} from "jwt-decode";

function ReservationForm({ onUpdate }) {

    const { id } = useParams();
    const [reservationInfo, setReservationInfo] = useState({
        duration: 1,
        numberOfCustomers: 0,
        startDate: null,
        startTime: null
    });
    const [restaurantDetails, setRestaurantDetails] = useState(null);
    const [customerDetails, setCustomerDetails] = useState([]);

    const token = localStorage.getItem('token');
    const decodedToken = jwtDecode(token);
    const customerEmail = decodedToken.sub;

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`/api/customers/email/${customerEmail}`, {
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

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`/api/restaurants/${id}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch restaurantDetails');
                }

                const restaurantData = await response.json();
                setRestaurantDetails(restaurantData);
            } catch (error) {
                console.error('Error fetching restaurantDetails:', error);
            }
        };
        fetchData();
    }, []);


    async function handleSubmit(event) {
        event.preventDefault();
        const { startDate, startTime, ...rest } = reservationInfo;
        const startDateTime = new Date();
        console.log(startDate);
        const [year, month, day] = startDate.split('-');
        const [hours, minutes] = startTime.split(':');
        startDateTime.setHours(hours, minutes)
        startDateTime.setFullYear(year, month, day)
        console.log(startDateTime)
        const reservationDTO = { ...rest, start: startDateTime.toISOString() };
        console.log(reservationDTO);

        const post = await fetch(`/api/reservations/${restaurantDetails.publicId}/${customerDetails.id}`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-type': 'application/json'
            },
            body: JSON.stringify(reservationDTO)
        });

        if (post.ok) {
            onUpdate(true);
        } else {
            onUpdate(false);
        }
    }

    return (
        <div>
            <div className="restaurantContainer">
                {restaurantDetails &&
                    <div className="restaurantDetails">
                        <h4>Name: {restaurantDetails.name}</h4>
                        <h5>E-mail: {restaurantDetails.email}</h5>
                        <h5>Phone: {restaurantDetails.phoneNumber}</h5>
                        <h5>Address: {restaurantDetails.address}</h5>
                        <form onSubmit={handleSubmit}>
                            <label style={{fontSize: 10}}>Number of guests: </label>
                            <input type="number" name="guestNumber" onChange={(event) => {
                                setReservationInfo(prev => ({ ...prev, numberOfCustomers: event.target.value })) }} />
                            <br />
                            <label style={{fontSize: 10}}>Chosen date:  </label>
                            <input type="date" name="date" onChange={(event) => {
                                setReservationInfo(prev => ({ ...prev, startDate: event.target.value })) }} />
                            <br />
                            <label style={{fontSize: 10}}>Start of timeslot:  </label>
                            <input type="time" step={3600} name="time" onChange={(event) => {
                                setReservationInfo(prev => ({ ...prev, startTime: event.target.value })) }} />
                            <br />
                            <button>Send request</button>
                        </form>
                    </div>
                }
            </div>
        </div>
    )
}

export default ReservationForm;
