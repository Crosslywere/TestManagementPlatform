import { format } from "date-fns";
import {
  useEffect,
  useState,
  type ChangeEvent,
  type FormEvent,
  type ReactNode,
} from "react";
import { useNavigate } from "react-router-dom";
import { classroomService } from "../service/api";

export interface Classroom {
  id: string;
  name: string;
  domains: string[];
  creationTime: string;
  joinable: boolean;
}

export const CreateClassroom = (): ReactNode => {
  const [formData, setFormData] = useState({ name: "", domains: "" });
  const navigate = useNavigate();
  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
    classroomService.createClassroom(formData.name, formData.domains);
    setFormData({ name: "", domains: "" });
    navigate("/table");
  };
  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3 input-group">
        <label htmlFor="name" className="input-group-text">
          Name
        </label>
        <input
          type="text"
          id="name"
          className="form-control"
          value={formData.name}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            setFormData({ ...formData, name: e.target.value })
          }
          required
        />
      </div>
      <div className="mb-3 input-group">
        <label htmlFor="domains" className="input-group-text">
          Domains (seperated by "<code>;</code>")
        </label>
        <input
          type="text"
          id="domains"
          className="form-control"
          value={formData.domains}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            setFormData({ ...formData, domains: e.target.value })
          }
          placeholder="@email.com; @email2.com"
        />
      </div>
      <button type="submit" className="btn btn-outline-primary">
        Create
      </button>
    </form>
  );
};

export const ClassroomTable = (): ReactNode => {
  const [classrooms, setClassrooms] = useState<Classroom[]>([] as Classroom[]);
  useEffect(() => {
    setTimeout(() => {
      classroomService.getClassrooms().then(setClassrooms).catch(console.error);
    }, 5000);
  }, []);
  return (
    <table className="table table-striped">
      <thead>
        <tr>
          <th>Name</th>
          <th>Domains</th>
          <th>When Created</th>
        </tr>
      </thead>
      <tbody>
        {classrooms.length > 0 ? (
          classrooms.map((classroom): ReactNode => {
            return (
              <tr
                key={classroom.id}
                className={
                  classroom.joinable ? "table-success" : "table-danger"
                }
              >
                <td>{classroom.name}</td>
                <td>{classroom.domains.map((domain) => `${domain}; `)}</td>
                <td>
                  {format(classroom.creationTime, "h:mm aa EEEE do MMMM yyyy")}
                </td>
              </tr>
            );
          })
        ) : (
          <tr>
            <td colSpan={4} className="text-center">
              No available data
            </td>
          </tr>
        )}
      </tbody>
    </table>
  );
};
