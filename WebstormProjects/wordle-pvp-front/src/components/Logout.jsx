// src/components/Logout.jsx
import React from 'react';
import { getAuth, signOut } from 'firebase/auth';
import styles from './Logout.module.css'; // Create a separate CSS module for logout styles

const Logout = () => {
    const handleLogout = async () => {
        const auth = getAuth();
        try {
            await signOut(auth);
            console.log('User logged out successfully');
            // Optionally redirect to home or show a message
        } catch (error) {
            console.error('Error logging out:', error.message);
            // Optionally show an error message here
        }
    };

    return (
        <button onClick={handleLogout} className={styles.logoutButton}>
            Logout
        </button>
    );
};

export default Logout;
