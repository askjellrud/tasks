import {useState} from "react";

type Props = {
    newTaskCreated: () => void;
}

const NewTask = ({ newTaskCreated } : Props) => {
    const [title, setTitle] = useState("");

    const createTask = async () => {
        try {
            const response = await fetch("http://localhost:8080/tasks", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({title}),
            });

            if (!response.ok) {
                throw new Error("Failed to create task");
            }

            newTaskCreated();
            setTitle("");
        } catch (error) {
            console.error(error);
        }
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        createTask();
    };

    return (
        <form onSubmit={handleSubmit} style={{padding: "10px"}}>
            <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="Enter new task"
                required
            />

            <button style={{marginLeft: "10px"}} type="submit">Add Task</button>
        </form>
    );
};

export default NewTask;
