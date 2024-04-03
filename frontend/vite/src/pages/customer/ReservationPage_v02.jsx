import ReservationTableFor_v02 from "./ReservationTableFor_v02.jsx";
import { useState } from "react";

function ReservationPage_v02() {
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
              <ReservationTableFor_v02
                  startDate={currentMonday}
                  openingTime={12}
                  closingTime={18}
              />
            </div>
        )}
      </div>
  );
}

export default ReservationPage_v02;

