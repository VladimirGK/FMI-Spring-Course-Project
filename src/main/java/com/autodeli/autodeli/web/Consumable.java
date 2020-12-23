package com.autodeli.autodeli.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class Consumable {

  @Id
  @Pattern(regexp = "^[A-Fa-f0-9]{24}$")
  private String id;
  @Pattern(regexp = "^[A-Fa-f0-9]{24}$")
  @Size(min = 2, max = 80)
  @NonNull
  @NotNull
  private String name;
  @Pattern(regexp = "^[A-Fa-f0-9]{24}$")
  @Size(min = 2, max = 20)
  @NonNull
  @NotNull
  private String brand;
  @NonNull
  @NotNull
  private float price;
  @URL
  @NonNull
  @NotNull
  private String photoUrl;
  private LocalDateTime created = LocalDateTime.now();
  private LocalDateTime modified = LocalDateTime.now();
}
