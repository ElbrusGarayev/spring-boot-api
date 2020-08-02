package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YItem {
    private String exchangeTimezoneName;
    private String fullExchangeName;
    private String symbol;
}
