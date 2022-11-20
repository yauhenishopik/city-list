import React from 'react';
import './App.css';
import './AxiosInterceptors.js'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import CitiesList from './CitiesList';
import CityEdit from "./CityEdit";
import Login from "./Login";


export default function App() {
    return (
        <Router>
            <Routes>
                <Route path='/' exact element={<CitiesList/>}/>
                <Route path='/login' exact element={<Login/>}/>
                <Route path='/cities' exact element={<CitiesList/>}/>
                <Route path='/cities/:id' element={<CityEdit/>}/>
            </Routes>
        </Router>
    )
}
