import {useState} from "react";

function TableEditor() {
    const [diningSpot, setDiningSpot] = useState({name: "", capacity: 0});


    return (
        <div className={"tableEditorContainer"}>
            <form style={{width: "100%"}} onSubmit={(event) => (event)}>
                <div>
                    <input style={{width: "90%"}}
                           type={"text"}
                           name={"name"}
                           value={diningSpot.name}
                           onChange={(event) => {
                               setDiningSpot(prev => ({...prev, name: event.target.value}))
                           }}/>
                </div>
                <div>
                    <input style={{width: "90%"}}
                           type={"number"}
                           name={"seats"}
                           value={diningSpot.capacity}
                           onChange={(event) => {
                               setDiningSpot(prev => ({...prev, capacity: event.target.value}))
                           }}/>
                </div>
                <button>Save</button>
            </form>
        </div>
    )
}

export default TableEditor;