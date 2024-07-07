import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../../config/axiosConfig";
import "./Profile.css"; // Assuming you have a CSS file for styling

function Profile() {
  const [userData, setUserData] = useState({});
  const [agents, setAgents] = useState([]);
  const [currentAgentIndex, setCurrentAgentIndex] = useState(0);
  const navigate = useNavigate();

  const fetchProfile = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/profile");
      setUserData(response.data);
      setAgents(response.data.agents || []);
    } catch (error) {
      console.error("There was an error fetching the profile!", error);
    }
  };

  useEffect(() => {
    fetchProfile();
  }, []);

  const nextAgent = () => {
    setCurrentAgentIndex((prevIndex) => (prevIndex + 1) % agents.length);
  };

  const previousAgent = () => {
    setCurrentAgentIndex(
      (prevIndex) => (prevIndex - 1 + agents.length) % agents.length
    );
  };

  const currentAgent = agents[currentAgentIndex] || {};

  const handleFileUpload = async (event) => {
    const file = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file);
  
    try {
      const response = await axios.patch(`http://localhost:8080/api/agents/${currentAgent.uuid}/upload`, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      });
      console.log(response.data);
      alert("File uploaded successfully!");
  
      // Refresh profile data after successful upload
      fetchProfile();
  
    } catch (error) {
      console.error("Error uploading file:", error);
      alert("Error uploading file. Please try again.");
    }
  };

  return (
    <div className="profile-container">
      <div className="profile-left">
        <h2>{userData.username}</h2>
        <h3>{userData.role}</h3>
        <button onClick={() => navigate("/add-agent")}>Create New Agent</button>
      </div>
      <div className="profile-right">
        {agents.length > 0 ? (
          <div className="agent-carousel">
            <button onClick={previousAgent}>Previous</button>
            <div className="agent-details">
              <h3>{currentAgent.name}</h3>
              <p>Language: {currentAgent.language}</p>
              <p>Expertise: {currentAgent.expertise}</p>
              <p>Tone: {currentAgent.tone}</p>
              <p>Usage: {currentAgent.usage}</p>
              <p>Created At: {currentAgent.createdAt}</p>
              <p>Personality:</p>
              {currentAgent.traits && (
                <div>
                  <p> Empathy: {currentAgent.traits.empathy}</p>
                  <p>Reliability: {currentAgent.traits.reliability}</p>
                  <p>Confidence: {currentAgent.traits.confidence}</p>
                  <p>
                    Attention to Detail:{" "}
                    {currentAgent.traits.attentionToDetail}
                  </p>
                  <p>Adaptability: {currentAgent.traits.adaptability}</p>
                  <p>Patience: {currentAgent.traits.patience}</p>
                  <p>
                    Communication: {currentAgent.traits.communication}
                  </p>
                  <p>Innovation: {currentAgent.traits.innovation}</p>
                  <p>Resilience: {currentAgent.traits.resilience}</p>
                  <p>
                    Collaboration: {currentAgent.traits.collaboration}
                  </p>
                </div>
              )}
               <div className="file-upload">
          <label>Upload File:</label>
          <input type="file" onChange={handleFileUpload} accept=".pdf,.doc,.docx,.txt" />
        </div>
            </div>
            <button onClick={nextAgent}>Next</button>
          </div>
        ) : (
          <p>No agents found</p>
        )}
      </div>
    </div>
  );
}

export default Profile;
