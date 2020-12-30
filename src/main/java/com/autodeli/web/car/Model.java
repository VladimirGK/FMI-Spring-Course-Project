package com.autodeli.web.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "models")
public class Model {

  @Id
  @Pattern(regexp = "^[A-Fa-f0-9]{24}$")
  private String id;
  @Size(min = 2, max = 80)
  @NonNull
  @NotNull
  private String name;
  @Size(min = 2, max = 80)
  @NonNull
  @NotNull
  private String brandName;
}
