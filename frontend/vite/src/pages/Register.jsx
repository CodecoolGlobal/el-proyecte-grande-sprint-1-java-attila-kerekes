import {Link, Outlet} from "react-router-dom";
import customerPic from "./photos/customer.jpg"
import restaurantPic from "./photos/restaurant.jpg"
import {useEffect, useState} from "react";

export default function Register() {
    const [customer, setCustomer] = useState(false)
    const [restaurant, setRestaurant] = useState(false)


    useEffect(() => {
        setCustomer(false)
        setRestaurant(false)
    }, []);

    return (
        <div>
            <div>
                <h2>Register</h2>
            </div>


            <div className={"RegistrationPicsContainer"}>

                <div> {restaurant ?
                    <Outlet/> :

                    <div className={"PicContainer"}>
                        <div>As customer</div>
                        <Link to={`customer`}> <img onClick={() => {
                            setCustomer(true)
                        }} className={"RegistrationPic"} src={customerPic}></img></Link>
                    </div>
                }
                </div>


                <div>{customer ? <Outlet/> :

                    <div className={"PicContainer"}>
                        <div>As restaurant</div>
                        <Link to={`restaurant`}><img onClick={() => {
                            setRestaurant(true)
                        }} className={"RegistrationPic"} src={restaurantPic}></img></Link>
                    </div>
                }
                </div>
            </div>
        </div>
    )
}