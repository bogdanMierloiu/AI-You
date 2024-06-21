import React from 'react'; 
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; 
import LoginPage from './components/Login/LoginPage'; 
import SignupPage from './components/SignUpPage'; 
import Dashboard from "./components/Dashboard";
import MetaMaskLogin from './components/Login/MetamaskLogin'; 

function App() { 
return ( 
	<div className="App"> 
	<Router> 
		
			<Routes> 
			<Route path="/" element={<LoginPage/>} /> 
			<Route path="/signup" element={ <SignupPage/>} /> 
			<Route path = "/dashboard" element={<Dashboard/>}/>
			<Route path = "/login/metamask" element={<MetaMaskLogin/>} />
			</Routes> 
	</Router> 
	</div> 
); 
} 

export default App; 
