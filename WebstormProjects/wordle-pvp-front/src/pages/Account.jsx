// src/pages/Account.jsx
import React from 'react';
import Logout from '../components/Logout'; // Import the Logout component
import styles from './Account.module.css'; // You can create this file if you want additional styles

const Account = () => {
    return (
        <div className={styles.accountContainer}>
            <h1>Your Account</h1>
            <p>This is a protected route. You can access this page only when logged in.</p>
            <Logout /> {/* Include the Logout button */}
        </div>
    );
};

export default Account;
