package springframework.values.demo.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springframework.values.demo.domain.Value;
import springframework.values.demo.repositories.ValueRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValueService {

    @Autowired
    final private ValueRepository valueRepository;

    public ValueService(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public void deleteAllValues() {
        valueRepository.deleteAll();
    }

    public int childRecursion(int total, Value value) {
        if (value.getChild() != null) {
            for (int i = 0; i < value.getChild().size(); i++) {
                total += value.getChild().get(i).getValue();
                total = childRecursion(total, value.getChild().get(i));
            }
        } else {
            return total;
        }

        return total;
    }

    public List<Value> mapJsonToValueObj(String json) {
        Gson gson = new Gson();
        Value[] values = gson.fromJson(json, Value[].class);
        List<Value> wrapperList = new ArrayList<>();

        for (Value value : values) {
            Value totalValueObj = new Value();
            int total = 0;
            total += value.getValue();
            total = childRecursion(total, value);
            totalValueObj.setTotal(total);
            totalValueObj.getChild().add(value);
            wrapperList.add(totalValueObj);
        }

        return wrapperList;
    }
}
