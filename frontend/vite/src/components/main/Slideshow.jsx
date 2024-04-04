import {useEffect, useState} from "react";

const [index, setIndex] = useState(0)
const pics=[];
function  Slideshow(){

    useEffect(() => {




    }, []);


    return (
        <div className="slideshow">
            <div className="slideshowSlider">
                <div className="slide"></div>
            </div>
        </div>
    );
}