package models.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import models.BaseModel;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class Genre extends BaseModel {
    public Integer genreId;
    public String name;
    public String description;
}
