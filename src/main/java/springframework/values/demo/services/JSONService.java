package springframework.values.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springframework.values.demo.domain.JSONtoDatabase;
import springframework.values.demo.repositories.JSONRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JSONService {

    @Autowired
    private JSONRepository jsonRepository;

    public List<JSONtoDatabase> getAllValues(){
        List<JSONtoDatabase> valuesJSON = new ArrayList<>();
        jsonRepository.findAll().forEach(valuesJSON::add);
        return valuesJSON;
    }

    public void setValues(JSONtoDatabase json){
        jsonRepository.save(json);
    }

    public void deleteAllValues() {
        jsonRepository.deleteAll();
    }
}