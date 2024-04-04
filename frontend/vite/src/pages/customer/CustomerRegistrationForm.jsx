import {useState} from "react";
import {useNavigate} from "react-router-dom";

function CustomerRegistrationForm(){
    //email, password, firstname, lastname, phoneNumber

    const [customer, setCustomer] = useState({});
    const navigate = useNavigate();

    //TODO: USE WRAPPER FETCH;
    async function handleSubmit(event) {
        event.preventDefault()
        const register =  await fetch('/api/customers', {
            method: "POST",
            headers: {
                "Content-type" : "application/json"
            } ,
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

    return(
        <div>
            <form onSubmit={(event)=>handleSubmit(event)}>
                <div>
                <input placeholder={"First name"} type={"text"} name={"firstName"} value={customer.firstName} onChange={(event) =>
                {setCustomer(prev => ({...prev, firstName: event.target.value}))}}/>
                </div>


                <div>
                <input placeholder={"Last name"} type={"text"} name={"lastName"} value={customer.lastName} onChange={(event) =>
                {setCustomer(prev => ({...prev, lastName: event.target.value}))}}/>
                </div>

                <div>
                <input placeholder={"e-mail"} type={"email"} name={"email"} value={customer.email} onChange={(event) =>
                {setCustomer(prev => ({...prev, email: event.target.value}))}}/>
                </div>

                <div>
                <input placeholder="Password" type={"password"} name={"password"} value={customer.password} onChange={(event) =>
                {setCustomer(prev => ({...prev, password: event.target.value}))}}/>
                </div>

                <div>

                    <input placeholder="Phone number" type={"number"} name={"phoneNumber"} value={customer.phoneNumber} onChange={(event) =>
                    {setCustomer(prev => ({...prev, phoneNumber: event.target.value}))}}/>
                </div>

                <div>
                    <button type={"submit"}>SUBMIT</button>
                </div>
            </form>
        </div>
    );
}

export default CustomerRegistrationForm;
