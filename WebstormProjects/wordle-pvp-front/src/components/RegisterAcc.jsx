import React, { useState } from 'react';
import { getAuth, createUserWithEmailAndPassword } from 'firebase/auth';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import styles from './SignUp.module.css'; // Assuming your CSS module is named SignUp.module.css

const RegisterAcc = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate(); // Initialize useNavigate

    const handleSignUp = async (e) => {
        e.preventDefault();
        const auth = getAuth();
        try {
            await createUserWithEmailAndPassword(auth, email, password);
            // Redirect to home or login page after successful sign-up
            navigate('/');
        } catch (error) {
            setError(error.message);
        }
    };

    const handleLoginRedirect = () => {
        navigate('/'); // Redirect to the login page
    };

    return (
        <div className={styles.loginContainer}>
            <h2>Sign Up</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSignUp}>
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit">Sign Up</button>
            </form>
            <button onClick={handleLoginRedirect} style={{ marginTop: '10px' }}>
                Login
            </button>
        </div>
    );
};

export default RegisterAcc;
