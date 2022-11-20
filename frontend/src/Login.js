import React from 'react';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {setToken} from "./TokenStorage";
import {useNavigate} from 'react-router-dom';

export function Login() {

    const navigate = useNavigate()
    const [username, setUsername] = React.useState("")
    const [password, setPassword] = React.useState("")

    async function handleSubmit(event) {
        event.preventDefault();
        setToken(btoa(username + ':' + password))
        navigate("/cities")
    }

    return <div>
        <AppNavbar/>
        <Container>
            <h2>Log in</h2>
            <Form onSubmit={event => handleSubmit(event)}>
                <FormGroup>
                    <Label for="username">Username</Label>
                    <Input type="text" name="username" id="username" value={username || ''}
                           onChange={event => setUsername(event.target.value)} autoComplete="username"/>
                </FormGroup>
                <FormGroup>
                    <Label for="password">Password</Label>
                    <Input type="password" name="password" id="password" value={password || ''}
                           onChange={event => setPassword(event.target.value)} autoComplete="password"/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Log in</Button>{' '}
                </FormGroup>
            </Form>
        </Container>
    </div>
}

export default Login