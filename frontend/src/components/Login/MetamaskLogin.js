import React from 'react';
import { useNavigate } from 'react-router-dom'; 

const MetaMaskLogin = () => {

    const history = useNavigate();

    const login = async () => {
        if (!window.ethereum) {
            console.error('Please install MetaMask');
            return;
        }

        try {
            // Prompt user to connect MetaMask
            const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
            const address = accounts[0];

            // Receive nonce and sign a message
            const nonce = await getNonce(address);
            const message = `Signing a message to login: ${nonce}`;
            const signature = await window.ethereum.request({ method: 'personal_sign', params: [message, address] });

            // Login with signature
            await sendLoginData(address, signature);
        } catch (error) {
            console.error('Error during login with MetaMask:', error);
        }
    };

    const getNonce = async (address) => {
        const response = await fetch(`/nonce/${address}`);
        const nonce = await response.text();
        return nonce;
    };

    const sendLoginData = async (address, signature) => {
        const response = await fetch('http://localhost:8080/metamask/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                address: encodeURIComponent(address),
                signature: encodeURIComponent(signature)
            })
        });
        
        if (response.ok) {
            history('/dashboard'); 
        } else {
            console.error('Login failed:', response.statusText);
        }
    };

    return (
        <div className="container">
            <div className="form-signin">
                <h3 className="form-signin-heading">Please sign in</h3>
                <button className="btn btn-lg btn-primary btn-block" onClick={login}>Login with MetaMask</button>
            </div>
        </div>
    );
};

export default MetaMaskLogin;
