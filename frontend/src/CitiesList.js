import React, {useEffect} from 'react';
import axios from 'axios'
import {Button, ButtonGroup, Container, Input, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import './TokenStorage.js';
import {Link, useNavigate} from 'react-router-dom';
import {errorHandler} from "./AxiosInterceptors";
import {hasRole} from "./TokenStorage";

export function CitiesList() {

    const navigate = useNavigate()
    const [data, setData] = React.useState([])
    const [filters, setFilters] = React.useState({})
    const [pageCount, setPageCount] = React.useState(1)
    const [pageIndex, setPageIndex] = React.useState(0)
    const [pageSize, setPageSize] = React.useState(10)

    useEffect(() => {
        uploadData(filters)
    },[filters, pageIndex, pageSize, pageIndex])

    async function handleFilterChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let stateFilters = {...filters};
        stateFilters[name] = value;
        setFilters(stateFilters)
    }

    async function uploadData() {
        await axios.post('/api/v1/city/search?sort=id&page=' + pageIndex + '&size=' + pageSize, filters)
            .then(response => response.data)
            .then(data => {
                setData(data.content)
                setPageCount(data.totalPages)
            }).catch(error => errorHandler(error, navigate))
    }

    function isNextPageDisabled() {
        return !(pageIndex < pageCount);
    }

    function isPreviousPageDisabled() {
        return pageIndex <= 0;
    }

    return (
        <div>
            <AppNavbar/>
            <Container className="table-wrapper">
                <h3>Cities</h3>
                <Table className="table table-striped">
                    <thead>
                    <tr className="row">
                        <th className="col-1">Id</th>
                        <th className="col-1">Name</th>
                        <th className="col-9">Photo</th>
                        <th className="col-1">Actions</th>
                    </tr>
                    <tr className="row">
                        <th className="col-1"></th>
                        <th className="col-1">
                            <Input type="text" name="name" id="name" onChange={handleFilterChange} autoComplete="name"/>
                        </th>
                        <th className="col-9"></th>
                        <th className="col-1"></th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map(city => {
                        return <tr key={city.id} className="row">
                            <td className="col-1">{city.id}</td>
                            <td className="col-1">{city.name}</td>
                            <td className="col-9"><a href={city.photoUrl} target="_blank">{city.photoUrl}</a></td>
                            <td className="col-1">
                                <ButtonGroup>
                                    <Button size="sm" color="primary" tag={Link} to={"/cities/" + city.id} disabled={!hasRole("ROLE_ALLOW_EDIT")}>Edit</Button>
                                </ButtonGroup>
                            </td>
                        </tr>
                    })}
                    </tbody>
                </Table>
                <div className="pagination">
                    <button onClick={() => setPageIndex(0)} disabled={isPreviousPageDisabled()}>
                        {'<<'}
                    </button>
                    {' '}
                    <button onClick={() => setPageIndex(pageIndex - 1)} disabled={isPreviousPageDisabled()}>
                        {'<'}
                    </button>
                    {' '}
                    <button onClick={() => setPageIndex(pageIndex + 1)} disabled={isNextPageDisabled()}>
                        {'>'}
                    </button>
                    {' '}
                    <button onClick={() => setPageIndex(pageCount - 1)} disabled={isNextPageDisabled()}>
                        {'>>'}
                    </button>
                    {' '}
                    <span>Page{' '}<strong>{pageIndex + 1} of {pageCount}</strong>{' '}</span><span>
          | Go to page:{' '}
                    <input
                        type="number"
                        defaultValue={pageIndex + 1}
                        onChange={e => {
                            const page = e.target.value ? Number(e.target.value) - 1 : 0
                            setPageIndex(page)
                        }}
                        style={{width: '100px'}}
                    />
        </span>{' '}
                    <select
                        value={pageSize}
                        onChange={e => {
                            setPageSize(Number(e.target.value))
                            setPageIndex(0)
                        }}
                    >
                        {[10, 20, 30, 40, 50].map(pageSize => (
                            <option key={pageSize} value={pageSize}>
                                Show {pageSize}
                            </option>
                        ))}
                    </select>
                </div>
            </Container>
        </div>
    );
}

export default CitiesList;