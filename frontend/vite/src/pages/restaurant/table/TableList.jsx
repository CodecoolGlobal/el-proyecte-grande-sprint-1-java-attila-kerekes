import {jwtDecode} from "jwt-decode";
import {useEffect, useState} from "react"
import TableEditor from "./TableEditor.jsx";
import TableReservationList from "./TableReservationList.jsx";

function TableList() {
    const [diningSpots, setDiningSpots] = useState([]);
    const [selectedSpotId, setSelectedSpotId] = useState(null)

    useEffect(() => {
        const fetchTables = async () => {
            try {
                const token = localStorage.getItem('token');
                const decodedToken = jwtDecode(token);
                const email = decodedToken.sub;
                const response = await fetch(`/api/restaurants/diningSpot/${email}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (!response.ok) {
                    throw new Error('Failed to fetch reservations');
                }

                const tableData = await response.json();
                setDiningSpots(tableData);
            } catch (error) {
                console.error('Error fetching reservations:', error);
            }
        };
        fetchTables();
    }, []);


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
            <TableEditor></TableEditor>
            <TableReservationList diningSpotId={selectedSpotId}></TableReservationList>
            </div>
        </div>
    )
}

export default TableList;
