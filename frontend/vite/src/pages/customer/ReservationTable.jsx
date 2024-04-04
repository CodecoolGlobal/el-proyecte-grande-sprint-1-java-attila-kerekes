
function ReservationTable({ startDate, openingTime, closingTime }) {

  const generateDateStrings = (startDate) => {
    const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    const dateStrings = [];
    for (let i = 0; i < 7; i++) {
      const currentDate = new Date(startDate);
      currentDate.setDate(startDate.getDate() + i);
      const dateString = `${days[currentDate.getDay()]} ${currentDate.getDate()}`;
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
        {timeSlots.map((time, index) => (
          <tr key={index}>
            <td>{time}</td>
            {dates.map((_, index) => (
              <td key={index}>
                <button
                    style={{ padding: "5px", fontSize: "15px" }}
                    onClick={() => console.log(`Date: ${dates[index]}, Time: ${time}`)}
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

export default ReservationTable;
