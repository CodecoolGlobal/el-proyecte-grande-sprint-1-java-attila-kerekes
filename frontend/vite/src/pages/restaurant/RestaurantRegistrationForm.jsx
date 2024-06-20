import {useState} from "react";
import {useNavigate} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';


function RestaurantRegistrationForm() {
    //email, password, firstname, lastname, phoneNumber

    const [restaurant, setRestaurant] = useState({});
    const navigate = useNavigate();

    //TODO: USE WRAPPER FETCH;
    async function handleSubmit(event) {
        event.preventDefault()
        const register = await fetch('/api/restaurants', {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(restaurant)
        });

        const response = await register.json();
        if (response.status === "401") {
            navigate("/");
        }
        navigate("/login");
    }

    return (
        <div className={"input-group"}>
            <div className={"card h-75 p-2 shadow mb-5 bg-body rounded"}>
                <div className={"card-body"}>

                    <form onSubmit={(event) => handleSubmit(event)}>
                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"} placeholder={"Name"} type={"text"} name={"name"}
                                   value={restaurant.name} onChange={(event) => {
                                setRestaurant(prev => ({...prev, name: event.target.value}))
                            }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"}
                                   placeholder={"e-mail"}
                                   type={"email"} name={"email"}
                                   value={restaurant.email}
                                   onChange={(event) => {
                                       setRestaurant(prev => ({...prev, email: event.target.value}))
                                   }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"}
                                   placeholder="Password"
                                   type={"password"}
                                   name={"password"}
                                   value={restaurant.password} onChange={(event) => {
                                setRestaurant(prev => ({...prev, password: event.target.value}))
                            }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"}
                                   placeholder="Phone number"
                                   type={"number"}
                                   name={"phoneNumber"}
                                   value={restaurant.phoneNumber}
                                   onChange={(event) => {
                                       setRestaurant(prev => ({...prev, phoneNumber: event.target.value}))
                                   }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"}
                                   placeholder="Address"
                                   type={"text"}
                                   name={"address"}
                                   value={restaurant.address} onChange={(event) => {
                                setRestaurant(prev => ({...prev, address: event.target.value}))
                            }}/>
                        </div>

                        <div>
                            <button className={"btn btn-primary"} type={"submit"}>SUBMIT</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>


    );
}

export default RestaurantRegistrationForm;
