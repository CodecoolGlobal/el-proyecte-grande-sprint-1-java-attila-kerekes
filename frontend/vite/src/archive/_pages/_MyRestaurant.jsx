import { useEffect, useState } from "react";
import { Link, Outlet, useNavigate, useParams } from "react-router-dom";
import _RestaurantNav from "../_components/_restaurant_components/_RestaurantNav.jsx";
import _RestaurantTableElements from '../_components/_restaurant_components/_RestaurantTableElements.jsx';
import _CustomerNavbar from '../_components/_customer_components/_CustomerNavbar.jsx';


function RestaurantMain(){
    const [restaurant, setRestaurant] = useState(null);
    const [isDeleteClicked, setIsDeleteClicked] = useState(false);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`/api/restaurants/${id}`);
            const restaurantData = await response.json();
            setRestaurant(restaurantData);
        }
        fetchData();
    }, [id])


    async function handleDelete(){
        const deleteResponse = await fetch(`/api/restaurants/${id}`, {
            method: 'DELETE'
        });

        if(deleteResponse.ok){
            navigate('/');
        }
    }

    return(
        <div className="restaurantMain restaurant-display">
            <_CustomerNavbar />
            <_RestaurantNav />
            {restaurant && 
                <div className="my-restaurant">
                    <h1>Restaurant name: {restaurant.restaurantName}</h1>
                    <div className="restaurantInfo">Closing: {restaurant.closing}</div>
                    <div className="restaurantInfo">Opening: {restaurant.opening}</div>
                    <div className="restaurantInfo">Email address: {restaurant.email}</div>
                    <div className="restaurantInfo">Phone number: {restaurant.phoneNumber}</div>
                    <Link to={`/restaurant/update/${id}`}><button>Update informations</button></Link>
                    <Link><button onClick={() => {
                        setIsDeleteClicked(true);
                    }} disabled={isDeleteClicked ? true : false}>Delete my restaurant</button></Link>

                    {isDeleteClicked && 
                        <>
                            <p>Are you sure you want to delete your account?</p>
                            <div>
                                <button onClick={handleDelete}>Allow deletion</button>
                                <button onClick={() => {setIsDeleteClicked(false)}}>Cancel</button>
                            </div>
                        </>
                    }

                    <p>Tables: {restaurant.tables.length ? restaurant.tables.length : 'You don\'t have any tables!'}</p>
                    <table>
                        <tr>
                            <th>Table Id:</th>
                            <th>Seats:</th>
                            <th>Available:</th>
                        </tr>
                        {restaurant.tables.map(table => <_RestaurantTableElements key={table.id} table={table}/>)}
                    </table>
                </div>
            }
        </div>
    )
}

export default RestaurantMain;