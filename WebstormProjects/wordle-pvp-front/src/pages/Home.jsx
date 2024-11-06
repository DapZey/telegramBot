import React from 'react';
import WelcomeText from "../components/welcomeTitle.jsx";
import Login from '../components/Login';
const Home = () => {
    return (
        <div>
            <WelcomeText/>
            <Login />
        </div>
    );
};

export default Home;