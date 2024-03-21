import { useState } from "react";
import _RestaurantNav from "../components/_restaurant_components/_RestaurantNav.jsx";
import { useNavigate, useParams } from "react-router-dom";
import _CustomerNavbar from '../components/_customer_components/_CustomerNavbar.jsx';


function _AddTables(){
    const [table, setTable] = useState({available: true});
    const {id} = useParams();
    const navigate = useNavigate();

    async function handleSubmit(event){
        event.preventDefault();
        const post = await fetch(`/api/table/${id}`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(table)
        });

        const response = await post.json();
        if(response.status === 'added'){
            navigate(`/restaurant/myrestaurant/${id}`);
        }
    }

    return(
        <div className="add-table restaurant-display">
            <_CustomerNavbar />
            <_RestaurantNav />
            <form onSubmit={handleSubmit}>
                <label>Table id: </label>
                <input type="number" value={table.id} required onChange={(event) => {setTable(prev => ({...prev, tableId: Number(event.target.value)}))}}/>
                <br />
                <label>Seats count: </label>
                <input type="number" required value={table.seats} onChange={(event) => {setTable(prev => ({...prev, seats: event.target.value}))}}/>
                <br />
                <button>Add table</button>
            </form>
        </div>
    )
}

export default _AddTables;