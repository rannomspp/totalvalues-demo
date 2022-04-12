package springframework.values.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springframework.values.demo.domain.JSONtoDatabase;
import springframework.values.demo.domain.Value;
import springframework.values.demo.repositories.JSONRepository;
import springframework.values.demo.services.JSONService;
import springframework.values.demo.services.ValueService;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
public class ValueController {
    private final JSONRepository jsonRepository;

    @Autowired
    private JSONService jsonService;
    @Autowired
    private ValueService valueService;

    public ValueController(JSONRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/set_values")
    public void setValues(RequestEntity<String> json){
        jsonService.deleteAllValues();
        JSONtoDatabase jsonToString = new JSONtoDatabase(json.getBody());
        jsonService.setValues(jsonToString);
    }

    @RequestMapping("/get_values_total")
    public List<Value> getAllValues() {
        valueService.deleteAllValues();
        return valueService.mapJsonToValueObj(jsonService.getAllValues().get(0).getJson());
    }
}