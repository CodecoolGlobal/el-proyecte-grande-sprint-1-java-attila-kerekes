import {useState} from 'react';
import ReservationForm from "./ReservationFrom.jsx";

function ReservationPage() {

    const [updateSuccess, setUpdateSuccess] = useState(null);

    return (
        <div className="restaurantReservation">
            {updateSuccess === null && <h4>Book your table:</h4>}
            {updateSuccess === null ? <ReservationForm onUpdate={(isSuccess) => {
                    setUpdateSuccess(isSuccess)
                }}/>
                :
                updateSuccess === true
                    ?
                    <h4>The booking was successful!</h4>
                    :
                    <h4>Sorry! There is no available table with your requirements.</h4>}

        </div>
    )
}

export default ReservationPage;
