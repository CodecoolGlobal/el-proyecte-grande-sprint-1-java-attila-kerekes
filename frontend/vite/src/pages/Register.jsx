import customerPic from "./photos/customer.jpg"
import restaurantPic from "./photos/restaurant.jpg"
import CustomerRegistrationForm from "./customer/CustomerRegistrationForm.jsx";
import RestaurantRegistrationForm from "./restaurant/RestaurantRegistrationForm.jsx";
import "./Style.css"
export default function Register() {


    return (
        <div className={"container d-flex align-items-center justify-content-evenly position-relative flex-wrap h-100"}>

            <div className={"card d-flex position-relative flex-row"}>
                <div className={'imgContainer'}>
                    <img className={"rounded float-right shadow"}
                         src={customerPic}
                         alt={"customer"}
                         style={{width: "400px", height: "300px"}}
                    />
                    <h2>Customer</h2>

                    <div className="content">
                        <CustomerRegistrationForm />
                    </div>
                </div>
            </div>

            <div className={"card d-flex position-relative flex-row"}>
                <div className={'imgContainer'}>
                    <img
                        className={"rounded float-right shadow"}
                        src={restaurantPic}
                        alt={"restaurant"}
                        style={{width: "400px", height: "300px"}}
                    />
                    <h2>Restaurant</h2>
                    <div className="content">
                        <RestaurantRegistrationForm/>
                    </div>
                </div>
            </div>

        </div>
    )
}