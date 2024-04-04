import { jwtDecode } from "jwt-decode";
import { useEffect, useState } from "react";
import TableEditor from "./TableEditor.jsx";
import TableReservationList from "./TableReservationList.jsx";

function TableList() {
    const [diningSpots, setDiningSpots] = useState([]);
    const [selectedSpotId, setSelectedSpotId] = useState(null);
    const [restaurantDetails, setRestaurantDetails] = useState(null);
    const [diningSpot, setDiningSpot] = useState({ name: "", capacity: 0 });

    async function handleSubmit(event) {
        event.preventDefault();
        try {
            const token = localStorage.getItem("token");
            const addTable = await fetch(`/api/restaurants/diningSpot/${restaurantDetails.publicId}`, {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(diningSpot)
            });
            if (!addTable.ok) {
                throw new Error("Failed to add table");
            }else if (addTable.ok){
                fetchTables();
            }
            const response = await addTable.json();
            // Handle the response if necessary
        } catch (error) {
            console.error(error);
        }
    }

    const fetchDetails = async () => {
        try {
            const token = localStorage.getItem("token");
            const decodedToken = jwtDecode(token);
            const email = decodedToken.sub;
            const response = await fetch(`/api/restaurants/email/${email}`, {
                headers: { "Authorization": `Bearer ${token}` },
            });
            if (!response.ok) {
                throw new Error("Failed to fetch restaurant");
            }
            const details = await response.json();
            setRestaurantDetails(details);
        } catch (error) {
            console.error(error);
        }
    };

    const fetchTables = async () => {
        try {
            const token = localStorage.getItem("token");
            const decodedToken = jwtDecode(token);
            const email = decodedToken.sub;
            const response = await fetch(`/api/restaurants/diningSpot/${email}`, {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error("Failed to fetch reservations");
            }

            const tableData = await response.json();
            setDiningSpots(tableData);
        } catch (error) {
            console.error("Error fetching reservations:", error);
        }
    };

    useEffect(() => {
        fetchTables();
        fetchDetails();
    }, [selectedSpotId]);

    return (
        <div className={"tableServiceContainer"}>
            <div>
                <h3>Tables</h3>
                <div className={"tableCardContainer"}>
                    {diningSpots && diningSpots.map((diningSpot) => (
                        <div className={"tableCard"} onClick={() => setSelectedSpotId(diningSpot.publicId)} key={diningSpot.id}>
                            <div className={"tableDetail"}>
                                {diningSpot.name.toUpperCase()}
                            </div>
                            <div className={"tableDetail"}>
                                {diningSpot.capacity}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            <div className={"tableContainer"}>
                <TableEditor onSubmit={handleSubmit} onChange={setDiningSpot}></TableEditor>
                <TableReservationList diningSpotId={selectedSpotId}></TableReservationList>
            </div>
        </div>
    );
}

export default TableList;