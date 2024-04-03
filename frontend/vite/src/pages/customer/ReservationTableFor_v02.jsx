import {useEffect, useState} from "react";
import {jwtDecode} from "jwt-decode";
import {useParams} from "react-router-dom";

function ReservationTableFor_v02({startDate, openingTime, closingTime, disabledTimeslots, numberOfCustomers, onBookingMade }) {

    const [customerDetails, setCustomerDetails] = useState([]);
    const [reservationInfo, setReservationInfo] = useState({
        duration: 1,
        numberOfCustomers: numberOfCustomers,
    });

    const token = localStorage.getItem('token');
    const decodedToken = jwtDecode(token);
    const customerEmail = decodedToken.sub;

    const {id} = useParams();

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

    async function handleReserveClick(event, date, time) {
        event.preventDefault();
        const startDateTime = new Date(date);
        const [year, month, day] = date.split('-');
        const [hours, minutes] = time.split(':');
        startDateTime.setHours(hours, minutes)
        startDateTime.setFullYear(year, month - 1, day)
        console.log(startDateTime)
        const reservationDTO = {...reservationInfo, start: startDateTime.toISOString()};
        console.log(reservationDTO);

        try {
            const post = await fetch(`/api/reservations/${id}/${customerDetails.id}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(reservationDTO)
            });

            if (post.ok) {
                console.log("Reservation successful");
                onBookingMade();
            } else {
                console.error("Reservation failed");
            }
        } catch (error) {
            console.error("Error occurred while making reservation:", error);
        }
    }

    const generateDateStrings = (startDate) => {
        const dateStrings = [];
        for (let i = 0; i < 7; i++) {
            const currentDate = new Date(startDate);
            currentDate.setDate(startDate.getDate() + i);
            const year = currentDate.getFullYear();
            const month = String(currentDate.getMonth() + 1).padStart(2, '0');
            const day = String(currentDate.getDate()).padStart(2, '0');
            const dateString = `${year}-${month}-${day}`;
            dateStrings.push(dateString);
        }
        return dateStrings;
    };

    const generateTimeSlots = (openingTime, closingTime) => {
        const timeSlots = [];
        for (let i = openingTime; i <= closingTime; i++) {
            timeSlots.push(`${i}:00`);
        }
        return timeSlots;
    };

    const dates = generateDateStrings(startDate);

    const timeSlots = generateTimeSlots(openingTime, closingTime);

    const isTimeslotDisabled = (dayOfWeek, hourOfDay) => {
        return disabledTimeslots.some(slot => slot.dayOfWeek === dayOfWeek && slot.hourOfDay - 12 === hourOfDay);
    };

    return (
        <table>
            <thead>
            <tr>
                <th></th>
                {dates.map((date, index) => (
                    <th key={index}>{date}</th>
                ))}
            </tr>
            </thead>
            <tbody>
            {timeSlots.map((time, timeIndex) => (
                <tr key={timeIndex}>
                    <td>{time}</td>
                    {dates.map((_, dateIndex) => (
                        <td key={dateIndex}>
                            <button
                                style={{padding: "5px", fontSize: "15px"}}
                                disabled={isTimeslotDisabled(dateIndex, timeIndex)}
                                onClick={(event) => {
                                    handleReserveClick(event, dates[dateIndex], time)
                                }}
                            >
                                Reserve
                            </button>
                        </td>
                    ))}
                </tr>
            ))}
            </tbody>
        </table>
    );
}

export default ReservationTableFor_v02;
