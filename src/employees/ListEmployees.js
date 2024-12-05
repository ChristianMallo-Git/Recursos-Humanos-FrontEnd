import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { NumericFormat } from 'react-number-format';
import { Link } from 'react-router-dom';

export default function ListEmployees() {

    const urlBase = "http://localhost:8080/rh-app/employees"

    const[employees, setEmployees]=useState([]);

    useEffect(() => {
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        const result = await axios.get(urlBase);
        console.log("Result loaad employees");
        console.log(result.data);
        setEmployees(result.data);
    }

    const deleteEmployee= async (id) => {
        await axios.delete(`${urlBase}/${id}`);
        loadEmployees();
    }

  return (
    <div className='container'>
        
        <div className='container text-center' style={{margin: "30px"}}>
            <h3>Human resources system</h3>
         </div>

        <table className="table table-striped table-hover align-middle">
        <thead className='table-dark'>
            <tr>
            <th scope="col">Id</th>
            <th scope="col">Employee</th>
            <th scope="col">Department</th>
            <th scope="col">Salary</th>
            <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            {
            //Iteramos el arreglo de empleados
            employees.map((employee, index) => (
                <tr key={index}>
                <th scope="row">{employee.idEmployee}</th>
                <td>{employee.nameEmployee}</td>
                <td>{employee.departmentEmployee}</td>
                <td><NumericFormat value={employee.salaryEmployee}
                    displayType={'text'}
                    thousandSeparator=',' prefix={'$'}
                    decimalScale={2} fixedDecimalScale/>
                    </td>
                <td className='text-center'>
                    <div>
                        <Link to={`/edit/${employee.idEmployee}`}
                        className='btn btn-warning btn-sm me-3'>Edit</Link>
                        <button 
                        onClick={()=> deleteEmployee(employee.idEmployee)} className='btn btn-danger btn-sm'
                        >Delete</button>
                    </div>
                    </td>    
            </tr>
            ))
            }
        </tbody>
        </table>
    </div>
  )
}
