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

        const response = await register.json();
        console.log("asd")
        if(response.statusCode.statusCode === "401"){
            navigate("/");
        }
    }




    return(
        <div>
            <form onSubmit={(event)=>handleSubmit(event)}>
                <div>
                <label>FirstName</label>
                <input type={"text"} name={"firstName"} value={customer.firstName} onChange={(event) => {setCustomer(prev => ({...prev, firstName: event.target.value}))}}/>
                </div>


                <div>
                <label>LastName</label>
                <input type={"text"} name={"lastName"} value={customer.lastName} onChange={(event) => {setCustomer(prev => ({...prev, lastName: event.target.value}))}}/>
                </div>

                <div>
                <label>e-mail</label>
                <input type={"text"} name={"email"} value={customer.email} onChange={(event) => {setCustomer(prev => ({...prev, email: event.target.value}))}}/>
                </div>

                <div>
                <label>Password</label>
                <input type={"text"} name={"password"} value={customer.password} onChange={(event) => {setCustomer(prev => ({...prev, password: event.target.value}))}}/>
                </div>

                <div>
                <label>Phone number</label>
                    <input type={"text"} name={"phoneNumber"} value={customer.phoneNumber} onChange={(event) => {setCustomer(prev => ({...prev, phoneNumber: event.target.value}))}}/>
                </div>

                <div>
                    <button type={"submit"}>SUBMIT</button>
                </div>
            </form>
        </div>
    );


}


export default CustomerRegistrationForm;
