import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./components/Login/LoginPage";
import SignupPage from "./components/SignUpPage";
import Dashboard from "./components/Dashboard";
import MetaMaskLogin from "./components/Login/MetamaskLogin";
import Profile from "./components/Profile/ProfilePage";
import AddAgent from "./components/Agent/AddAgent";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/login/metamask" element={<MetaMaskLogin />} />
          <Route path="/profile/profilepage" element={<Profile />} />
		  <Route path="/add-agent" element={<AddAgent />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
