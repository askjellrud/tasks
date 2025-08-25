import {useEffect, useState} from "react";
import TaskRow, {type Task} from "./TaskRow.tsx";
import NewTask from "./NewTask.tsx";

const TaskList = () => {

    const [tasks, setTasks] = useState<Task[]>([]);

    const refreshTasks = () => {
        fetch('http://localhost:8080/tasks')
            .then(result => {
                if (!result.ok) throw new Error('HTTP error!');
                return result.json();
            })
            .then((data: Task[]) => setTasks(data));
    }

    useEffect(() => {
        refreshTasks();
    }, []);

    return (
        <>
            {tasks.map(task => (
                <TaskRow key={task.id} task={task}/>
            ))}

            <NewTask newTaskCreated={() => refreshTasks()}/>
        </>
    );
}

export default TaskList