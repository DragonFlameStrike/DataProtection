package pankov.dv.defence.lab2.task1;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InputValuesDto {
    private Integer x; //начальное значение (0<=X<m)
    private Integer a; //множитель (0<=a<m)
    private Integer c; //приращение (0<=c<m)
    private Integer m; //модуль (m>=2)
}
