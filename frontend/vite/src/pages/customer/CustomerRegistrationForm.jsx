import {useState} from "react";
import {useNavigate} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

function CustomerRegistrationForm() {
    //email, password, firstname, lastname, phoneNumber

    const [customer, setCustomer] = useState({});
    const navigate = useNavigate();

    //TODO: USE WRAPPER FETCH;
    async function handleSubmit(event) {
        event.preventDefault()
        const register = await fetch('/api/customers', {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(customer)
        });
        const response = await register;
        if (response.status === 200) {
            navigate("/login");
        }
        if (response.status === 401) {
            navigate("/");
        }
        navigate("/login");
    }

    return (
        <div className={"input-group"}>
            <div className={"card w-75 h-75 p-2 shadow mb-5 bg-body rounded"}>
                <div className={"card-body"}>

                    <form onSubmit={(event) => handleSubmit(event)}>
                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"} placeholder={"First name"} type={"text"} name={"firstName"}
                                   value={customer.firstName} onChange={(event) => {
                                setCustomer(prev => ({...prev, firstName: event.target.value}))
                            }}/>
                        </div>


                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"} placeholder={"Last name"} type={"text"} name={"lastName"} value={customer.lastName}
                                   onChange={(event) => {
                                       setCustomer(prev => ({...prev, lastName: event.target.value}))
                                   }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"} placeholder={"e-mail"} type={"email"} name={"email"} value={customer.email}
                                   onChange={(event) => {
                                       setCustomer(prev => ({...prev, email: event.target.value}))
                                   }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <input className={"form-control"} placeholder="Password" type={"password"} name={"password"}
                                   value={customer.password} onChange={(event) => {
                                setCustomer(prev => ({...prev, password: event.target.value}))
                            }}/>
                        </div>

                        <div className={"form-outline mb-2"}>

                            <input className={"form-control"} placeholder="Phone number" type={"number"} name={"phoneNumber"}
                                   value={customer.phoneNumber} onChange={(event) => {
                                setCustomer(prev => ({...prev, phoneNumber: event.target.value}))
                            }}/>
                        </div>

                        <div className={"form-outline mb-2"}>
                            <button className={"btn btn-primary"} type={"submit"}>SUBMIT</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default CustomerRegistrationForm;
