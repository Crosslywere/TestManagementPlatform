// import { useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";
// import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ClassroomTable, CreateClassroom } from "./component/Classroom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/create"
          element={
            <div className="container my-5">
              <CreateClassroom />
            </div>
          }
        />
        <Route
          path="/table"
          element={
            <div className="container my-5">
              <ClassroomTable />
            </div>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
