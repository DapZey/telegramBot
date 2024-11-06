import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import { getAuth, onAuthStateChanged } from 'firebase/auth';

const ProtectedRoute = ({ children }) => {
    const [loading, setLoading] = useState(true);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const auth = getAuth();

    useEffect(() => {
        const unsubscribe = onAuthStateChanged(auth, (user) => {
            setIsAuthenticated(!!user); // Set authentication status based on user state
            setLoading(false); // Stop loading once user state is checked
        });
        return () => unsubscribe(); // Cleanup subscription on unmount
    }, [auth]);

    if (loading) {
        return <div>Loading...</div>; // Show a loading state while checking auth
    }

    // If user is not authenticated, redirect to home page
    return isAuthenticated ? children : <Navigate to="/Home" />;
};

export default ProtectedRoute;
