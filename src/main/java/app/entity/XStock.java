package app.entity;

import lombok.Value;

@Value
public class XStock {
  String exchangeTimezoneName;
  String fullExchangeName;
  String symbol;
}
