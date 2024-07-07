// AddAgent.js
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../../config/axiosConfig";
import "./AddAgent.css"; // Assuming you have a CSS file for styling

function AddAgent() {
  const navigate = useNavigate();
  const [agentData, setAgentData] = useState({
    name: "",
    language: "English",
    expertise: "",
    tone: "",
    usage: "",
    traits: {
      empathy: "",
      reliability: "",
      confidence: "",
      attentionToDetail: "",
      adaptability: "",
      patience: "",
      communication: "",
      innovation: "",
      resilience: "",
      collaboration: ""
    }
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setAgentData({
      ...agentData,
      [name]: value,
    });
  };

  const handleTraitsChange = (event) => {
    const { name, value } = event.target;
    setAgentData({
      ...agentData,
      traits: {
        ...agentData.traits,
        [name]: value,
      },
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8080/api/agents", agentData)
      .then((response) => {
        console.log("Agent created successfully", response.data);
        navigate("/profile/profilepage");
      })
      .catch((error) => {
        console.error("There was an error creating the agent!", error);
      });
  };

  return (
    <div className="add-agent-container">
      <h2>Create your agent</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input
            type="text"
            name="name"
            value={agentData.name}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Language:
          <input
            type="text"
            name="language"
            value={agentData.language}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Expertise:
          <input
            type="text"
            name="expertise"
            value={agentData.expertise}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Tone:
          <input
            type="text"
            name="tone"
            value={agentData.tone}
            onChange={handleInputChange}
          />
        </label>
        <label>
          Usage:
          <input
            type="text"
            name="usage"
            value={agentData.usage}
            onChange={handleInputChange}
          />
        </label>
        <div className="traits-section">
          <h3>Traits</h3>
          {Object.keys(agentData.traits).map((trait) => (
            <label key={trait}>
              {trait.charAt(0).toUpperCase() + trait.slice(1)}:
              <input
                type="text"
                name={trait}
                value={agentData.traits[trait]}
                onChange={handleTraitsChange}
              />
            </label>
          ))}
        </div>
        <button type="submit">Create Agent</button>
      </form>
    </div>
  );
}

export default AddAgent;
