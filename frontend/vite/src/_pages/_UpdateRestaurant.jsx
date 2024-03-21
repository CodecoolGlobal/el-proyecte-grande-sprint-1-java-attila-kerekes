import { useState } from 'react';
import _RestaurantForm from '../components/_restaurant_components/_RestaurantForm.jsx';
import _RestaurantNav from '../components/_restaurant_components/_RestaurantNav.jsx';
import _CustomerNavbar from '../components/_customer_components/_CustomerNavbar.jsx';

function _UpdateRestaurant(){

    const [updateSuccess, setUpdateSuccess] = useState(null);

    return(
        <div className="update-restaurant restaurant-display">
            <_CustomerNavbar />
            <_RestaurantNav />
            {updateSuccess === null && <h1>Update restaurant information: </h1>}
            {updateSuccess === null ? <_RestaurantForm onUpdate={(isSuccess) => {setUpdateSuccess(isSuccess)}}/> : updateSuccess === true  ? <h3>The updates was successfull!</h3> : <h3>Try again! Something went wrong!</h3>}

        </div>
    )
}

export default _UpdateRestaurant;

