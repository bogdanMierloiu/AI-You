import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { MDBContainer, MDBInput, MDBBtn } from "mdb-react-ui-kit";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "./LoginPage.css"; // Import the CSS file

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const webChromeMetamask =
    "https://chromewebstore.google.com/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn?pli=1";
  const backendUrl = "http://localhost:8080";
  const navigate = useNavigate();

  const handleInternalLogin = async () => {
    try {
      if (!username || !password) {
        setError("Please enter both username and password.");
        return;
      }

      const response = await axios.post(backendUrl + "/auth/signin", {
        username,
        password,
      });
      console.log("Login successful:", response.data);
      localStorage.clear();
      localStorage.setItem("token", response.data.jwt);
      navigate("/dashboard");
    } catch (error) {
      console.error(
        "Login failed:",
        error.response ? error.response.data : error.message
      );
      setError("Invalid username or password.");
    }
  };

  const handleMetamaskLogin = async () => {
    if (!window.ethereum) {
      window.location.href = webChromeMetamask;
      console.error("Please install MetaMask");
      return;
    }

    try {
      // Prompt user to connect MetaMask and obtain the user's address
      const accounts = await window.ethereum.request({
        method: "eth_requestAccounts",
      });
      const address = accounts[0];

      // Receive nonce and sign a message
      const nonce = await getNonce(address);
      console.log(nonce);
      const message = `Signing a message to login: ${nonce}`;
      const signature = await window.ethereum.request({
        method: "personal_sign",
        params: [message, address],
      });

      // Login with signature
      const response = await sendLoginData(address, signature);
      if (response.ok) {
        const data = await response.json();
        localStorage.clear();
        localStorage.setItem("token", data.jwt);
        navigate("/dashboard");
      } else {
        console.error("Login failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error during login with MetaMask:", error);
    }
  };

  const getNonce = async (address) => {
    const response = await fetch(`${backendUrl}/nonce/${address}`);
    const nonce = await response.text();
    return nonce;
  };

  const sendLoginData = async (address, signature) => {
    const response = await fetch("http://localhost:8080/metamask/login", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({
        address: encodeURIComponent(address),
        signature: encodeURIComponent(signature),
      }),
    });
    return response;
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault(); // Preveniți comportamentul default al formularului
    await handleInternalLogin();
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">Sign In</h2>
        <form className="login-form" onSubmit={handleFormSubmit}>
          <div className="form-group">
            <label htmlFor="email" className="form-label">
              Email Address
            </label>
            <input
              type="email"
              id="email"
              className="form-control"
              placeholder="Enter your email"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password" className="form-label">
              Password
            </label>
            <input
              type="password"
              id="password"
              className="form-control"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary"
            onClick={() => handleInternalLogin()}
          >
            Sign In
          </button>
          <button
            type="submit"
            className="btn btn-primary"
            style={{ marginTop: "10px", background: "#F6851B" }}
            onClick={() => handleMetamaskLogin()}
          >
            Connect with MetaMask
          </button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
