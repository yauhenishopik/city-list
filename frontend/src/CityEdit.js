import React, {useEffect} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';
import axios from "axios";
import {errorHandler} from "./AxiosInterceptors";

export default function CityEdit() {

    const params = useParams();
    const navigate = useNavigate()
    const [city, setCity] = React.useState({})

    useEffect(() => {
        axios.get(`/api/v1/city/${params.id}`)
            .then(response => response.data)
            .then(data => setCity(data))
            .catch(error => errorHandler(error, navigate))
    }, [])

    function handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...city};
        item[name] = value;
        setCity(item);
    }

    async function handleSubmit(event) {
        event.preventDefault();
        await axios.put('/api/v1/city', city).catch(error => errorHandler(error, navigate))
        navigate('/cities');
    }


    return <div>
        <AppNavbar/>
        <Container>
            <h2>Edit City</h2>
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="id">Id</Label>
                    <Input type="text" name="id" id="id" value={city.id || ''} disabled={true} autoComplete="id"/>
                </FormGroup>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input type="text" name="name" id="name" value={city.name || ''}
                           onChange={handleChange} autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Label for="photoUrl">Photo URL</Label>
                    <Input type="text" name="photoUrl" id="photoUrl" value={city.photoUrl || ''}
                           onChange={handleChange} autoComplete="photoUrl"/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/cities">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>
}