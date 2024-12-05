import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'

export default function EditEmployee() {

const urlBase = "http://localhost:8080/rh-app/employees"    

let navegation = useNavigate();

const {id} = useParams();

const [employee, setEmployee]=useState({
  nameEmployee:"",
  departmentEmployee:"",
  salaryEmployee:""
})

const{nameEmployee, departmentEmployee, salaryEmployee} = employee

useEffect(()=>{
    getEmployee();
},[])

const getEmployee = async () => {
    const result = await axios.get(`${urlBase}/${id}`)
    setEmployee(result.data);
}

const onInputChange = (e) => {
  setEmployee({...employee, [e.target.name]: e.target.value})
}

const onSubmit = async (e) => {
  e.preventDefault(); 
  await axios.put(`${urlBase}/${id}`, employee);
  navegation('/');
  
}

  return (
    <div className='container'>
      <div className='container text-center' style={{margin: "30px"}}>
        <h3>Edit Employee</h3>
      </div>
      <form onSubmit={(e)=> onSubmit(e)}>
      <div className="mb-3">
        <label htmlFor="name" className="form-label">Name</label>
        <input type="text" className="form-control" id="name" name='nameEmployee' required={true}
        value={nameEmployee} onChange={(e)=>onInputChange(e)}/>
      </div>
      <div className="mb-3">
        <label htmlFor="department" className="form-label">Department</label>
        <input type="text" className="form-control" id="department" name='departmentEmployee'
        value={departmentEmployee} onChange={(e)=>onInputChange(e)}/>
      </div>
      <div className="mb-3">
        <label htmlFor="salary" className="form-label">Salary</label>
        <input type="number" step="any" className="form-control" id="salary" name='salaryEmployee'
        value={salaryEmployee} onChange={(e)=>onInputChange(e)}/>
      </div>
      <div className='text-center'>
        <button type="submit" className="btn btn-warning btn-sm me-3">Save</button>
        <a href='/' className='btn btn-danger btn-sm'>Return</a>
      </div>
      </form>

    </div>
  )
}
