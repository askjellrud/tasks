export interface Task {
    id: number;
    title: string;
    completed: boolean;
}

type Props = {
    task: Task
}

const TaskRow = ({ task }: Props) => (
    <div>
        <strong>{task.title}</strong> - {task.completed ? 'Completed' : 'Pending'}
    </div>
);

export default TaskRow