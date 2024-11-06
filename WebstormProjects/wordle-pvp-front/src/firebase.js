// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// import { getAnalytics } from "firebase/analytics";
import { getAuth, GoogleAuthProvider, setPersistence, browserLocalPersistence } from 'firebase/auth';
const firebaseConfig = {
    apiKey: "AIzaSyBkSj1-C145f1TaTQUHh4GLtP_vBRZnz9c",
    authDomain: "wordlepvp.firebaseapp.com",
    projectId: "wordlepvp",
    storageBucket: "wordlepvp.firebasestorage.app",
    messagingSenderId: "183955152648",
    appId: "1:183955152648:web:375fbee1014b50a93516bd",
    measurementId: "G-9DRE3DE2HW"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const provider = new GoogleAuthProvider(); // Create a Google Auth Provider
setPersistence(auth, browserLocalPersistence)
    .then(() => {
        // Existing and future Auth states are now set to be stored in the browser's local storage
    })
    .catch((error) => {
        console.error("Error setting persistence:", error);
    });

export { auth, provider };
