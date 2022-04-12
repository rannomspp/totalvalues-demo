package springframework.values.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"id"})
@Table(name = "valuestable")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int total;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Integer value;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Value> childs = new ArrayList<>();

    public Value(){}

    public Value(int value, List<Value> child) {
        this.value = Integer.valueOf(value);
        this.childs = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getValue() {
        if (value != null) {
            return value.intValue();
        } else {
            return 0;
        }
    }

    public void setValue(int value) {
        this.value = Integer.valueOf(value);
    }

    public List<Value> getChild() {
        return childs;
    }

    public void setChild(List<Value> child) {
        this.childs = child;
    }

    @Override
    public String toString() {
        return "Value{" +
                ", value=" + value +
                ", childs=" + childs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Value value = (Value) o;

        return id != null ? id.equals(value.id) : value.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}