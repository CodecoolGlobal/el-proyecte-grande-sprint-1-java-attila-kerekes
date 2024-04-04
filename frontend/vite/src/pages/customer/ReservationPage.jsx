import ReservationTable from "./ReservationTable.jsx";
<<<<<<< HEAD
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

function ReservationPage() {
    const [showReservationTable, setShowReservationTable] = useState(false);
    const [numberOfGuests, setNumberOfGuests] = useState(0);
    const [currentMonday, setCurrentMonday] = useState(getCurrentMonday());
    const [disabledTimeslots, setDisabledTimeslots] = useState([]);
    const [bookingMade, setBookingMade] = useState(false);
    const {id} = useParams();

    const token = localStorage.getItem('token');

    function getCurrentMonday() {
        const today = new Date();
        const currentDay = today.getDay();
        const daysUntilMonday = currentDay === 0 ? 6 : currentDay - 1;
        const currentWeekMonday = new Date(today);
        currentWeekMonday.setDate(today.getDate() - daysUntilMonday);

        return currentWeekMonday;
    }

    function handleOkClick() {
        setShowReservationTable(true);
    }

    function handlePrevWeek() {
        const prevMonday = new Date(currentMonday);
        prevMonday.setDate(prevMonday.getDate() - 7);
        setCurrentMonday(prevMonday);
    }

    function handleNextWeek() {
        const nextMonday = new Date(currentMonday);
        nextMonday.setDate(nextMonday.getDate() + 7);
        setCurrentMonday(nextMonday);
    }

    const handleBookingMade = () => {
        setBookingMade(true);
    };

    useEffect(() => {
        const fetchDisabledTimeslots = async () => {
            try {
                const year = currentMonday.getFullYear().toString();
                const month = (currentMonday.getMonth() + 1).toString().padStart(2, "0");
                const day = currentMonday.getDate().toString().padStart(2, "0");

                const formattedDate = `${year}-${month}-${day}`;

                //console.log(formattedDate)

                const response = await fetch(`/api/reservations/disabled-timeslots/${id}/${numberOfGuests}/${formattedDate}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch disabled timeslots');
                }
                const data = await response.json();
                setDisabledTimeslots(data);
                //console.log(disabledTimeslots);
            } catch (error) {
                console.error('Error fetching disabled timeslots:', error);
            }
        };

        if (showReservationTable || bookingMade) {
            fetchDisabledTimeslots();
            setBookingMade(false);
        }
    }, [showReservationTable, numberOfGuests, currentMonday, bookingMade]);

    return (
        <div>
            <h2>Reservation Page</h2>
            {!showReservationTable && (
            <div>
                <label>Number of Guests: </label>
                <input
                    type="number"
                    value={numberOfGuests}
                    onChange={(e) => setNumberOfGuests(e.target.value)}
                />
                <button
                    style={{padding: "5px", fontSize: "15px"}}
                    onClick={handleOkClick}>OK
                </button>
            </div>
            )}
            {showReservationTable && (
                <div>
                    <div>
                        <button
                            style={{padding: "5px", fontSize: "15px"}}
                            onClick={handlePrevWeek}>&lt; Prev Week
                        </button>
                        <button
                            style={{padding: "5px", fontSize: "15px"}}
                            onClick={handleNextWeek}>Next Week &gt;
                        </button>
                    </div>
                    <h2>Week of {currentMonday.toLocaleDateString()}</h2>
                    <ReservationTable
                        startDate={currentMonday}
                        openingTime={12}
                        closingTime={20}
                        disabledTimeslots={disabledTimeslots}
                        numberOfCustomers={numberOfGuests}
                        onBookingMade={handleBookingMade}
                    />
                </div>
            )}
        </div>
    );
=======
import { useState } from "react";

function ReservationPage() {
  const [showReservationTable, setShowReservationTable] = useState(false);
  const [numberOfGuests, setNumberOfGuests] = useState(0);
  const [currentMonday, setCurrentMonday] = useState(getCurrentMonday());

  function getCurrentMonday() {
    const today = new Date();
    const currentDay = today.getDay();
    const daysUntilMonday = currentDay === 0 ? 6 : currentDay - 1;
    const currentWeekMonday = new Date(today);
    currentWeekMonday.setDate(today.getDate() - daysUntilMonday);
    return currentWeekMonday;
  }

  function handleOkClick() {
    setShowReservationTable(true);
  }

  function handlePrevWeek() {
    const prevMonday = new Date(currentMonday);
    prevMonday.setDate(prevMonday.getDate() - 7);
    setCurrentMonday(prevMonday);
  }

  function handleNextWeek() {
    const nextMonday = new Date(currentMonday);
    nextMonday.setDate(nextMonday.getDate() + 7);
    setCurrentMonday(nextMonday);
  }

  return (
      <div>
        <h2>Reservation Page</h2>
        <div>
          <label>Number of Guests: </label>
          <input
              type="number"
              value={numberOfGuests}
              onChange={(e) => setNumberOfGuests(e.target.value)}
          />
          <button
              style={{ padding: "5px", fontSize: "15px" }}
              onClick={handleOkClick}>OK
          </button>
        </div>
        {showReservationTable && (
            <div>
              <div>
                <button
                    style={{ padding: "5px", fontSize: "15px" }}
                    onClick={handlePrevWeek}>&lt; Prev Week
                </button>
                <button
                    style={{ padding: "5px", fontSize: "15px" }}
                    onClick={handleNextWeek}>Next Week &gt;
                </button>
              </div>
              <h2>Week of {currentMonday.toLocaleDateString()}</h2>
              <ReservationTable
                  startDate={currentMonday}
                  openingTime={12}
                  closingTime={18}
              />
            </div>
        )}
      </div>
  );
>>>>>>> development
}

export default ReservationPage;

