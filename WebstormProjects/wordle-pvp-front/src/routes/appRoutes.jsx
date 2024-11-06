// src/routes/AppRoutes.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import SignUp from '../pages/SignUp.jsx';
import Account from '../pages/Account.jsx';
import ProtectedRoute from '../components/ProtectedRoute';

const AppRoutes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/Home" element={<Home />} />
                <Route path="/signup" element={<SignUp />} />
                <Route
                    path="/"
                    element={
                        <ProtectedRoute>
                            <Account />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </Router>
    );
};

export default AppRoutes;
