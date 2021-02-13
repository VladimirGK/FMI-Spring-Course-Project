package com.autodeli.web.user;

import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Battery;
import com.autodeli.web.consumable.Oil;
import com.autodeli.web.consumable.Supplement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Document("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @Pattern(regexp = "[A-Za-z0-9]{24}")
    private String id;
    @Pattern(regexp = "[A-Za-z0-9]{24}")
    @NonNull
    @NotNull
    private String userId;
    @Size(min = 2, max = 30)
    @NonNull
    @NotNull
    private String firstName;
    @Size(min = 2, max = 30)
    @NonNull
    @NotNull
    private String lastName;
    @Size(min = 2, max = 30)
    @NonNull
    @NotNull
    private String city;
    @Size(min = 2, max = 60)
    @NonNull
    @NotNull
    private String address;
    @Pattern(regexp = ".*[^0-9].*")
    private String number;
    @PastOrPresent
    private LocalDateTime created = LocalDateTime.now();

    private List<AutoPart> autoParts = null;
    private List<Oil> oils = null;
    private List<Battery> batteries = null;
    private List<Supplement> supplements = null;
    private double total;

    public Order(@Pattern(regexp = "[A-Za-z0-9]{24}") String userId,
                 @Size(min = 2, max = 30) @NonNull @NotNull String firstName,
                 @Size(min = 2, max = 30) @NonNull @NotNull String lastName,
                 @Size(min = 2, max = 30) @NonNull @NotNull String city,
                 @Size(min = 2, max = 60) @NonNull @NotNull String address,
                 @Pattern(regexp = ".*[^0-9].*") String number) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.number = number;
    }

    public Order(@Pattern(regexp = "[A-Za-z0-9]{24}") @NonNull @NotNull String userId,
                 @Size(min = 2, max = 30) @NonNull @NotNull String firstName,
                 @Size(min = 2, max = 30) @NonNull @NotNull String lastName,
                 @Size(min = 2, max = 30) @NonNull @NotNull String city,
                 @Size(min = 2, max = 60) @NonNull @NotNull String address,
                 @Pattern(regexp = ".*[^0-9].*") String number,
                 List<AutoPart> autoParts,
                 List<Oil> oils,
                 List<Battery> batteries,
                 List<Supplement> supplements,
                 double total) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.number = number;
        this.autoParts = autoParts;
        this.oils = oils;
        this.batteries = batteries;
        this.supplements = supplements;
        this.total = total;
    }
}
