import {Link, Outlet} from "react-router-dom";
import customerPic from "./photos/customer.jpg"
import restaurantPic from "./photos/restaurant.jpg"
import {useEffect, useState} from "react";
import {Container} from "react-bootstrap";

export default function Register() {
    const [customer, setCustomer] = useState(false)
    const [restaurant, setRestaurant] = useState(false)


    useEffect(() => {
        setCustomer(false)
        setRestaurant(false)
    }, []);

    return (
        <div>
            <Container className={"mb-4 d-flex justify-content-xl-center"}>
                <h2>Register</h2>
            </Container>

            <Container className={"d-inline-flex p-2"}>
                <Container> {restaurant ?
                    <Outlet/> :

                    <Container className={"mb-4"}>
                        <div>As customer</div>
                        <Link to={`customer`}>
                            <img onClick={() => {
                                setCustomer(true)
                            }}
                                 className={"rounded float-left"}
                                 src={customerPic}
                                 alt={"customer"}
                                 style={{width: "400px"}}>
                            </img>
                        </Link>
                    </Container>
                }
                </Container>

                <Container>{customer ? <Outlet/> :

                    <Container className={"mb-4"}>
                        <div>As restaurant</div>
                        <Link to={`restaurant`}>
                            <img onClick={() => {
                                setRestaurant(true)
                            }}
                                 className={"rounded float-right"}
                                 src={restaurantPic}
                                 alt={"restaurant"}
                                 style={{width: "400px"}}>
                            </img></Link>
                    </Container>
                }
                </Container>
            </Container>
        </div>
    )
}