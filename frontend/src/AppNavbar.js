import React from 'react';
import {Button, Navbar, NavbarBrand} from 'reactstrap';
import {Link, useNavigate} from 'react-router-dom';
import {removeToken} from "./TokenStorage";

export default function AppNavbar() {

    const navigate = useNavigate()

    function logout() {
        removeToken()
        navigate("/login")
    }

    return <Navbar color="dark" dark expand="md">
        <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
        <div className="float-right">
            <Button size="sm" color="danger" onClick={() => logout()}>Log out</Button>
        </div>
    </Navbar>;
}