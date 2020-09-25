package by.bsuir.coursework.pmacoursework.util;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DataExporter {

    public void jsonExporter(Employee employee) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String fileName = "employee_" + employee.getId() + ".json";
        mapper.writeValue(new File(fileName), employee);
    }

    public void xmlExporter(Employee employee) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String fileName = "employee_" + employee.getId() + ".xml";
        xmlMapper.writeValue(new File(fileName), employee);
    }
}
