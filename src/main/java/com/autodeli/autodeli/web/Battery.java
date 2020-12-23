package com.autodeli.autodeli.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Document(collection = "batteries")
public class Battery extends Consumable {

  public Battery(@Pattern(regexp = "^[A-Fa-f0-9]{24}$") @Size(min = 2, max = 80) @NonNull @NotNull String name,
      @Pattern(regexp = "^[A-Fa-f0-9]{24}$") @Size(min = 2, max = 20) @NonNull @NotNull String brand, @NonNull @NotNull float price,
      @URL @NonNull @NotNull String photoUrl) {
    super(name, brand, price, photoUrl);
  }
}
