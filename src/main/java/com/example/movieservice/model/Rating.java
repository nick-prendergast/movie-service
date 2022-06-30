package com.example.movieservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Data
@Entity
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private String title;

    @JsonProperty("rating")
    private int movieRating;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal boxOffice;

    public void setBoxOffice(String boxOffice) {
        BigDecimal moneyAmount = BigDecimal.ZERO;

        if (!boxOffice.equals("N/A")) {
            Locale locale = new Locale("en", "US");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            try {
                Number money = currencyFormat.parse(boxOffice);
                moneyAmount = BigDecimal.valueOf(money.doubleValue());
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }
        this.boxOffice = moneyAmount;
    }
}
