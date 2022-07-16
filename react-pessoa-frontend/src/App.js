import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import PageShow from "./components/PageShow";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route exact path="home" element={<PageShow />} />
          <Route path="/" element={<Navigate to="/home" />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
