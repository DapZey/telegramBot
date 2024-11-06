import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { auth, provider } from '../firebase.js'; // Ensure your provider is properly exported from your Firebase setup
import { signInWithEmailAndPassword, signInWithPopup } from 'firebase/auth';
import styles from './Login.module.css';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await signInWithEmailAndPassword(auth, username, password);
            console.log('User logged in successfully');
            navigate('/'); // Redirect to account page after successful login
        } catch (error) {
            console.error('Error logging in:', error.message);
            // Optionally, show a user-friendly error message here
        }
    };

    const handleSignUp = () => {
        navigate('/signup');
    };

    const handleGoogleLogin = async () => {
        try {
            await signInWithPopup(auth, provider);
            console.log('User logged in with Google successfully');
            navigate('/'); // Redirect to account page after successful Google login
        } catch (error) {
            console.error('Error logging in with Google:', error.message);
            // Optionally, show a user-friendly error message here
        }
    };

    return (
        <div className={styles.loginContainer}>
            <h2>Login</h2>
            <InputField
                type="email" // Change to email type for better UX
                placeholder="Email"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <InputField
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleLogin}>Login</button>
            <button onClick={handleGoogleLogin} className={styles.googleButton}>Login with Google</button>
            <p>Not logged in?</p>
            <button onClick={handleSignUp}>Sign Up</button>
        </div>
    );
};

const InputField = ({ type, placeholder, value, onChange }) => {
    return (
        <input
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            className={styles.inputField} // Added for styling purposes
        />
    );
};

export default Login;
