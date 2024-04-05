
function TableEditor({ onSubmit, onChange }) {
    return (
        <div className={"tableEditorContainer"}>
            <h3>Add new table</h3>
            <form style={{ width: "100%" }} onSubmit={(event) => onSubmit(event)}>
                <div>
                    <input
                        style={{ width: "90%" }}
                        type={"text"}
                        name={"name"}
                        placeholder={"Table name"}
                        onChange={(event) => {
                            onChange((prev) => ({ ...prev, name: event.target.value }));
                        }}
                    />
                </div>
                <div>
                    <input
                        style={{ width: "90%" }}
                        type={"number"}
                        name={"seats"}
                        placeholder={"Number of seats"}
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