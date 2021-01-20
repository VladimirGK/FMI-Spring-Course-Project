package com.autodeli.web.consumable;

import lombok.NonNull;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "oils")
public class Oil extends Consumable {
  public Oil() {
    super();
  }
  public Oil(@Size(min = 2, max = 80) @NonNull @NotNull String name, @Size(min = 2, max = 20) @NonNull @NotNull String brand,
      @NonNull @NotNull float price, @URL @NonNull @NotNull String photoUrl) {
    super(name, brand, price, photoUrl);
  }
}
