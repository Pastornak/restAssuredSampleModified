package models;

import lombok.*;

@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ResponseError {
    public String timestamp;
    public Integer statusCode;
    public String error;
    public String errorMessage;
}
