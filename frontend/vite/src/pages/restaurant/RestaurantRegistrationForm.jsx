import {useState} from "react";
import {useNavigate} from "react-router-dom";

function RestaurantRegistrationForm(){
    //email, password, firstname, lastname, phoneNumber

    const [restaurant, setRestaurant] = useState({});
    const navigate = useNavigate();

    //TODO: USE WRAPPER FETCH;
    async function handleSubmit(event) {
        event.preventDefault()
        const register =  await fetch('/api/restaurants', {
            method: "POST",
            headers: {
                "Content-type" : "application/json"
            } ,
            body: JSON.stringify(restaurant)
        });

        const response = await register.json();
        if(response.status === "401"){
            navigate("/");
        }
        navigate("/login");
    }

    return(
        <div>
            <form onSubmit={(event)=>handleSubmit(event)}>
                <div>
                    <label>Name</label>
                    <input type={"text"} name={"name"} value={restaurant.name} onChange={(event) =>
                    {setRestaurant(prev => ({...prev, name: event.target.value}))}}/>
                </div>

                <div>
                    <label>e-mail</label>
                    <input type={"email"} name={"email"} value={restaurant.email} onChange={(event) =>
                    {setRestaurant(prev => ({...prev, email: event.target.value}))}}/>
                </div>

                <div>
                    <label>Password</label>
                    <input type={"password"} name={"password"} value={restaurant.password} onChange={(event) =>
                    {setRestaurant(prev => ({...prev, password: event.target.value}))}}/>
                </div>

                <div>
                    <label>Phone number</label>
                    <input type={"number"} name={"phoneNumber"} value={restaurant.phoneNumber} onChange={(event) =>
                    {setRestaurant(prev => ({...prev, phoneNumber: event.target.value}))}}/>
                </div>

                <div>
                    <label>Address</label>
                    <input type={"text"} name={"address"} value={restaurant.address} onChange={(event) =>
                    {setRestaurant(prev => ({...prev, address: event.target.value}))}}/>
                </div>

                <div>
                    <button type={"submit"}>SUBMIT</button>
                </div>
            </form>
        </div>
    );
}

export default RestaurantRegistrationForm;
