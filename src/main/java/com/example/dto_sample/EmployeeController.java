package com.example.dto_sample;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository erepo;
	@RequestMapping("/test")
	public String test()
	{
		return "this is test for dto sample";
	}
@RequestMapping("/add")
public String addingg(@RequestBody EmployeeDTO emp)
{
	Employee e=new Employee();
	e.setName(emp.getName());
	e.setAge(emp.getAge());
	e.setCity(emp.getCity());
	erepo.save(e);
	return "data saved";
}
@RequestMapping("/all")
List<EmployeeDTO>allshow()
{
	return erepo.findAll().stream().map( employe ->{
		EmployeeDTO employeDTO=new EmployeeDTO();
		employeDTO.setId(employe.getId());
		employeDTO.setName(employe.getName());
		employeDTO.setCity(employe.getCity());
		employeDTO.setAge(employe.getAge());
		return employeDTO;
	}
	)
	.collect(Collectors.toList());	
	}

@GetMapping("/{id}")
public EmployeeDTO byid(@PathVariable int id)
{
	Employee e=erepo.findById(id).get();
	EmployeeDTO employeeDTO=new EmployeeDTO();
	BeanUtils.copyProperties(e,employeeDTO);
	return employeeDTO;
	
}
@GetMapping("/name/{name}")
public EmployeeDTO byname(@PathVariable String name)
{
	Employee e=erepo.findByName(name).get();
	EmployeeDTO employeeDTO=new EmployeeDTO();
	BeanUtils.copyProperties(e, employeeDTO);
	return employeeDTO;
	
}
@PatchMapping("/upd/{id}")
public EmployeeDTO update(@PathVariable int id,@RequestBody EmployeeDTO empDTO)
{
	Employee e=erepo.findById(id).get();
	//e.setAge(empDTO.getAge());
	e.setCity(empDTO.getCity());
	//e.setName(empDTO.getName());
	erepo.save(e);
	return empDTO;
	}

}

