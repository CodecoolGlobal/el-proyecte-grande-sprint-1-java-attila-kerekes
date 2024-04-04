
function TableEditor({ onSubmit, onChange }) {
    return (
        <div className={"tableEditorContainer"}>
            <form  style={{ width: "100%" }} onSubmit={(event) => onSubmit(event)}>
                <div>
                    <input
                        placeholder={"Table name"}
                        style={{ width: "90%" }}
                        type={"text"}
                        name={"name"}
                        onChange={(event) => {
                            onChange((prev) => ({ ...prev, name: event.target.value }));
                        }}
                    />
                </div>
                <div>
                    <input
                        placeholder={"Number of seats"}
                        style={{ width: "90%" }}
                        type={"number"}
                        name={"seats"}
                        onChange={(event) => {
                            onChange((prev) => ({ ...prev, capacity: parseInt(event.target.value) }));
                        }}
                    />
                </div>
                <button type="submit">Save</button>
            </form>
        </div>
    );
}

export default TableEditor;